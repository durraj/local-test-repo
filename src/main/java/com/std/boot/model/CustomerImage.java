package com.std.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name="app_customer_image")
public class CustomerImage {

	public CustomerImage(){}
	
	public CustomerImage(String key, String url) {
		this.key = key;
		this.url =url;		
	}

	@Id
	@Getter
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	
	public long getId() {
		return id;
	}

	@Setter
	@Getter
	@Column(name = "s3_key", nullable = false, length=200)
	private String key;
	
	@Setter
	@Getter
	@Column(name = "url", nullable = false, length=1000)
	private String url;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}