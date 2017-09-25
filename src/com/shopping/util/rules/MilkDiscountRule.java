package com.shopping.util.rules;

import java.util.Map;

import com.shopping.util.BasketItem;

/**
 * @author Devavratt Bagayat
 *
 */
public class MilkDiscountRule extends DiscountRule {

	/**
	 * 
	 * @param mockBasketItemMap
	 */
	public MilkDiscountRule(Map<String, BasketItem> basketItemMap) {
		this.basketItemMap = basketItemMap;
		basketItem = basketItemMap.get("Milk");
		this.percentage = basketItem.getItem().getDiscountPercent();
		this.itemPrice = basketItem.getItem().getItemPrice();
		this.quantity = basketItem.getItemQuantity();
	}

}
