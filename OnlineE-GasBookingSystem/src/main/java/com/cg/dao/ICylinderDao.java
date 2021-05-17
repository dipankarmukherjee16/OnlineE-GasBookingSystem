package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Cylinder;

@Repository
public interface ICylinderDao extends JpaRepository<Cylinder, Integer>{

}
