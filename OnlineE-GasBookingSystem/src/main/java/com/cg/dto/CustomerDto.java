package com.cg.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.cg.util.CgUtil;

public class CustomerDto {
	@NotBlank(message = CgUtil.USERNAME_REQUIRED)
	private String userName;
	@NotBlank(message = CgUtil.MOBILE_REQUIRED)
	@Pattern(regexp = "[0-9]{10}", message = CgUtil.MOBILE_PATTERN)
	private String mobileNumber;
	@NotBlank(message = CgUtil.EMAIL_REQUIRED)
	@Email(message = CgUtil.INVALID_EMAIL)
	private String email;
	@NotBlank(message = CgUtil.AADHAR_REQUIRED)
	private String aadharCard;
	@NotBlank(message = CgUtil.ADDRESS_REQUIRED)
	private String adderss;
	@NotBlank(message = CgUtil.CITY_REQUIRED)
	private String city;
	@NotBlank(message = CgUtil.CYLINDERTYPE_REQUIRED)
	@Pattern(regexp = "(Domestic|Industrial)", message = CgUtil.CYLINDEROPTION)
	private String cylinderType;
	
	
	
	public CustomerDto() {
		super();
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

	
	

	public String getCylinderType() {
		return cylinderType;
	}

	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}

}
