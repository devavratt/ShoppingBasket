package com.shopping.domain;

import java.math.BigDecimal;

/**
 * @author Devavratt Bagayat
 *
 */
public class Milk extends Item {
	
	/**
	 * 
	 */
	public Milk() {
		name = "Milk";
		itemPrice = new BigDecimal(1.30);
		isDiscountApplied = false;
	}
}
