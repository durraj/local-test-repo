package com.std.boot.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
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

}
