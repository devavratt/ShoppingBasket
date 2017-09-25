package com.shopping.util.rules;

import java.util.Map;

import com.shopping.util.BasketItem;

/**
 * @author Devavratt Bagayat
 *
 */
public class SoupDiscountRule extends DiscountRule {

	/**
	 * 
	 * @param mockBasketItemMap
	 */
	public SoupDiscountRule(Map<String, BasketItem> basketItemMap) {
		this.basketItemMap = basketItemMap;
		basketItem = basketItemMap.get("Soup");
		this.percentage = basketItem.getItem().getDiscountPercent();
		this.itemPrice = basketItem.getItem().getItemPrice();
		this.quantity = basketItem.getItemQuantity();
	}

}
