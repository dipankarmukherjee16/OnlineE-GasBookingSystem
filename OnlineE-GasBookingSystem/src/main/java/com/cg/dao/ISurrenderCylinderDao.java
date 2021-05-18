package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;
import com.cg.entity.SurrenderCylinder;

@Repository
public interface ISurrenderCylinderDao extends JpaRepository<SurrenderCylinder, Integer>{

	
	@Query("from SurrenderCylinder sc where sc.surrenderDate.getYear()=:year.getYear()")
	public List<SurrenderCylinder> viewSurrenderedCustomerByYear(@Param("year") LocalDate year);
	
	
}