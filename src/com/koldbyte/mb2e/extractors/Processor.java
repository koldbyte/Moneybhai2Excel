package com.koldbyte.mb2e.extractors;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class Processor {
	ArrayList<TransactionEntry> entries = new ArrayList<TransactionEntry>();

	String cType = "regular";

	public ArrayList<TransactionEntry> process(String content) {
		Document page = Jsoup.parse(content);
		org.jsoup.select.Elements tables = page.select("table");

		// System.err.println("BHASKAR1 ");
		// System.err.println(tables.html());
		// System.err.println("1BHASKAR");

		removeComments(page);

		//2015-02-03 14:26:06
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (Element table : tables) {
			if (table.hasAttr("id")) {
				// its the main tbody
				Element tbody = table.child(1);
				for (Element tr : tbody.getElementsByTag("tr")) {

					//System.err.println("BHASKAR1");
					//System.err.println(tr.html());
					//System.err.println("1BHASKAR");

					TransactionEntry newEntry = new TransactionEntry();

					newEntry.setCompany(tr.child(0).child(0).text());

					try {
						newEntry.setQuantity(Integer.parseInt(tr.child(1)
								.child(0).text()));
					} catch (NumberFormatException e1) {
						System.err.println("Processor : " + "Error in setQuantity: " + tr.child(1).child(0).text() );
						newEntry.setQuantity(0);
					}

					try {
						newEntry.setBuyDate(dateFormat.parse(tr.child(2)
								.text()));
					} catch (Exception e) {
						newEntry.setBuyDate((Date) new Date().clone());
						System.err.println("Processor : " + "Error in setBuyDate: "  + tr.child(2).text());
					}

					try {
						newEntry.setBuyPrice(parseDecimal(tr.child(3)
								.text()));
					} catch (Exception e1) {
						newEntry.setBuyPrice(0.0);
						System.err.println("Processor : " + "Error in setBuyPrice: " + tr.child(3).text());
					}

					try {
						newEntry.setSellDate(dateFormat.parse(tr.child(4)
								.text()));
					} catch (ParseException e) {
						newEntry.setSellDate((Date) new Date().clone());
						System.err.println("Processor : " + "Error in setSellDate: " + tr.child(4).text());
					}

					try {
						newEntry.setSellPrice(parseDecimal(tr.child(5)
								.text()));
					} catch (Exception e) {
						newEntry.setSellPrice(0.0);
						System.err.println("Processor : " + "Error in setsellPrice: " + tr.child(5).text());
					}

					try {
						newEntry.setGainOrLoss(parseDecimal(tr.child(6).text()));
					} catch (Exception e) {
						newEntry.setGainOrLoss(0.0);
						System.err.println("Processor : " + "Error in setGainLoss: " + tr.child(6).text());
					} 

					newEntry.setType(cType);

					entries.add(newEntry);

				}
			} else {
				/*
				 * <table width='845'><tr><td colspan=8 style='background: none
				 * repeat scroll 0 0 #606060;color: #FFFFFF;font: 18px
				 * arial;margin-top: 10px;padding: 6px 10px;'>Intraday
				 * ShortBuy</td></tr></table>
				 */
				cType = table.child(0).child(0).text();
			}
		}

		return entries;
	}

	private static void removeComments(Node node) {
		for (int i = 0; i < node.childNodes().size();) {
			Node child = node.childNode(i);
			if (child.nodeName().equals("#comment"))
				child.remove();
			else {
				removeComments(child);
				i++;
			}
		}
	}
	
	public double parseDecimal(String input) throws ParseException{
		  NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		  ParsePosition parsePosition = new ParsePosition(0);
		  Number number = numberFormat.parse(input, parsePosition);

		  if(parsePosition.getIndex() != input.length()){
		    throw new ParseException("Invalid input", parsePosition.getIndex());
		  }

		  return number.doubleValue();
		}
}
