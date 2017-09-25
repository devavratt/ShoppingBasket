package com.shopping.domain;

import java.math.BigDecimal;

/**
 * @author Devavratt Bagayat
 *
 */
public class Apple extends Item {

	/**
	 * 
	 */
	public Apple() {
		name = "Apple";
		itemPrice = new BigDecimal(1.0);
		isDiscountApplied = true;
		discountPercent = new BigDecimal(10);
	}
}
