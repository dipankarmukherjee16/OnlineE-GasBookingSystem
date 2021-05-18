package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.SurrenderCylinder;

@Repository
public interface ISurrenderCylinderDao extends JpaRepository<SurrenderCylinder, Integer>{

	
	@Query("from SurrenderCylinder sc where sc.surrenderDate.getYear()=:year")
	public List<SurrenderCylinder> viewSurrenderedCustomerByYear(@Param("year") int year);
	
	
}
