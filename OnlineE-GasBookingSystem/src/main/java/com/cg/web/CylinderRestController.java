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

@RestController
public class CylinderRestController {

	@Autowired
	private ICylinderService cylinderService;

	@PostMapping("addcylinder")
	public SuccessMessage addCylinder(@Valid @RequestBody CylinderDto cylinderDto, BindingResult br)
			throws CylinderTypeMismatchException, ValidateException {

		if (br.hasErrors()) {
			throw new ValidateException(br.getFieldErrors());
		}

		int cylinderId = cylinderService.addCylinder(cylinderDto);

		return new SuccessMessage(CgUtil.CYLINDER_CREATED + cylinderId);

	}

	@GetMapping("viewallcylinder")
	public List<Cylinder> viewAllCylinder() throws CylinderNotFoundException {
		return cylinderService.viewAllCylinder();
	}

}
