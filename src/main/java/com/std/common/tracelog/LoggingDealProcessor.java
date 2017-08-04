package com.std.common.tracelog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.std.dto.DealsDTO;

/**
 * This custom {@code ItemProcessor} simply writes the information of the
 * processed deal to the log and returns the processed object.
 *
 */
public class LoggingDealProcessor implements ItemProcessor<DealsDTO, DealsDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDealProcessor.class);

    @Override
    public DealsDTO process(DealsDTO item) throws Exception {
        LOGGER.info("Processing Deal information: {}", item);
        return item;
    }
}
