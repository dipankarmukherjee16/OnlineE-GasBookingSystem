package com.cg.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Cylinder;
import com.cg.exception.CylinderNotFoundException;
import com.cg.service.ICylinderService;

@RestController
public class CylinderRestController {
	
	@Autowired
	private ICylinderService cylinderService;
	
	@GetMapping("viewallcylinder")
	public List<Cylinder> viewAllCylinder()throws CylinderNotFoundException{
		return cylinderService.viewAllCylinder();
	}

}
