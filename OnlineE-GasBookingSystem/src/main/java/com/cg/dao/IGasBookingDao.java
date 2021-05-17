package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.GasBooking;

@Repository
public interface IGasBookingDao extends JpaRepository<GasBooking, Integer>{
	
}