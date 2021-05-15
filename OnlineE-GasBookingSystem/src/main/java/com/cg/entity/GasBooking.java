package com.cg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "egas_booking")
public class GasBooking {
	@Id
	@Column(name="gas_booking_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq3")
	@SequenceGenerator(name = "seq3", sequenceName = "egas_seq3", allocationSize = 1 )
	private int gasBookingId;
	
	@Column(name = "booking_date")
	private LocalDate bookingDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "bill")
	private float bill;
	
	@Column(name = "dac_number")
	private int dacNumber;
	
	@Column(name = "dispatch_date")
	private LocalDate dispatchDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cust_id", referencedColumnName = "customer_id")
	private Customer customer= new Customer();

	public int getGasBookingId() {
		return gasBookingId;
	}

	public void setGasBookingId(int gasBookingId) {
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

	public int getDacNumber() {
		return dacNumber;
	}

	public void setDacNumber(int dacNumber) {
		this.dacNumber = dacNumber;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

	public LocalDate getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
