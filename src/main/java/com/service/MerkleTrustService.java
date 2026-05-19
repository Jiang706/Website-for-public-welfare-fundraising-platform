package com.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.JuanzengjiluEntity;
import com.entity.MerkleBatchEntity;
import com.entity.MerkleProofEntity;
import com.utils.MerkleTreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MerkleTrustService {

    public static final String STATUS_UNPAID = "UNPAID";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_COMMITTED = "COMMITTED";

    @Autowired
    private JuanzengjiluService juanzengjiluService;

    @Autowired
    private MerkleBatchService merkleBatchService;

    @Autowired
    private MerkleProofService merkleProofService;

    public String buildLeafHash(JuanzengjiluEntity donation) {
        if (donation == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date ts = donation.getPayTime() != null ? donation.getPayTime() : donation.getJuanzengshijian();
        String time = ts == null ? "" : sdf.format(ts);
        String amount = donation.getYijuanjine() == null
                ? "0.00"
                : BigDecimal.valueOf(donation.getYijuanjine()).setScale(2, RoundingMode.HALF_UP).toPlainString();

        String payload = String.join("|",
                String.valueOf(donation.getMujuanxiangmuId() == null ? 0L : donation.getMujuanxiangmuId()),
                safe(donation.getJuanzengbianhao()),
                String.valueOf(donation.getId() == null ? 0L : donation.getId()),
                amount,
                time,
                safe(donation.getJuanzengzhanghao()),
                safe(donation.getMujuanzhezhanghao())
        );
        return MerkleTreeUtil.sha256(payload);
    }

    public List<MerkleBatchEntity> listPublicRoots(Long campaignId, Integer limit) {
        EntityWrapper<MerkleBatchEntity> wrapper = new EntityWrapper<>();
        if (campaignId != null) {
            wrapper.eq("campaign_id", campaignId);
        }
        wrapper.orderBy("id", false);
        List<MerkleBatchEntity> batches = merkleBatchService.selectList(wrapper);
        if (limit != null && limit > 0 && batches.size() > limit) {
            return batches.subList(0, limit);
        }
        return batches;
    }

    @Transactional
    public int buildPendingBatches() {
        EntityWrapper<JuanzengjiluEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("ispay", "已支付");
        List<JuanzengjiluEntity> paidDonations = juanzengjiluService.selectList(wrapper);

        List<JuanzengjiluEntity> pending = paidDonations.stream()
                .filter(item -> StringUtils.isBlank(item.getMerkleStatus()) || STATUS_PENDING.equalsIgnoreCase(item.getMerkleStatus()))
                .collect(Collectors.toList());

        if (pending.isEmpty()) {
            return 0;
        }

        Map<Long, List<JuanzengjiluEntity>> grouped = pending.stream()
                .collect(Collectors.groupingBy(item -> item.getMujuanxiangmuId() == null ? 0L : item.getMujuanxiangmuId()));

        int processed = 0;
        for (Map.Entry<Long, List<JuanzengjiluEntity>> entry : grouped.entrySet()) {
            processed += buildBatch(entry.getKey(), entry.getValue());
        }
        return processed;
    }

    @Transactional
    public int buildBatch(Long campaignId, List<JuanzengjiluEntity> donations) {
        if (donations == null || donations.isEmpty()) {
            return 0;
        }

        Collections.sort(donations, Comparator.comparing(JuanzengjiluEntity::getId));

        List<String> leaves = new ArrayList<>();
        for (JuanzengjiluEntity donation : donations) {
            String leaf = donation.getLeafHash();
            if (StringUtils.isBlank(leaf)) {
                leaf = buildLeafHash(donation);
            }
            leaves.add(leaf);
        }

        String root = MerkleTreeUtil.buildRoot(leaves);
        String batchNo = "MB-" + campaignId + "-" + System.currentTimeMillis();

        MerkleBatchEntity batch = new MerkleBatchEntity();
        batch.setBatchNo(batchNo);
        batch.setCampaignId(campaignId);
        batch.setRootHash(root);
        batch.setLeafCount(donations.size());
        batch.setStatus("PUBLISHED");
        batch.setPublishedAt(new Date());
        batch.setCreatedAt(new Date());
        merkleBatchService.insert(batch);

        for (int i = 0; i < donations.size(); i++) {
            JuanzengjiluEntity donation = donations.get(i);
            String leafHash = leaves.get(i);
            List<MerkleTreeUtil.ProofNode> proof = MerkleTreeUtil.buildProof(leaves, i);

            MerkleProofEntity proofEntity = new MerkleProofEntity();
            proofEntity.setJuanzengjiluId(donation.getId());
            proofEntity.setBatchNo(batchNo);
            proofEntity.setLeafIndex(i);
            proofEntity.setProofJson(JSON.toJSONString(proof));
            proofEntity.setCreatedAt(new Date());
            merkleProofService.insert(proofEntity);

            JuanzengjiluEntity update = new JuanzengjiluEntity();
            update.setId(donation.getId());
            update.setLeafHash(leafHash);
            update.setMerkleBatchNo(batchNo);
            update.setMerkleStatus(STATUS_COMMITTED);
            juanzengjiluService.updateById(update);
        }

        return donations.size();
    }

    public Map<String, Object> getProofByDonationId(Long donationId) {
        JuanzengjiluEntity donation = juanzengjiluService.selectById(donationId);
        if (donation == null) {
            return null;
        }

        EntityWrapper<MerkleProofEntity> proofWrapper = new EntityWrapper<>();
        proofWrapper.eq("juanzengjilu_id", donationId).orderBy("id", false);
        List<MerkleProofEntity> proofs = merkleProofService.selectList(proofWrapper);
        MerkleProofEntity latestProof = proofs.isEmpty() ? null : proofs.get(0);

        MerkleBatchEntity batch = null;
        if (StringUtils.isNotBlank(donation.getMerkleBatchNo())) {
            EntityWrapper<MerkleBatchEntity> batchWrapper = new EntityWrapper<>();
            batchWrapper.eq("batch_no", donation.getMerkleBatchNo()).orderBy("id", false);
            List<MerkleBatchEntity> batchList = merkleBatchService.selectList(batchWrapper);
            if (!batchList.isEmpty()) {
                batch = batchList.get(0);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("donationId", donation.getId());
        result.put("donationNo", donation.getJuanzengbianhao());
        result.put("campaignId", donation.getMujuanxiangmuId());
        result.put("leafHash", donation.getLeafHash());
        result.put("batchNo", donation.getMerkleBatchNo());
        result.put("merkleStatus", donation.getMerkleStatus());
        result.put("rootHash", batch == null ? null : batch.getRootHash());
        result.put("proof", latestProof == null ? "[]" : latestProof.getProofJson());
        return result;
    }

    public Map<String, Object> verifyDonation(Long donationId, String leafHashOverride, String rootHashOverride, String proofOverride) {
        Map<String, Object> proofData = getProofByDonationId(donationId);
        if (proofData == null) {
            return null;
        }

        String leafHash = StringUtils.isNotBlank(leafHashOverride) ? leafHashOverride : (String) proofData.get("leafHash");
        String rootHash = StringUtils.isNotBlank(rootHashOverride) ? rootHashOverride : (String) proofData.get("rootHash");
        String proofJson = StringUtils.isNotBlank(proofOverride) ? proofOverride : (String) proofData.get("proof");

        List<MerkleTreeUtil.ProofNode> proofNodes = JSON.parseArray(proofJson, MerkleTreeUtil.ProofNode.class);
        boolean valid = MerkleTreeUtil.verifyProof(leafHash, proofNodes, rootHash);

        Map<String, Object> result = new HashMap<>(proofData);
        result.put("valid", valid);
        return result;
    }

    private String safe(String value) {
        return value == null ? "" : value.trim();
    }
}
