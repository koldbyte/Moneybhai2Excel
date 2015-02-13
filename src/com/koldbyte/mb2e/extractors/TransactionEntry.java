package com.koldbyte.mb2e.extractors;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionEntry {
	public String company;
	public int quantity;
	public Date buyDate;
	public double buyPrice;
	public Date sellDate;
	public double sellPrice;
	public double gainOrLoss;
	public String type;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public double getGainOrLoss() {
		return gainOrLoss;
	}
	public void setGainOrLoss(double gainOrLoss) {
		this.gainOrLoss = gainOrLoss;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public TransactionEntry(String company, int quantity, Date buyDate,
			double buyPrice, Date sellDate, double sellPrice,
			double gainOrLoss, String type) {
		this.company = company;
		this.quantity = quantity;
		this.buyDate = buyDate;
		this.buyPrice = buyPrice;
		this.sellDate = sellDate;
		this.sellPrice = sellPrice;
		this.gainOrLoss = gainOrLoss;
		this.type = type;
	}
	public TransactionEntry(String company, int quantity, Date buyDate,
			double buyPrice, Date sellDate, double sellPrice, double gainOrLoss) {
		this.company = company;
		this.quantity = quantity;
		this.buyDate = buyDate;
		this.buyPrice = buyPrice;
		this.sellDate = sellDate;
		this.sellPrice = sellPrice;
		this.gainOrLoss = gainOrLoss;
	}
	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		String bd = "";
		try {
			bd = dateFormat.format(buyDate);
		} catch (Exception e) {
			System.err.println("TransactionEntry : " + "Error in parsing bd : " + buyDate.toString());
		}
		String sd = "";
		try {
			sd = dateFormat.format(sellDate);
		} catch (Exception e) {
			System.err.println("TransactionEntry : " + "Error in parsing sd : " + sellDate.toString() );
		}
		
		return company + ", " + quantity + ", " + bd + ", " + buyPrice
				+ ", " + sd + ", " + sellPrice + ", " + gainOrLoss + ", " + type;
	}
	public TransactionEntry() {
		super();
	}
}
