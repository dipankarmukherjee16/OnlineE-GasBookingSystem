package com.cg.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
	private String aadharCard;
	@NotBlank(message = CgUtil.ADDRESS_REQUIRED)
	private String address;
	@NotBlank(message = CgUtil.CITY_REQUIRED)
	private String city;
	@NotBlank(message = CgUtil.CYLINDERTYPE_REQUIRED)
	@Pattern(regexp = "(domestic|industrial)", message = CgUtil.CYLINDEROPTION)
	private String cylinderType;

	public CustomerDto() {
		super();
	}

	public CustomerDto(@NotBlank(message = "UserName is REquired") String userName,
			@NotBlank(message = "Mobile required") @Pattern(regexp = "[0-9]{10}", message = "Mobile no must contain 10 digits") String mobileNumber,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email") String email,
			@NotBlank(message = "Aadhar required") String aadharCard,
			@NotBlank(message = "Address required") String address, @NotBlank(message = "City Required") String city,
			@NotBlank(message = "Cylinder type required") @Pattern(regexp = "(Domestic|Industrial)", message = "Option must be Domestic or Industrial") String cylinderType) {
		super();
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.aadharCard = aadharCard;
		this.address = address;
		this.city = city;
		this.cylinderType = cylinderType;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
