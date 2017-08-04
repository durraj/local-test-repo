package com.std.boot.service;

import java.util.Iterator;

import com.std.boot.model.DealModel;
import com.std.boot.model.User;
import com.std.dto.DealDTO;

public interface DealService {
	//void save(Iterator<DealDTO> deals);
	DealDTO save(DealDTO deal);
}
