package com.cg.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.ICylinderDao;
import com.cg.dto.CylinderDto;
import com.cg.entity.Cylinder;
import com.cg.exception.CylinderNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.exception.ValidateException;
import com.cg.util.CgUtil;


/*********************************************************************************************
 *          @author: Debabrata Deb         
 *          Description: It is a service class that provides the services for adding 
                         a new cylinder type and viewing all the available cylinder types                            
 *          Version: 1.0         
 *          Created Date: 19-MAY-2021
 **********************************************************************************************/



@Service("cylinderservice")
@Transactional
public class CylinderServiceImpl implements ICylinderService {

	@Autowired
	private ICylinderDao cylinderDao;

	
	/************************************************************************************
	 * 	Method: addCylinder
     * 	Description: To add a new cylinder type along with its details
	 * 	@returns Integer  - cylinder id, if cylinder type exists otherwise throws CylinderTypeMismatchException
	 * 	@throws CylinderTypeMismatchException - It is raised if wrong cylinder type supplied  
     *	Created By - Debabrata Deb
     *	Created Date - 19-MAY-2021                           
	
	 ************************************************************************************/

	
	
	@Override
	@Transactional
	public Integer addCylinder(CylinderDto cylinderDto) throws ValidateException {

		Cylinder cylinder = new Cylinder();

		if(cylinderDto.getWeight()==null)
			throw new ValidateException();
		
		cylinder.setCylinderType(cylinderDto.getCylinderType());
		cylinder.setWeight(cylinderDto.getWeight());

		Cylinder persistedCylinder = cylinderDao.save(cylinder);

		return persistedCylinder.getCylinderTypeId();
	}

	
	/************************************************************************************
	 * 	Method: viewAllCylinder
     * 	Description: To view all cylinder types along with their details
	 * 	@returns List<Cylinder>  - all available cylinder types 
	 *  @throws CylinderNotFoundException - It is raised if no cylinder type is present 
     *	Created By - Debabrata Deb
     *	Created Date - 19-MAY-2021                           
	
	 ************************************************************************************/

	
	
	@Override
	public List<Cylinder> viewAllCylinder() throws CylinderNotFoundException {

		List<Cylinder> lst = cylinderDao.findAll();
		if (lst.isEmpty())
			throw new CylinderNotFoundException(CgUtil.CYLINDERNOTFOUND);
		lst.sort((e1, e2) -> e1.getCylinderType().compareTo(e2.getCylinderType()));

		return lst;
	}

}
