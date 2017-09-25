package com.shopping.services;

/**
 * @author Devavratt Bagayat
 *
 */
public interface Shopping {
	
	/**
	 * Create and output the shopping basket including any discounts applied.
	 * 
	 * @param items
	 *            - list of items
	 * @return - the PriceBasket output
	 */
	public String processShopping(String[] items);

}
