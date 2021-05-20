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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "egas_customer")
public class Customer {
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq1")
	@SequenceGenerator(name = "seq1", sequenceName = "egas_seq1", allocationSize = 1)
	private Integer customerId;

	@Column(name = "user_name", length = 25, nullable = false, unique = true)
	private String userName;

	@Column(name = "mob_number", length = 10, nullable = false, unique = true)
	private String mobileNumber;

	@Column(name = "email", length = 20, nullable = false, unique = true)
	private String email;

	@Column(name = "aadhar_card", length = 12, unique = true)
	private String aadharCard;

	@Column(name = "address", nullable = false, length = 50)
	private String address;

	@Column(name = "city", nullable = false, length = 20)
	private String city;

	@Column(name = "connection_status", nullable = false, length = 10)
	private String connectionStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cylinder_type_id", referencedColumnName = "cylinder_type_id")
	private Cylinder cylinder = new Cylinder();

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private Set<GasBooking> gasBookings;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public String getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(String connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public Cylinder getCylinder() {
		return cylinder;
	}

	public void setCylinder(Cylinder cylinder) {
		this.cylinder = cylinder;
	}

	public Set<GasBooking> getGasBookings() {
		return gasBookings;
	}

	public void setGasBookings(Set<GasBooking> gasBookings) {
		this.gasBookings = gasBookings;
	}

	public Customer(Integer customerId, String userName, String mobileNumber, String email, String aadharCard,
			String address, String city, String connectionStatus) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.aadharCard = aadharCard;
		this.address = address;
		this.city = city;
		this.connectionStatus = connectionStatus;

	}

	public Customer() {
		super();
	}

}
