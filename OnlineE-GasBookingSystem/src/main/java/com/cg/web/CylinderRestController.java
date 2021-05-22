package com.cg.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CylinderDto;
import com.cg.dto.SuccessMessage;
import com.cg.entity.Cylinder;
import com.cg.exception.CylinderNotFoundException;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.exception.ValidateException;
import com.cg.service.ICylinderService;
import com.cg.util.CgUtil;


/*********************************************************************************************
 *          @author: Debabrata Deb         
 *          Description: It is a controller class that provides the services for adding 
                         a new cylinder type and viewing all the available cylinder types                            
 *          Version: 1.0         
 *          Created Date: 20-MAY-2021
 **********************************************************************************************/



@RestController
public class CylinderRestController {

	@Autowired
	private ICylinderService cylinderService;
	
	/************************************************************************************
	 * 	Method: addCylinder
     * 	Description: To add a new cylinder type along with its details
	 * 	@returns SuccessMessage  - cylinder id, if added otherwise throws ValidateException
	 * 	@throws ValidateException - It is raised due to invalid data supplied  
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/


	@PostMapping("addcylinder")
	public SuccessMessage addCylinder(@Valid @RequestBody CylinderDto cylinderDto, BindingResult br)
			throws CylinderTypeMismatchException, ValidateException {

		if (br.hasErrors()) {
			throw new ValidateException(br.getFieldErrors());
		}

		int cylinderId = cylinderService.addCylinder(cylinderDto);

		return new SuccessMessage(CgUtil.CYLINDER_CREATED + cylinderId);

	}
	
	
	/************************************************************************************
	 * 	Method: viewAllCylinder
     * 	Description: To view all cylinder types along with their details
	 * 	@returns List<Cylinder>  - all available cylinder types  
     *	Created By - Debabrata Deb
     *	Created Date - 20-MAY-2021                           
	
	 ************************************************************************************/

	

	@GetMapping("viewallcylinder")
	public List<Cylinder> viewAllCylinder() throws CylinderNotFoundException {
		return cylinderService.viewAllCylinder();
	}

}
