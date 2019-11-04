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
	@SuppressWarnings("rawtypes")
	// read customer JSON file
	public static List readCustomerFile(List<CustomerInfo> cList) {

		try {
			cList = mapper.readValue(new File("customerInfo.json"), new TypeReference<List<CustomerInfo>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cList;
	}

	@SuppressWarnings("deprecation")
	// write customer JSON file
	public static void writeCustomerFile(List<CustomerInfo> cList) {
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(new File("customerInfo.json"), cList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// -----------company connection ------------
	@SuppressWarnings("rawtypes")
	// read company JSON file
	public static List readCompanyFile(List<CompanyShare> csList) {

		try {
			csList = mapper.readValue(new File("companyShare.json"), new TypeReference<List<CompanyShare>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return csList;
	}

	@SuppressWarnings("deprecation")
	// write company JSON file
	public static void writeCompanyFile(List<CompanyShare> csList) {
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(new File("companyShare.json"), csList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -----------transaction connection ------------
	@SuppressWarnings("rawtypes")
	// read transaction JSON file
	public static List readTransactionFile(List<Transaction> tList) {

		try {
			tList = mapper.readValue(new File("transaction.json"), new TypeReference<List<Transaction>>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tList;
	}

	@SuppressWarnings("deprecation")
	// write transaction JSON file
	public static void writeTransactionFile(List<Transaction> tList) {
		try {
			mapper.defaultPrettyPrintingWriter().writeValue(new File("transaction.json"), tList);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ------------Print report------------
	public static void printReport(List<CompanyShare> csList, List<CustomerInfo> cList, List<Transaction> tList) {
		try {
			String csReport = mapper.writeValueAsString(csList);
			String cReport = mapper.writeValueAsString(cList);
			String tReport = mapper.writeValueAsString(tList);
			System.out.println("customer report : \n"+cReport +"\n");
			System.out.println("company report : \n"+csReport+"\n");
			System.out.println("transaction report : \n"+tReport+"\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
