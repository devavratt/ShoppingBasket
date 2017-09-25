package com.shopping.domain;

import java.math.BigDecimal;

/**
 * @author Devavratt Bagayat
 *
 */
public class Soup extends Item {

	/**
	 * 
	 */
	public Soup() {
		name = "Soup";
		itemPrice = new BigDecimal(0.65);
		isDiscountApplied = false;
	}
}
