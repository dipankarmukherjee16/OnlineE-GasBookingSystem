package com.cg.dto;

public class CylinderDto {

	private Integer cylinderId;
	private Float weight;
	private Float price;
	
	public CylinderDto()
	{
		
	}
	
	public CylinderDto(Integer cylinderId, Float weight, Float price) {
		super();
		this.cylinderId = cylinderId;
		this.weight = weight;
		this.price = price;
	}
	
	public Integer getCylinderId() {
		return cylinderId;
	}
	public void setCylinderId(Integer cylinderId) {
		this.cylinderId = cylinderId;
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
	
}
