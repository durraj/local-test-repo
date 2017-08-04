package com.std.boot.repository;

import java.util.Iterator;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.std.boot.model.DealModel;
import com.std.boot.model.User;
import com.std.dto.DealDTO;

public interface DealsRepository extends PagingAndSortingRepository<DealDTO, String>{

	//void save(Iterator<DealDTO> deals);
	
	//void save(Iterator<DealDTO> deals);

	//void save(Iterator<DealModel> deals);

}
