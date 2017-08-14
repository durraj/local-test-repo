package com.std.boot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.std.api.response.APIStatus;
import com.std.api.response.StatusResponse;
import com.std.api.util.APIUtil;
import com.std.api.util.UniqueID;
import com.std.boot.model.Customer;
import com.std.boot.model.DealModel;
import com.std.boot.service.DealService;
import com.std.boot.service.DealServiceImpl;
import com.std.dto.DealDTO;
import com.std.dto.DealsDTO;
import com.std.integration.scheduler.RESTDealReader;
import com.std.slugify.Slugify;

@RestController
@RequestMapping("/api")
public class DealsController extends APIUtil{
	 private static final Logger LOGGER = LoggerFactory.getLogger(DealsController.class);
	@Autowired
	private DealService dealService;
	@Autowired
	private ItemReader itemReader;

	@RequestMapping(value = "/deals", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String deals(HttpServletRequest request) {
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
				LOGGER.debug("Deal Already exits with deal id: "+deal.getDealId()+" & Deal Name :"
						+deal.getDealTitle());
				continue;}
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
	@RequestMapping(value = "/allDeals", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String allDealsWithPageNum(@RequestParam String pageNumber,
			@RequestParam String pageSize,
			HttpServletRequest request) {
		LOGGER.info("Session Id is : "+request.getSession().getId());
		List<DealDTO> allDeals=  new ArrayList<>();
		if(pageNumber!=null && pageNumber.equalsIgnoreCase("undefined"))
		{
			pageNumber="0";
		}
		int pageIdInt= Integer.valueOf(pageNumber);
	try {
		Page<DealDTO> alldeals = dealService.findAll(pageIdInt, 100);
		//alldeals.iterator().forEachRemaining(allDeals::add);
		//return (List<Customer>) alldeals.iterator();
		//dealService.save((Iterator<DealDTO>) dealList);
		//statusResponse = new StatusResponse(APIStatus.OK.getCode(), alldeals);
		return writeObjectToJson(alldeals);
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
	
	@RequestMapping(value = "/allDeals/{pageId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String allDeals(@PathVariable("pageId") String pageId,HttpServletRequest request) {
		LOGGER.info("Session Id is : "+request.getSession().getId());
		List<DealDTO> allDeals=  new ArrayList<>();
		if(pageId!=null && pageId.equalsIgnoreCase("undefined"))
		{
			pageId="0";
		}
		int pageIdInt= Integer.valueOf(pageId);
	try {
		Page<DealDTO> alldeals = dealService.findAll(pageIdInt, 100);
		//alldeals.iterator().forEachRemaining(allDeals::add);
		//return (List<Customer>) alldeals.iterator();
		//dealService.save((Iterator<DealDTO>) dealList);
		//statusResponse = new StatusResponse(APIStatus.OK.getCode(), alldeals);
		return writeObjectToJson(alldeals);
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
	@RequestMapping(value = "/dealDetail/{dealId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public String dealDetails(@PathVariable("dealId") String dealId) {
		DealDTO deal = dealService.findDealBySeoName(dealId);
		statusResponse = new StatusResponse(APIStatus.OK.getCode(), deal);
		return writeObjectToJson(statusResponse);
		
	}

}
