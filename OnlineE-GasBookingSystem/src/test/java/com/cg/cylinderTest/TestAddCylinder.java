package com.cg.cylinderTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.dao.ICylinderDao;
import com.cg.dto.CylinderDto;
import com.cg.entity.Cylinder;
import com.cg.exception.CylinderTypeMismatchException;
import com.cg.service.CylinderServiceImpl;
import com.cg.service.ICylinderService;

@SpringBootTest
public class TestAddCylinder {

	@Mock
	private ICylinderDao cylinderDao;

	@InjectMocks
	private ICylinderService cylinderService = new CylinderServiceImpl();

	@BeforeEach
	public void beforeEach() {

		Cylinder cylinder = new Cylinder(1, "domestic", 14.5f);
		when(cylinderDao.save(any(Cylinder.class))).thenReturn(cylinder);

	}
	
	@Test
	@DisplayName(value = "test add cylinder of domestic type")
	public void testAddCylinder1() throws CylinderTypeMismatchException{
		CylinderDto cylinderDto= new CylinderDto(1,14.2f, "domestic");
		assertNotNull(cylinderService.addCylinder(cylinderDto));
	}
}
