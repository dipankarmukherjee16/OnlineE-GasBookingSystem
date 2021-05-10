package com.cg.service;

import com.cg.entity.Bank;

public interface IBankService {
	public Bank insertBank(Bank bank);
	public Bank updateBank(Bank bank);
	public Bank deleteBank(Bank bank);
}