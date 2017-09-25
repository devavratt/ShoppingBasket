package com.shopping.domain;

import java.math.BigDecimal;

/**
 * @author Devavratt Bagayat
 *
 */
public class Bread extends Item {
	
	/**
	 * 
	 */
	public Bread() {
		name = "Bread";
		itemPrice = new BigDecimal(0.80);
		isDiscountApplied = true;
		discountPercent = new BigDecimal(50);

	}
}
