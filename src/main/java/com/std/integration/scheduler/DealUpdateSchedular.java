package com.std.integration.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.std.api.response.APIStatus;
import com.std.api.response.StatusResponse;
import com.std.api.util.UniqueID;
import com.std.boot.controller.DealsController;
import com.std.boot.service.DealService;
import com.std.dto.DealDTO;
import com.std.dto.DealsDTO;
import com.std.slugify.Slugify;

@Component
public class DealUpdateSchedular {
	private static final Logger LOGGER = LoggerFactory.getLogger(DealsController.class);
	@Autowired
	private DealService dealService;
	@Autowired
	private ItemReader itemReader;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Scheduled(cron="0 0 */1 * * *")
    public void updateDeals()
    {
    	LOGGER.info("Deal Update Started at :"+ dateFormat.format(new Date()));
    	try {
    		DealsDTO dealDto = (DealsDTO) itemReader.read();
    		//jobLauncher.launchXmlFileToDatabaseJob();
    		List<DealDTO> dealList = dealDto.getDeals();
    		Slugify slg = new Slugify();
    		//DealServiceImpl ds= new DealServiceImpl();
    		for(DealDTO deal:dealList)
    		{
    			deal.setId(UniqueID.getUUID());
    			deal.setDealSEOName(slg.slugify(deal.getDealTitle()));
    			boolean dealExist = dealService.findDealByDealId(deal.getDealId())==null?false:true;
    			if(dealExist){
    				LOGGER.info("Deal Already exits with deal id: "+deal.getDealId()+" & Deal Name :"
    						+deal.getDealTitle());
    				continue;}
    			dealService.save(deal);
    		}
    	} catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException
    			| JobInstanceAlreadyCompleteException e) {
    		LOGGER.error("JobParametersInvalidException | JobExecutionAlreadyRunningException |JobRestartException Exception |JobInstanceAlreadyCompleteException Occured: "+e);
    	}
    	catch (UnexpectedInputException e) {
    		LOGGER.error("UnexpectedInputException Exception Occured: "+e);
    	} catch (ParseException e) {
    		LOGGER.error("ParseException Exception Occured: "+e);
    	} catch (NonTransientResourceException e) {
    		LOGGER.error("NonTransientResourceException Exception Occured: "+e);
    	} catch (Exception e) {
    		LOGGER.error("Exception Exception Occured: "+e);
    	}
    	LOGGER.info("Deal Update Ended at :"+ dateFormat.format(new Date()));
    }
}
