package com.bridgelabz.cdp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bridgelabz.cdp.model.CompanyShare;
import com.bridgelabz.cdp.model.CustomerInfo;
import com.bridgelabz.cdp.model.Transaction;
import com.bridgelabz.cdp.repository.Connection;

public class StockMaintain {
	static Scanner sc = new Scanner(System.in);

	@SuppressWarnings("unchecked")
	public static void buy(List<CustomerInfo> cList, List<CompanyShare> csList, List<Transaction> tList) {
		CompanyShare cs = new CompanyShare();
		Transaction t = new Transaction();
		System.out.println("Enter your symbol/Id");
		int symbol = sc.nextInt();
		System.out.println("Enter your name");
		String buyerName = sc.next();
		String buyer = Integer.toString(symbol) + buyerName;
		boolean flag1 = false;
		for (int i = 0; i < cList.size(); i++) {
			if (symbol == (cList.get(i).getCustomerSymbol()) && buyerName.equals(cList.get(i).getCustomerName())) {
				flag1 = true;
				System.out.println("Enter amount");
				int amount = sc.nextInt();
				if (amount < cList.get(i).getAmmount()) {
					System.out.println("Enter company symbol");
					String cSymbol = sc.next();
					boolean flag2 = false;
					for (int j = 0; j < csList.size(); j++) {
						if (cSymbol.equals(csList.get(j).getCompanySymbol())) {
							flag2 = true;
							cs = csList.get(j);
							String seller = cs.getCompanySymbol();
							int share = (amount / csList.get(j).getCompanySharePrice());
							if (share < csList.get(j).getCompanyShare()) {
								int remainingShare = csList.get(j).getCompanyShare() - share;
								csList.get(j).setCompanyShare(remainingShare);
								int totalShare = share + cList.get(i).getCustomerShare();
								cList.get(i).setCustomerShare(totalShare);
								int transactionAmmount = share * cs.getCompanySharePrice();
								double updateTotalValue = csList.get(j).getCompanyTotalValue() - transactionAmmount;
								csList.get(j).setCompanyTotalValue(updateTotalValue);
								int ammount = cList.get(i).getAmmount() - transactionAmmount;
								cList.get(i).setAmmount(ammount);
								t.setTransactionId(randomValue());
								t.setBuyer(buyer);
								t.setSeller(seller);
								t.setTransactionAmmount(transactionAmmount);
								t.setDate(date());

								tList.add(t);
								System.out.println("transaction successfull");
							} else
								System.out
										.println("company shares available only : " + csList.get(j).getCompanyShare());

						}

					}
					if (flag2 == false)
						System.out.println("Enter valid company symbol");
				} else
					System.out.println("your account balance is low : " + cList.get(i).getAmmount());
			}

		}
		if (flag1 == false) {
			System.out.println("first creat your account");
			cList = Connection.readCustomerFile(cList);
			StockMaintain.addCustomer(cList);
			Connection.writeCustomerFile(cList);
		}

	}

	@SuppressWarnings("unchecked")
	public static void sell(List<CustomerInfo> cList, List<CompanyShare> csList, List<Transaction> tList) {

		System.out.println("Enter you symbol/Id");
		int id = sc.nextInt();
		System.out.println("Enter your name");
		String seller = sc.next();
		boolean flag1 = false;
		for (int i = 0; i < cList.size(); i++) {
			if (id == cList.get(i).getCustomerSymbol() && seller.equals(cList.get(i).getCustomerName())) {
				flag1 = true;
				System.out.println("Enter the share you want to sell");
				int share = sc.nextInt();
				if(share <=cList.get(i).getCustomerShare()) {
					System.out.println("Enter company Symbol/name");
					String buyer = sc.next();
					boolean flag2 = false;
					for(int j=0;j<csList.size();j++) {
						if(buyer.equals(csList.get(j).getCompanySymbol())) {
							flag2 =true;
							int updatedCustomerShare = cList.get(i).getCustomerShare()-share;
							cList.get(i).setCustomerShare(updatedCustomerShare);
							
							int transactionAmount = share*csList.get(j).getSharePrice();
							int updatedCustomerAmount = cList.get(i).getAmmount()+(transactionAmount);
							cList.get(i).setAmmount(updatedCustomerAmount);
							
							int updatedCompanyShare = csList.get(j).getCompanyShare()+share;
							csList.get(j).setCompanyShare(updatedCompanyShare);
							
							double updateTotalValue = csList.get(j).getCompanyTotalValue() - transactionAmount;
							csList.get(j).setCompanyTotalValue(updateTotalValue);
							Transaction t = new Transaction();
							t.setTransactionId(randomValue());
							t.setBuyer(buyer);
							t.setSeller(seller);
							t.setTransactionAmmount(transactionAmount);
							t.setDate(date());	
							tList.add(t);
							System.out.println("transaction successfull");
						}			
					}
					if(flag2 == false)
						System.out.println("Company doesn't exist enter valid company name");
				}else
					System.out.println("you have only "+cList.get(i).getCustomerShare()+" share");
			}
		}

		if (flag1 == false) {
			System.out.println("first creat your account");
			cList = Connection.readCustomerFile(cList);
			StockMaintain.addCustomer(cList);
			Connection.writeCustomerFile(cList);
		}
	}

	public static void addCustomer(List<CustomerInfo> cList) {
		CustomerInfo c = new CustomerInfo();
		System.out.println("Enter customer symbol/Id");
		c.setCustomerSymbol(sc.nextInt());
		System.out.println("Enter customer name");
		c.setCustomerName(sc.next());
		System.out.println("Enter customer share");
		c.setCustomerShare(sc.nextInt());
		System.out.println("Enter customer ammount");
		c.setAmmount(sc.nextInt());
		cList.add(c);
		System.out.println("added customer successfully");
	}

	public static void addCompany(List<CompanyShare> csList) {
		CompanyShare cs = new CompanyShare();
		System.out.println("Enter company symbol/name");
		cs.setCompanySymbol(sc.next());
		cs.setCompanyShare(100);
		System.out.println("Enter share price");
		cs.setSharePrice(sc.nextInt());
		double companyTotalValue = cs.getCompanyShare() * cs.getSharePrice();
		cs.setCompanyTotalValue(companyTotalValue);
		csList.add(cs);
		System.out.println("added company successfully");

	}

	public static int randomValue() {
		Random rand = new Random();
		int random = rand.nextInt(100000);
		return random;
	}

	public static String date() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String todayDate = formatter.format(date);
		return todayDate;
	}
}
