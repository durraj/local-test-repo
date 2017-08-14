package com.std.boot.repository;

import java.util.Iterator;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.std.boot.model.DealModel;
import com.std.boot.model.User;
import com.std.dto.DealDTO;

public interface DealsRepository extends PagingAndSortingRepository<DealDTO, String>{

	//void save(Iterator<DealDTO> deals);
	
	//void save(Iterator<DealDTO> deals);

	//void save(Iterator<DealModel> deals);
	@Query("SELECT d FROM deal_detail d WHERE d.DealId = :dealId")
    DealDTO findDealByDealId(@Param("dealId") int dealId);
	@Query("SELECT d FROM deal_detail d WHERE d.DealSEOName = :seoName")
    DealDTO findDealBySeoName(@Param("seoName") String seoName);

}
