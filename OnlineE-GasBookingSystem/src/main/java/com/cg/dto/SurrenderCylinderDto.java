package com.cg.dto;

import java.time.LocalDate;

public class SurrenderCylinderDto {

	private int surrenderId;
	private LocalDate surrenderDate;
	private int customerId;
	
	public SurrenderCylinderDto()
	{
		super();
	}
	
	public SurrenderCylinderDto(int surrenderId, LocalDate surrenderDate, int customerId) {
		super();
		this.surrenderId = surrenderId;
		this.surrenderDate = surrenderDate;
		this.customerId = customerId;
	}

	public int getSurrenderId() {
		return surrenderId;
	}

	public void setSurrenderId(int surrenderId) {
		this.surrenderId = surrenderId;
	}

	public LocalDate getSurrenderDate() {
		return surrenderDate;
	}

	public void setSurrenderDate(LocalDate surrenderDate) {
		this.surrenderDate = surrenderDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
}
