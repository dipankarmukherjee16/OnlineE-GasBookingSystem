package com.cg.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="egas_customer")
public class Customer {
	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq1")
	@SequenceGenerator(name = "seq1", sequenceName = "egas_seq1", allocationSize = 1 )
	private int customerId;
	
	@Column(name = "user_name", length = 25, nullable = false, unique = true)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "mob_number", nullable = false,unique = true)
	private String mobileNumber;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "pan",nullable = false, unique = true)
	private String pan;

	
	@Column(name = "no_of_cylinders_left")
	private int noOfCylinderLeft;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	
	public int getNoOfCylinderLeft() {
		return noOfCylinderLeft;
	}

	public void setNoOfCylinderLeft(int noOfCylinderLeft) {
		this.noOfCylinderLeft = noOfCylinderLeft;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cId", referencedColumnName = "cylinder_id")
	private Cylinder cylinder= new Cylinder();
	

	public Cylinder getCylinder() {
		return cylinder;
	}

	public void setCylinder(Cylinder cylinder) {
		this.cylinder = cylinder;
	}
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private Set<GasBooking> gasBookings;
	
	

	public Set<GasBooking> getGasBookings() {
		return gasBookings;
	}

	public void setGasBookings(Set<GasBooking> gasBookings) {
		this.gasBookings = gasBookings;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
