package com.cg.repository;

import com.cg.entity.Bank;

public interface IBankRepository{
	public Bank insertBank(Bank bank);
	public Bank updateBank(Bank bank);
	public Bank deleteBank(Bank bank);
}