package com.cg.service;

import java.util.List;

import com.cg.dto.CylinderDto;
import com.cg.entity.Cylinder;
import com.cg.exception.CylinderNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;

public interface ICylinderService {
	public Integer addCylinder(CylinderDto cylinderDto)throws CylinderTypeMismatchException;
	public List<Cylinder> viewAllCylinder()throws CylinderNotFoundException;

}
