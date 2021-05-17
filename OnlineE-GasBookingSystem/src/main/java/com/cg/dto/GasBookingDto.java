package com.cg.dto;

import java.time.LocalDate;

public class GasBookingDto {
	private Integer gasBookingId;
	private LocalDate bookingDate;
	private String status;
	private Float bill;
	private Integer dacNumber;
	private LocalDate dispatchDate;
	
	public GasBookingDto() {
		super();
	}
	public GasBookingDto(Integer gasBookingId, LocalDate bookingDate, String status, Float bill, Integer dacNumber,
			LocalDate dispatchDate) {
		super();
		this.gasBookingId = gasBookingId;
		this.bookingDate = bookingDate;
		this.status = status;
		this.bill = bill;
		this.dacNumber = dacNumber;
		this.dispatchDate = dispatchDate;
	}
	public Integer getGasBookingId() {
		return gasBookingId;
	}
	public void setGasBookingId(Integer gasBookingId) {
		this.gasBookingId = gasBookingId;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Float getBill() {
		return bill;
	}
	public void setBill(Float bill) {
		this.bill = bill;
	}
	public Integer getDacNumber() {
		return dacNumber;
	}
	public void setDacNumber(Integer dacNumber) {
		this.dacNumber = dacNumber;
	}
	public LocalDate getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	
}
