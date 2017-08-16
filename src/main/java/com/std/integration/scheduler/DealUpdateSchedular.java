package com.std.integration.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DealUpdateSchedular {
	private static final Logger log = LoggerFactory.getLogger(DealUpdateSchedular.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(cron="0 0 */1 * * *")
    public void updateDeals()
    {
    	log.info("Deal Update Started at :"+ dateFormat.format(new Date()));
    	log.info("Deal Update Ended at :"+ dateFormat.format(new Date()));
    }
}
