package com.std.boot.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


public class DealModel {
	public DealModel(){}
	@Id
    @Basic(optional = false)
    @Column(name = "id")
	private long id;
	@Id
    @Basic(optional = false)
    @Column(name = "deal_id")
	private String dealId;
	private String dealType;
	enum dealTypes{
		COUPON,DISCOUNT
	}
	private String couponCode;
	private String url;
	private boolean featuredDeal;
	private boolean exclusiveDeal;
	private int merchantId;
	private String merchangeName;
	private String merchantLogo;
	private String networkId;
	private String networkName;
	private int dealCatagoryId;
	private String CategoryName;
	private String startDate;
	private String endDate;

}
