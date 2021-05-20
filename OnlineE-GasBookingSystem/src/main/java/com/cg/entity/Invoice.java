package com.cg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@DynamicInsert
@DynamicUpdate
@Table(name = "egas_invoice")
public class Invoice {

	@Id
	@Column(name = "invoice_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq5")
	@SequenceGenerator(name = "seq5", sequenceName = "invoice_seq1", allocationSize = 1)
	private Integer invoiceId;

	@Column(name = "invoice_date")
	private LocalDate invoiceDate;

	@Column(name = "bill_amount")
	private Double billAmount;

	@Column(name = "invoice_status")
	private String invoiceStatus;

	@ManyToOne
	@JoinColumn(name = "gas_booking_id", referencedColumnName = "gas_booking_id")
	private GasBooking booking;

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public GasBooking getBooking() {
		return booking;
	}

	public void setBooking(GasBooking booking) {
		this.booking = booking;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

}
