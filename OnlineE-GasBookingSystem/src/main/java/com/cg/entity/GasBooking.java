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
	private Integer gasBookingId;
	
	@Column(name = "booking_date")
	private LocalDate bookingDate;
	
	@Column(name = "status", length = 10)
	private String status;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cust_id", referencedColumnName = "customer_id")
	private Customer customer= new Customer();

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

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
}
