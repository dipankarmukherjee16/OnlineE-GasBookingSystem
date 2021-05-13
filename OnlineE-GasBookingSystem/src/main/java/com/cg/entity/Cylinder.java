package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="egas_cylinder_connection")
public class Cylinder {
	@Id
	@Column(name="cylinder_id")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "seq2")
	@SequenceGenerator(name = "seq2", sequenceName = "egas_seq2", allocationSize = 1 )
	private int cylinderId;
	
	@Column(name = "type", nullable = false)
	private String type;
	
	@Column(name = "weight")
	private float weight;
	
	@Column(name = "strap_color", nullable = false)
	private String strapColor;
	
	@Column(name = "price")
	private float price;
		

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getStrapColor() {
		return strapColor;
	}

	public void setStrapColor(String strapColor) {
		this.strapColor = strapColor;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}