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

@Entity
@Table(name = "egas_surrender_cylinder")
public class SurrenderCylinder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq4")
	@SequenceGenerator(name = "seq4", sequenceName = "egas_seq4", allocationSize = 1)
	@Column(name = "surrender_id")
	private Integer surrenderId;

	@Column(name = "surrender_date")
	private LocalDate surrenderDate;

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;

	public Integer getSurrenderId() {
		return surrenderId;
	}

	public void setSurrenderId(Integer surrenderId) {
		this.surrenderId = surrenderId;
	}

	public LocalDate getSurrenderDate() {
		return surrenderDate;
	}

	public void setSurrenderDate(LocalDate surrenderDate) {
		this.surrenderDate = surrenderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public SurrenderCylinder()
	{
		super();
	}

	public SurrenderCylinder(Integer surrenderId, LocalDate surrenderDate) {
		super();
		this.surrenderId = surrenderId;
		this.surrenderDate = surrenderDate;
	}

	
	
}
