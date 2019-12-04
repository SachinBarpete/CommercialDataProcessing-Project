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

/**
 * @author Sachin Barpete
 */
public class StockMaintain {
	static Scanner sc = new Scanner(System.in);

	/**
	 * @param customerList
	 * @param companyList
	 * @param transactionList
	 * @purpose for buy the shares
	 */
	public static void buy(List<CustomerInfo> customerList, List<CompanyShare> companyList,
			List<Transaction> transactionList) {
		CompanyShare cs = new CompanyShare();
		Transaction t = new Transaction();
		System.out.println("Enter your symbol/Id");
		int symbol = sc.nextInt();
		System.out.println("Enter your name");
		String buyerName = sc.next();
		String buyer = Integer.toString(symbol) + buyerName;
		boolean flag1 = false;
		for (int i = 0; i < customerList.size(); i++) {
			if (symbol == (customerList.get(i).getCustomerSymbol())
					&& buyerName.equals(customerList.get(i).getCustomerName())) {
				flag1 = true;
				System.out.println("Enter amount");
				int amount = sc.nextInt();
				if (amount < customerList.get(i).getAmmount()) {
					System.out.println("Enter company symbol");
					String cSymbol = sc.next();
					boolean flag2 = false;
					for (int j = 0; j < companyList.size(); j++) {
						if (cSymbol.equals(companyList.get(j).getCompanySymbol())) {
							flag2 = true;
							cs = companyList.get(j);
							String seller = cs.getCompanySymbol();
							int share = (amount / companyList.get(j).getCompanySharePrice());
							if (share < companyList.get(j).getCompanyShare()) {
								int remainingShare = companyList.get(j).getCompanyShare() - share;
								companyList.get(j).setCompanyShare(remainingShare);
								int totalShare = share + customerList.get(i).getCustomerShare();
								customerList.get(i).setCustomerShare(totalShare);
								int transactionAmmount = share * cs.getCompanySharePrice();
								double updateTotalValue = companyList.get(j).getCompanyTotalValue()
										- transactionAmmount;
								companyList.get(j).setCompanyTotalValue(updateTotalValue);
								int ammount = customerList.get(i).getAmmount() - transactionAmmount;
								customerList.get(i).setAmmount(ammount);
								t.setTransactionId(randomValue());
								t.setBuyer(buyer);
								t.setSeller(seller);
								t.setTransactionAmmount(transactionAmmount);
								t.setDate(date());

								transactionList.add(t);
								System.out.println("transaction successfull");
							} else
								System.out.println(
										"company shares available only : " + companyList.get(j).getCompanyShare());

						}

					}
					if (flag2 == false)
						System.out.println("Enter valid company symbol");
				} else
					System.out.println("your account balance is low : " + customerList.get(i).getAmmount());
			}

		}
		if (flag1 == false) {
			System.out.println("first creat your account");
			customerList = Connection.readCustomerFile(customerList);
			StockMaintain.addCustomer(customerList);
			Connection.writeCustomerFile(customerList);
		}

	}

	/**
	 * @param customerList
	 * @param companyList
	 * @param transactionList
	 * @purpose for shell the share
	 */
	public static void sell(List<CustomerInfo> customerList, List<CompanyShare> companyList,
			List<Transaction> transactionList) {

		System.out.println("Enter you symbol/Id");
		int id = sc.nextInt();
		System.out.println("Enter your name");
		String seller = sc.next();
		boolean flag1 = false;
		for (int i = 0; i < customerList.size(); i++) {
			if (id == customerList.get(i).getCustomerSymbol() && seller.equals(customerList.get(i).getCustomerName())) {
				flag1 = true;
				System.out.println("Enter the share you want to sell");
				int share = sc.nextInt();
				if (share <= customerList.get(i).getCustomerShare()) {
					System.out.println("Enter company Symbol/name");
					String buyer = sc.next();
					boolean flag2 = false;
					for (int j = 0; j < companyList.size(); j++) {
						if (buyer.equals(companyList.get(j).getCompanySymbol())) {
							flag2 = true;
							int updatedCustomerShare = customerList.get(i).getCustomerShare() - share;
							customerList.get(i).setCustomerShare(updatedCustomerShare);

							int transactionAmount = share * companyList.get(j).getSharePrice();
							int updatedCustomerAmount = customerList.get(i).getAmmount() + (transactionAmount);
							customerList.get(i).setAmmount(updatedCustomerAmount);

							int updatedCompanyShare = companyList.get(j).getCompanyShare() + share;
							companyList.get(j).setCompanyShare(updatedCompanyShare);

							double updateTotalValue = companyList.get(j).getCompanyTotalValue() - transactionAmount;
							companyList.get(j).setCompanyTotalValue(updateTotalValue);
							Transaction t = new Transaction();
							t.setTransactionId(randomValue());
							t.setBuyer(buyer);
							t.setSeller(seller);
							t.setTransactionAmmount(transactionAmount);
							t.setDate(date());
							transactionList.add(t);
							System.out.println("transaction successfull");
						}
					}
					if (flag2 == false)
						System.out.println("Company doesn't exist enter valid company name");
				} else
					System.out.println("you have only " + customerList.get(i).getCustomerShare() + " share");
			}
		}

		if (flag1 == false) {
			System.out.println("first creat your account");
			customerList = Connection.readCustomerFile(customerList);
			StockMaintain.addCustomer(customerList);
			Connection.writeCustomerFile(customerList);
		}
	}

	/**
	 * @param customerList
	 * @purpose for add customer details
	 */
	public static void addCustomer(List<CustomerInfo> customerList) {
		CustomerInfo c = new CustomerInfo();
		System.out.println("Enter customer symbol/Id");
		c.setCustomerSymbol(sc.nextInt());
		System.out.println("Enter customer name");
		c.setCustomerName(sc.next());
		System.out.println("Enter customer share");
		c.setCustomerShare(sc.nextInt());
		System.out.println("Enter customer ammount");
		c.setAmmount(sc.nextInt());
		customerList.add(c);
		System.out.println("added customer successfully");
	}

	/**
	 * @param companyList
	 * @purpose for add company
	 */
	public static void addCompany(List<CompanyShare> companyList) {
		CompanyShare cs = new CompanyShare();
		System.out.println("Enter company symbol/name");
		cs.setCompanySymbol(sc.next());
		cs.setCompanyShare(100);
		System.out.println("Enter share price");
		cs.setSharePrice(sc.nextInt());
		double companyTotalValue = cs.getCompanyShare() * cs.getSharePrice();
		cs.setCompanyTotalValue(companyTotalValue);
		companyList.add(cs);
		System.out.println("added company successfully");

	}

	/**
	 * @return random generated value
	 */
	public static int randomValue() {
		Random rand = new Random();
		int random = rand.nextInt(100000);
		return random;
	}

	/**
	 * @return today date
	 */
	public static String date() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String todayDate = formatter.format(date);
		return todayDate;
	}
}
