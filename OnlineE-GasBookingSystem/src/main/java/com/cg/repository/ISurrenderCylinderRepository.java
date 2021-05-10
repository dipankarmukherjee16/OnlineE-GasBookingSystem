package com.cg.repository;

import com.cg.entity.SurrenderCylinder;
import com.cg.exception.SurrenderCylinderNotFoundException;

public interface ISurrenderCylinderRepository {
	public SurrenderCylinder insertSurrenderCylinder(SurrenderCylinder surrenderCylinder) throws SurrenderCylinderNotFoundException;
	public SurrenderCylinder updateSurrenderCylinder(SurrenderCylinder surrenderCylinder) throws SurrenderCylinderNotFoundException;
	public SurrenderCylinder deleteSurrenderCylinder(int surrenderCylinderId);
	public int countSurrenderedCylinders();
}