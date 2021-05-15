package com.cg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.GasBooking;
import com.cg.exception.GasBookingNotFoundException;

public interface IGasBookingDao extends JpaRepository<GasBooking, Integer>{
	
}