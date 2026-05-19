package com.controller;

import com.annotation.IgnoreAuth;
import com.annotation.SysLog;
import com.entity.MerkleBatchEntity;
import com.service.MerkleTrustService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/merkle")
public class MerkleController {

    @Autowired
    private MerkleTrustService merkleTrustService;

    @IgnoreAuth
    @RequestMapping("/public/roots")
    public R publicRoots(@RequestParam(required = false) Long campaignId,
                         @RequestParam(required = false) Integer limit) {
        List<MerkleBatchEntity> data = merkleTrustService.listPublicRoots(campaignId, limit);
        return R.ok().put("data", data);
    }

    @RequestMapping("/proof/{donationId}")
    public R proof(@PathVariable("donationId") Long donationId) {
        Map<String, Object> data = merkleTrustService.getProofByDonationId(donationId);
        if (data == null) {
            return R.error("donation not found");
        }
        return R.ok().put("data", data);
    }

    @IgnoreAuth
    @RequestMapping("/verify")
    public R verify(@RequestBody Map<String, Object> body) {
        Object donationIdObj = body.get("donationId");
        if (donationIdObj == null) {
            return R.error("donationId is required");
        }
        Long donationId = Long.valueOf(String.valueOf(donationIdObj));

        String leafHash = body.get("leafHash") == null ? null : String.valueOf(body.get("leafHash"));
        String rootHash = body.get("rootHash") == null ? null : String.valueOf(body.get("rootHash"));
        String proof = body.get("proof") == null ? null : String.valueOf(body.get("proof"));

        Map<String, Object> result = merkleTrustService.verifyDonation(donationId, leafHash, rootHash, proof);
        if (result == null) {
            return R.error("donation not found");
        }
        return R.ok().put("data", result);
    }

    @SysLog("构建Merkle批次")
    @RequestMapping("/admin/build")
    public R buildNow() {
        int processed = merkleTrustService.buildPendingBatches();
        return R.ok().put("processed", processed);
    }
}
