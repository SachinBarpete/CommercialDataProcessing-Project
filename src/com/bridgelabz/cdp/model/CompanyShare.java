package com.bridgelabz.cdp.model;
/**
 * @author Sachin Barpete
 * @purpose object for companyShare.json file
 */
public class CompanyShare {
	
	private String companySymbol;
	private int companyShare;
	private int companySharePrice;
	private double companyTotalValue;

	public String getCompanySymbol() {
		return companySymbol;
	}

	public void setCompanySymbol(String companySymbol) {
		this.companySymbol = companySymbol;
	}

	public int getCompanySharePrice() {
		return companySharePrice;
	}

	public void setCompanySharePrice(int companySharePrice) {
		this.companySharePrice = companySharePrice;
	}

	public int getCompanyShare() {
		return companyShare;
	}

	public void setCompanyShare(int companyShare) {
		this.companyShare = companyShare;
	}

	public int getSharePrice() {
		return companySharePrice;
	}

	public void setSharePrice(int sharePrice) {
		this.companySharePrice = sharePrice;
	}

	public double getCompanyTotalValue() {
		return companyTotalValue;
	}

	public void setCompanyTotalValue(double companyTotalValue) {
		this.companyTotalValue = companyTotalValue;
	}

}
