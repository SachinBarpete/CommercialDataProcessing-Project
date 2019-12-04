package com.bridgelabz.cdp.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.bridgelabz.cdp.model.CompanyShare;
import com.bridgelabz.cdp.model.CustomerInfo;
import com.bridgelabz.cdp.model.Transaction;

public class Connection {
	static ObjectMapper mapper = new ObjectMapper();

	// -----------customer connection ------------
	/**
	 * @param customerList
	 * @purpose read customer JSON file
	 * @return customer list
	 */
	public static List readCustomerFile(List<CustomerInfo> customerList) {

		try {
			customerList = mapper.readValue(new File("customerInfo.json"), new TypeReference<List<CustomerInfo>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	/**
	 * @param customerList
	 * @purpose write customer JSON file
	 */
	public static void writeCustomerFile(List<CustomerInfo> customerList) {
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(new File("customerInfo.json"), customerList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// -----------company connection ------------
	/**
	 * @param companyList
	 * @purpose read company JSON file
	 * @return company list
	 */
	public static List readCompanyFile(List<CompanyShare> companyList) {

		try {
			companyList = mapper.readValue(new File("companyShare.json"), new TypeReference<List<CompanyShare>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return companyList;
	}

	/**
	 * @param companyList
	 * @purpose write company JSON file
	 */
	public static void writeCompanyFile(List<CompanyShare> companyList) {
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(new File("companyShare.json"), companyList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -----------transaction connection ------------
	/**
	 * @param transactionList
	 * @purpose read transaction JSON file
	 * @return transaction list
	 */
	public static List readTransactionFile(List<Transaction> transactionList) {

		try {
			transactionList = mapper.readValue(new File("transaction.json"), new TypeReference<List<Transaction>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return transactionList;
	}

	/**
	 * @param transactionList
	 * @purpose write transaction JSON file
	 */
	public static void writeTransactionFile(List<Transaction> transactionList) {
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(new File("transaction.json"), transactionList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param companyList
	 * @param customerList
	 * @param transactionList
	 * @purpose Print report
	 */
	public static void printReport(List<CompanyShare> companyList, List<CustomerInfo> customerList,
			List<Transaction> transactionList) {
		try {
			String companyReport = mapper.writeValueAsString(companyList);
			String customerReport = mapper.writeValueAsString(customerList);
			String transactionReport = mapper.writeValueAsString(transactionList);
			System.out.println("customer report : \n" + customerReport + "\n");
			System.out.println("company report : \n" + companyReport + "\n");
			System.out.println("transaction report : \n" + transactionReport + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
