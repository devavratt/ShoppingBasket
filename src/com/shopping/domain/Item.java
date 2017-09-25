package com.shopping.domain;

import java.math.BigDecimal;

/**
 * @author Devavratt Bagayat
 *
 */
public class Item {
	
	protected String name;
	protected int quantity;
	protected boolean isDiscountApplied;
	protected BigDecimal discountPercent = BigDecimal.ZERO;
	protected BigDecimal itemPrice = BigDecimal.ZERO;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @return the isDiscountApplied
	 */
	public boolean isDiscountApplied() {
		return isDiscountApplied;
	}
	
	/**
	 * @param isDiscountApplied the isDiscountApplied to set
	 */
	public void setDiscountApplied(boolean isDiscountApplied) {
		this.isDiscountApplied = isDiscountApplied;
	}

	/**
	 * @return the discountPercent
	 */
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * @param discountPercent the discountPercent to set
	 */
	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}

	/**
	 * @return the itemPrice
	 */
	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	/**
	 * @param itemPrice the itemPrice to set
	 */
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
}
