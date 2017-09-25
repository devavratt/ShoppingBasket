package com.shopping.main;

import com.shopping.services.Shopping;
import com.shopping.services.ShoppingImpl;

/**
 * @author Devavratt Bagayat
 *
 */
public class PriceBasket {
	
	private Shopping shopping;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PriceBasket priceBasket = new PriceBasket();
		priceBasket.createShopping(args);
	}

	private void createShopping(String[] args) {
		shopping = new ShoppingImpl();
		String output = shopping.processShopping(args);
		
		System.out.print(output);
	}

}
