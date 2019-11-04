package com.bridgelabz.cdp.model;

public class Transaction {

	private int transactionId;
	private String buyer;
	private String seller;
	private int transactionAmmount;
	private String date;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public float getTransactionAmmount() {
		return transactionAmmount;
	}

	public void setTransactionAmmount(int transactionAmmount) {
		this.transactionAmmount = transactionAmmount;
	}

}
