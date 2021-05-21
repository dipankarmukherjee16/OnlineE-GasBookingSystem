package com.cg.cylinderTest;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.ICylinderDao;
import com.cg.entity.Cylinder;
import com.cg.service.CylinderServiceImpl;
import com.cg.service.ICylinderService;

@SpringBootTest
public class TestAddCylinder {

	@Mock
	private ICylinderDao cylinderDao;
	
	@InjectMocks
	private ICylinderService cylinderService=new CylinderServiceImpl();
	
	@BeforeEach
	public void beforeEach() {
		
		Cylinder cylinder=new Cylinder(1,"domestic",14.5f);
		when(cylinderDao.save(any(Cylinder.class))).
		
	}
}
