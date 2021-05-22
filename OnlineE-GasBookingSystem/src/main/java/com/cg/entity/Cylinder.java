package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "egas_cylinder_connection")
public class Cylinder {
	@Id
	@Column(name = "cylinder_type_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq2")
	@SequenceGenerator(name = "seq2", sequenceName = "egas_seq2", allocationSize = 1)
	private Integer cylinderTypeId;

	@Column(name = "cylinder_type", length = 10, nullable = false)
	private String cylinderType;

	@Column(name = "weight")
	private Float weight;

	public Integer getCylinderTypeId() {
		return cylinderTypeId;
	}

	public void setCylinderTypeId(Integer cylinderTypeId) {
		this.cylinderTypeId = cylinderTypeId;
	}

	public String getCylinderType() {
		return cylinderType;
	}

	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	
	public Cylinder() {
		super();
	}

	public Cylinder(Integer cylinderTypeId, String cylinderType, Float weight) {
		super();
		this.cylinderTypeId = cylinderTypeId;
		this.cylinderType = cylinderType;
		this.weight = weight;
	}
	
	

}
