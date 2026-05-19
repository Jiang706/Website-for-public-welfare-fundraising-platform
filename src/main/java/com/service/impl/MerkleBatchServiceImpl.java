package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.MerkleBatchDao;
import com.entity.MerkleBatchEntity;
import com.service.MerkleBatchService;
import org.springframework.stereotype.Service;

@Service("merkleBatchService")
public class MerkleBatchServiceImpl extends ServiceImpl<MerkleBatchDao, MerkleBatchEntity> implements MerkleBatchService {
}
