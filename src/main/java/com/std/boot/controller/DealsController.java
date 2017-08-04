package com.std.boot.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.std.api.response.APIStatus;
import com.std.api.response.StatusResponse;
import com.std.api.util.APIUtil;
import com.std.api.util.UniqueID;
import com.std.boot.model.DealModel;
import com.std.boot.service.DealService;
import com.std.boot.service.DealServiceImpl;
import com.std.dto.DealDTO;
import com.std.dto.DealsDTO;

@RestController
public class DealsController extends APIUtil{
	@Autowired
	private DealService dealService;
	@Autowired
	private ItemReader itemReader;

	@RequestMapping(value = "/deals", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String deals() {
	try {
		DealsDTO dealDto = (DealsDTO) itemReader.read();
		//jobLauncher.launchXmlFileToDatabaseJob();
		List<DealDTO> dealList = dealDto.getDeals();
		
		//DealServiceImpl ds= new DealServiceImpl();
		for(DealDTO deal:dealList)
		{
			deal.setId(UniqueID.getUUID());
			dealService.save(deal);
		}
		//dealService.save((Iterator<DealDTO>) dealList);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), dealDto);
		//return writeObjectToJson(dealDto);
	} catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException
			| JobInstanceAlreadyCompleteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (UnexpectedInputException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NonTransientResourceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return writeObjectToJson(statusResponse);
}

}
