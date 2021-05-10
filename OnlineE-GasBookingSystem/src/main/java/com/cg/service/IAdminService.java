package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.entity.Admin;
import com.cg.entity.GasBooking;

public interface IAdminService {
	public Admin insertAdmin(Admin admin);
	public Admin updateAdmin(Admin admin);
	public Admin deleteAdmin(int adminId);
	public List<GasBooking> getAllBookings(int customerId);
	public List<GasBooking> getAllBookingsForDays(int customerId, LocalDate fromDate, LocalDate toDate);
}