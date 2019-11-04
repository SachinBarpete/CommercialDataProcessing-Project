package com.bridgelabz.cdp.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.bridgelabz.cdp.model.CompanyShare;
import com.bridgelabz.cdp.model.CustomerInfo;
import com.bridgelabz.cdp.model.Transaction;
import com.bridgelabz.cdp.repository.Connection;
import com.bridgelabz.cdp.service.StockMaintain;

public class StockAccount {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		List<CompanyShare> csList = new LinkedList<>();
		List<CustomerInfo> cList = new LinkedList<>();
		List<Transaction> tList = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println(" Enter" + "\n 1 for add company" + "\n 2 for add customer" + "\n 3 for buy shares"
				+ "\n 4 for sell shares" + "\n 5 for print report");

		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			csList = Connection.readCompanyFile(csList);
			StockMaintain.addCompany(csList);
			Connection.writeCompanyFile(csList);
			break;
		case 2:
			cList = Connection.readCustomerFile(cList);
			StockMaintain.addCustomer(cList);
			Connection.writeCustomerFile(cList);
			break;
		case 3:
			csList = Connection.readCompanyFile(csList);
			cList = Connection.readCustomerFile(cList);
			tList = Connection.readTransactionFile(tList);
			StockMaintain.buy(cList, csList, tList);
			Connection.writeCompanyFile(csList);
			Connection.writeCustomerFile(cList);
			Connection.writeTransactionFile(tList);
			break;
		case 4:
			csList = Connection.readCompanyFile(csList);
			cList = Connection.readCustomerFile(cList);
			tList = Connection.readTransactionFile(tList);
			StockMaintain.sell(cList, csList, tList);
			Connection.writeCompanyFile(csList);
			Connection.writeCustomerFile(cList);
			Connection.writeTransactionFile(tList);
			break;
		case 5:
			csList = Connection.readCompanyFile(csList);
			cList = Connection.readCustomerFile(cList);
			tList = Connection.readTransactionFile(tList);
			Connection.printReport(csList, cList, tList);
			break;
		default:
			System.out.println("Enter valid selection");
			sc.close();
		}
	}
}
