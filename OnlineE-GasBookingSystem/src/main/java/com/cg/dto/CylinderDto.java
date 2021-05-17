package com.cg.dto;

public class CylinderDto {

	private Integer cylinderTypeId;
	private Float weight;
	private String cylinderType;
	private Float price;
	
	public CylinderDto()
	{
		super();
	}
	
	
	
	public CylinderDto(Integer cylinderTypeId, Float weight, String cylinderType, Float price) {
		super();
		this.cylinderTypeId = cylinderTypeId;
		this.weight = weight;
		this.cylinderType = cylinderType;
		this.price = price;
	}



	public Integer getCylinderId() {
		return cylinderTypeId;
	}
	public void setCylinderId(Integer cylinderTypeId) {
		this.cylinderTypeId = cylinderTypeId;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCylinderType() {
		return cylinderType;
	}

	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}
	
}
