package com.cg.entity;

import java.time.LocalDate;
import java.util.Set;

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
import org.hibernate.annotations.ManyToAny;


@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "egas_booking")
public class GasBooking {
	@Id
	@Column(name="cylinderId")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "seq3")
	@SequenceGenerator(name = "seq3", sequenceName = "egas_seq3", allocationSize = 1 )
	private int gasBookingId;
	
	@Column(name = "bookingDate")
	private LocalDate bookingDate;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "bill")
	private float bill;
	
	@Column(name = "dispatch")
	private LocalDate dispatchDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cust_Id", referencedColumnName = "customerId")
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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