package com.cg.repository;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Admin;
import com.cg.entity.GasBooking;
import com.cg.exception.AdminNotFoundException;

public interface IAdminRepository {
	public Admin insertAdmin(Admin admin);
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException;
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException;
	public List<GasBooking> getAllBookings(int customerId);
	public List<GasBooking> getAllBookingsForDays(int customerId, LocalDate fromDate, LocalDate toDate);
}