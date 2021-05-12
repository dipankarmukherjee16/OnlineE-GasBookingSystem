package com.cg.entity;

import java.time.LocalDate;

public class GasBooking {
	private int gasBookingId;
	private int customerId;
	private LocalDate bookingDate;
	private boolean status;
	private float bill;
	private LocalDate dispatchDate;
}