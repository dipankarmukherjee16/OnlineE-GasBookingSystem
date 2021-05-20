package com.cg.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cg.util.CgUtil;

public class CylinderDto {

	private Integer cylinderTypeId;

	@NotNull(message = CgUtil.WEIGHT_REQUIRED)
	private Float weight;

	@NotBlank(message = CgUtil.CYLIDERTYPE_REQUIRED)
	private String cylinderType;

	public CylinderDto() {
		super();
	}

	public CylinderDto(Integer cylinderTypeId, Float weight, String cylinderType) {
		super();
		this.cylinderTypeId = cylinderTypeId;
		this.weight = weight;
		this.cylinderType = cylinderType;

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

	public String getCylinderType() {
		return cylinderType;
	}

	public void setCylinderType(String cylinderType) {
		this.cylinderType = cylinderType;
	}

}
