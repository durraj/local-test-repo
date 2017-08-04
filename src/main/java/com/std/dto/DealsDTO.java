package com.std.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

//@XmlRootElement(name="deal")
public class DealsDTO {
	@JsonProperty
	private String Status;
	@JsonProperty
	private int TotalRecords;
	@JsonProperty
	private List<DealDTO> Deals;
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getTotalRecords() {
		return TotalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		TotalRecords = totalRecords;
	}
	public List<DealDTO> getDeals() {
		return Deals;
	}
	public void setDeals(List<DealDTO> deals) {
		Deals = deals;
	}
	
}
