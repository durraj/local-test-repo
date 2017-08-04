package com.std.common.tracelog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.std.dto.DealsDTO;

import java.util.List;

/**
 * This custom {@code ItemWriter} writes the information of the deal to
 * the log.
 *
 */
public class LoggingDealWriter implements ItemWriter<DealsDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDealWriter.class);

    @Override
    public void write(List<? extends DealsDTO> items) throws Exception {
        LOGGER.info("Received the information of {} Deals", items.size());

        //items.forEach(i -> LOGGER.debug("Received the information of a Deal: {}", i));
    }
}
