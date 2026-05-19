package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.MerkleProofDao;
import com.entity.MerkleProofEntity;
import com.service.MerkleProofService;
import org.springframework.stereotype.Service;

@Service("merkleProofService")
public class MerkleProofServiceImpl extends ServiceImpl<MerkleProofDao, MerkleProofEntity> implements MerkleProofService {
}
