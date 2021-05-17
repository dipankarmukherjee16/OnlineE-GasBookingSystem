package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.SurrenderCylinder;

@Repository
public interface ISurrenderCylinderDao extends JpaRepository<SurrenderCylinder, Integer>{

}
