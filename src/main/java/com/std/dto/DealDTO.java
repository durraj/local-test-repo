package com.std.dto;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Entity(name="deal_detail")
public class DealDTO {
	
	public DealDTO(){}
	public DealDTO(int dealId, String dealTitle, String dealDescription, String dealType, String couponCode,
			String deepLinkUrl, boolean featuredDeal, boolean exclusiveDeal, int merchantID, String merchantName,
			String merchantLogoURL, int networkID, String networkName, int dealCategoryID, String categoryName,
			String startDate, String endDate, int totalRecords) {
		super();
		this.DealId = dealId;
		this.DealTitle = dealTitle;
		this.DealDescription = dealDescription;
		this.DealType = dealType;
		this.CouponCode = couponCode;
		this.DeepLinkUrl = deepLinkUrl;
		this.FeaturedDeal = featuredDeal;
		this.ExclusiveDeal = exclusiveDeal;
		this.MerchantID = merchantID;
		this.MerchantName = merchantName;
		this.MerchantLogoURL = merchantLogoURL;
		this.NetworkID = networkID;
		this.NetworkName = networkName;
		this.DealCategoryID = dealCategoryID;
		this.CategoryName = categoryName;
		this.StartDate = startDate;
		this.EndDate = endDate;
		this.TotalRecords = totalRecords;
	}
	

	@Setter
	@Getter
	@Basic(optional = false)
	@Id
	private String id;
	@Setter
	@Getter
	@JsonProperty
	@Basic(optional = false)
    @Column(name = "deal_id")
	private int DealId;
	@Setter
	@Getter
	@JsonProperty
	@Basic(optional = false)
    @Column(name = "deal_title")
	private String DealTitle;
	@Setter
	@Getter
	@Basic(optional = false)
    @Column(name = "deal_seo_title")
	private String DealSEOName;
	@Setter
	@Getter
	@JsonProperty
	@Basic(optional = false)
    @Column(name = "deal_description")
	private String DealDescription;
	@Setter
	@Getter
	@JsonProperty
	@Column(name = "deal_type")
	private String DealType;
	@Setter
	@Getter
	@JsonProperty
	@Column(name = "coupon_code")
	private String CouponCode;
	@Setter
	@Getter
	@JsonProperty
	@Basic(optional = false)
    @Column(name = "deep_link")
	private String DeepLinkUrl;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "feature_deal")
	private boolean FeaturedDeal;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "exclusive_deal")
	private boolean ExclusiveDeal;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "merchant_id")
	private int MerchantID;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "merchant_name")
	private String MerchantName;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "image_url")
	private String MerchantLogoURL;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "network_id")
	private int NetworkID;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "network_name")
	private String NetworkName;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "category_id")
	private int DealCategoryID;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "category_name")
	private String CategoryName;
	@Setter
	@Getter
	@JsonProperty
	 @Column(name = "start_date")
	private String StartDate;
	@Setter
	@Getter
	@JsonProperty
	@Column(name = "end_date")
	private String EndDate;
	@Setter
	@Getter
	@JsonProperty
	@Transient
	private int TotalRecords;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDealId() {
		return DealId;
	}
	public void setDealId(int dealId) {
		DealId = dealId;
	}
	public String getDealTitle() {
		return DealTitle;
	}
	public void setDealTitle(String dealTitle) {
		DealTitle = dealTitle;
	}
	public String getDealDescription() {
		return DealDescription;
	}
	public void setDealDescription(String dealDescription) {
		DealDescription = dealDescription;
	}
	public String getDealType() {
		return DealType;
	}
	public void setDealType(String dealType) {
		DealType = dealType;
	}
	public String getCouponCode() {
		return CouponCode;
	}
	public void setCouponCode(String couponCode) {
		CouponCode = couponCode;
	}
	public String getDeepLinkUrl() {
		return DeepLinkUrl;
	}
	public void setDeepLinkUrl(String deepLinkUrl) {
		DeepLinkUrl = deepLinkUrl;
	}
	public boolean isFeaturedDeal() {
		return FeaturedDeal;
	}
	public void setFeaturedDeal(boolean featuredDeal) {
		FeaturedDeal = featuredDeal;
	}
	public boolean isExclusiveDeal() {
		return ExclusiveDeal;
	}
	public void setExclusiveDeal(boolean exclusiveDeal) {
		ExclusiveDeal = exclusiveDeal;
	}
	public int getMerchantID() {
		return MerchantID;
	}
	public void setMerchantID(int merchantID) {
		MerchantID = merchantID;
	}
	public String getMerchantName() {
		return MerchantName;
	}
	public void setMerchantName(String merchantName) {
		MerchantName = merchantName;
	}
	public String getMerchantLogoURL() {
		return MerchantLogoURL;
	}
	public void setMerchantLogoURL(String merchantLogoURL) {
		MerchantLogoURL = merchantLogoURL;
	}
	public int getNetworkID() {
		return NetworkID;
	}
	public void setNetworkID(int networkID) {
		NetworkID = networkID;
	}
	public String getNetworkName() {
		return NetworkName;
	}
	public void setNetworkName(String networkName) {
		NetworkName = networkName;
	}
	public int getDealCategoryID() {
		return DealCategoryID;
	}
	public void setDealCategoryID(int dealCategoryID) {
		DealCategoryID = dealCategoryID;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	public int getTotalRecords() {
		return TotalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		TotalRecords = totalRecords;
	}
	public String getDealSEOName() {
		return DealSEOName;
	}
	public void setDealSEOName(String dealSEOName) {
		DealSEOName = dealSEOName;
	}
}
