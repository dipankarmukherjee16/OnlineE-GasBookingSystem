package com.cg.dto;

import com.cg.util.CgUtil;

public class CustomerDto {
	private Integer customerId;
	private String userName;
	private String mobileNumber;
	private String email;
	private String aadharCard;
	private String adderss;
	private String city;
	private String connectionStatus;
	private String cylinderType;
	
	public CustomerDto(Integer customerId, String userName, String mobileNumber, String email, String aadharCard,
			String adderss, String city,  String connectionStatus, String cylinderType) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.aadharCard = aadharCard;
		this.adderss = adderss;
		this.city = city;
		this.connectionStatus = connectionStatus;
		this.cylinderType = cylinderType;
	}
	
	public CustomerDto() {
		super();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAadharCard() {
		return aadharCard;
	}

	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}

	public String getAdderss() {
		return adderss;
	}

	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus() {
		this.connectionStatus = CgUtil.CONNECTIONSTATUS;
	}

	public String getCylinderType() {
		return cylinderType;
	}

	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}

}
