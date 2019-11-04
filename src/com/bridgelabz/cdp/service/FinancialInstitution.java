package com.bridgelabz.cdp.service;

public interface FinancialInstitution {
	
	double valueOf();
	void buy(int amount, String symbol);
	void sell(int amount, String symbol);
	void save(String filename);
	void printReport();
	
}
