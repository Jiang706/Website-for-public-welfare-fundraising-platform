package com.task;

import com.service.MerkleTrustService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MerkleBatchTask {

    private static final Logger log = LoggerFactory.getLogger(MerkleBatchTask.class);

    @Autowired
    private MerkleTrustService merkleTrustService;

    @Scheduled(cron = "${merkle.batch.cron:0 */10 * * * ?}")
    public void buildPendingMerkleBatches() {
        int processed = merkleTrustService.buildPendingBatches();
        if (processed > 0) {
            log.info("Merkle batch task processed {} donations", processed);
        }
    }
}
