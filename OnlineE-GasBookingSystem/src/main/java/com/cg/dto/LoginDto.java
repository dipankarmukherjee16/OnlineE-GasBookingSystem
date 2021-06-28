package com.cg.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cg.util.LoginConstants;

/*
 * Created By Titas Sarkar
 */
public class LoginDto {

	@NotNull(message= LoginConstants.USERID_NOTNULL_MESSAGE)
	private Integer userId;
	
	@NotBlank(message = LoginConstants.PASSWORD_REQUIRED_MESSAGE )
	private String password;
	
	private String role;

	public LoginDto() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
