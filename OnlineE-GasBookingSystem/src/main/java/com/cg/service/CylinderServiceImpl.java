package com.cg.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.dao.ICylinderDao;
import com.cg.dto.CylinderDto;
import com.cg.entity.Cylinder;
import com.cg.exception.CylinderNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;

@Transactional
public class CylinderServiceImpl implements ICylinderService{
	
	@Autowired
	private ICylinderDao cylinderDao;

	@Override
	@Transactional
	public Integer addCylinder(CylinderDto cylinderDto) throws CylinderTypeMismatchException {
		
		Cylinder cylinder=new Cylinder();
		
		cylinder.setCylinderType(cylinderDto.getCylinderType());
		cylinder.setWeight(cylinderDto.getWeight());
		
		Cylinder persistedCylinder=cylinderDao.save(cylinder);
		
		return persistedCylinder.getCylinderTypeId();
	}

	@Override
	public List<Cylinder> viewAllCylinder() throws CylinderNotFoundException {
		
		List<Cylinder> lst=cylinderDao.findAll();
		if(lst.isEmpty())
			throw new CylinderNotFoundException("No cylinder found");
		lst.sort((e1,e2)->e1.getCylinderType().compareTo(e2.getCylinderType()));
		
		return lst;
	}

}
