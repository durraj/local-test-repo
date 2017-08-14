package com.std.boot.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.std.boot.repository.DealsRepository;
import com.std.dto.DealDTO;

@Service
public class DealServiceImpl implements DealService{
	@Autowired
    private DealsRepository dealRepository;
	/*@Override
	public void save(Iterator<DealDTO> deals) {
		// TODO Auto-generated method stub
		dealRepository.save(deals);
		//return null;
	}*/

	@Override
	public DealDTO save(DealDTO deal) {
		
		return dealRepository.save(deal);
	}

	@Override
	public Page<DealDTO> findAll(int pageNum, int noOfRec) {
		return dealRepository.findAll(new PageRequest(pageNum, noOfRec));
	}

	@Override
	public DealDTO findDealByDealId(int dealId) {
		return dealRepository.findDealByDealId(dealId);
	}

	@Override
	public DealDTO findDealBySeoName(String seoName) {
		return dealRepository.findDealBySeoName(seoName);
	}
	
	
	

}
