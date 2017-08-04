package com.std.integration.scheduler;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.std.dto.DealDTO;
import com.std.dto.DealsDTO;

public class RESTDealReader implements ItemReader<DealsDTO>{



    private static final Logger LOGGER = LoggerFactory.getLogger(RESTDealReader.class);

    private final String apiUrl;
    private final RestTemplate restTemplate;

    private int nextDealIndex;
    private DealsDTO dealData;
    private boolean runJob;

    RESTDealReader(String apiUrl, RestTemplate restTemplate, boolean runJob) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        this.runJob=runJob;//nextDealIndex = 0;
    }
    
    

    @Override
    public DealsDTO read() throws Exception {
    	if(runJob)
    	{
    		LOGGER.info("Reading the information of the next deal");
    		
    		if (dealDataIsNotInitialized()) {
    			dealData = fetchDealDataFromAPI();
    		}else
    		{
    			dealData=null;
    		}
    		
    		/* DealDTO nextDeal = null;

        if (nextDealIndex < dealData.size()) {
            nextDeal = dealData.getDeals().get(nextDealIndex);
            nextDealIndex++;
        }
    		 */
    		LOGGER.info("Found deal: {}", dealData.toString());
    	}else
    	{
    		LOGGER.info("running the job is set to false");
    		this.runJob=true;
    	}

        return dealData;
    }

    private boolean dealDataIsNotInitialized() {
        return this.dealData == null;
    }

    private DealsDTO fetchDealDataFromAPI() {
        LOGGER.debug("Fetching deals data from an external API by using the url: {}", apiUrl);

        ResponseEntity<DealsDTO> response = restTemplate.getForEntity(apiUrl, DealsDTO.class);
        DealsDTO dealData = response.getBody();
        LOGGER.debug("Found {} deals", dealData.getDeals().size());

        return dealData;//.getDeals();//Arrays.asList(dealData);
    }
}
