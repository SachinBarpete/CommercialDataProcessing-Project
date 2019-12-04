package com.bridgelabz.cdp.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.bridgelabz.cdp.model.CompanyShare;
import com.bridgelabz.cdp.model.CustomerInfo;
import com.bridgelabz.cdp.model.Transaction;
import com.bridgelabz.cdp.repository.Connection;
import com.bridgelabz.cdp.service.StockMaintain;

/**
 * @author Sachin Barpete
 * @purpose Controller class for commercial data processing -stock Account
 */
public class StockAccount {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		List<CompanyShare> companyList = new LinkedList<>();
		List<CustomerInfo> customerList = new LinkedList<>();
		List<Transaction> transactionList = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println(" Enter" + "\n 1 for add company" + "\n 2 for add customer" + "\n 3 for buy shares"
				+ "\n 4 for sell shares" + "\n 5 for print report");

		int ch = sc.nextInt();
		switch (ch) {
		case 1:
			companyList = Connection.readCompanyFile(companyList);
			StockMaintain.addCompany(companyList);
			Connection.writeCompanyFile(companyList);
			break;
		case 2:
			customerList = Connection.readCustomerFile(customerList);
			StockMaintain.addCustomer(customerList);
			Connection.writeCustomerFile(customerList);
			break;
		case 3:
			companyList = Connection.readCompanyFile(companyList);
			customerList = Connection.readCustomerFile(customerList);
			transactionList = Connection.readTransactionFile(transactionList);
			StockMaintain.buy(customerList, companyList, transactionList);
			Connection.writeCompanyFile(companyList);
			Connection.writeCustomerFile(customerList);
			Connection.writeTransactionFile(transactionList);
			break;
		case 4:
			companyList = Connection.readCompanyFile(companyList);
			customerList = Connection.readCustomerFile(customerList);
			transactionList = Connection.readTransactionFile(transactionList);
			StockMaintain.sell(customerList, companyList, transactionList);
			Connection.writeCompanyFile(companyList);
			Connection.writeCustomerFile(customerList);
			Connection.writeTransactionFile(transactionList);
			break;
		case 5:
			companyList = Connection.readCompanyFile(companyList);
			customerList = Connection.readCustomerFile(customerList);
			transactionList = Connection.readTransactionFile(transactionList);
			Connection.printReport(companyList, customerList, transactionList);
			break;
		default:
			System.out.println("Enter valid selection");
			sc.close();
		}
	}
}
