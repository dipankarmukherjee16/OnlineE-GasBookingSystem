package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.entity.GasBooking;

@Repository
public interface IGasBookingDao extends JpaRepository<GasBooking, Integer> {

	@Query("from GasBooking gb inner join fetch gb.customer cs where cs.customerId=:custId")
	public List<GasBooking> viewBookingDetailsByCustomerId(@Param("custId") int customerId);

	@Query("from GasBooking gb inner join fetch gb.customer cs where cs.customerId=:custId and extract(year from gb.bookingDate)=:year")
	public List<GasBooking> noOfCylindersBookedInAYear(@Param("year") int year, @Param("custId") int customerId);
	
	@Query("select gb from GasBooking gb inner join gb.customer cs where cs.customerId=:custId and extract(year from gb.bookingDate)=extract(year from sysdate)")
	public List<GasBooking> checkBookingLimit(@Param("custId") int customerId);
}
