package com.cg.service;

import com.cg.entity.SurrenderCylinder;

public interface ISurrenderCylinderService {
	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder surrenderCylinder);
	public SurrenderCylinder updateSurrenderCylinder(SurrenderCylinder surrenderCylinder);
	public SurrenderCylinder deleteSurrenderCylinder(int surrenderCylinderId);
	public int countSurrenderedCylinders();
}