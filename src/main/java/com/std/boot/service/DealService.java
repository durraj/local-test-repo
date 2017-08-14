package com.std.boot.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.std.boot.model.DealModel;
import com.std.boot.model.User;
import com.std.dto.DealDTO;

public interface DealService {
	//void save(Iterator<DealDTO> deals);
	DealDTO save(DealDTO deal);
	Page<DealDTO> findAll(int pageNum, int noOfRec);
    DealDTO findDealByDealId(int dealId);
    DealDTO findDealBySeoName(String seoName);
}
