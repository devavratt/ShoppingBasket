package com.shopping.util.rules;

import java.math.BigDecimal;
import java.util.Map;

import com.shopping.util.BasketItem;

/**
 * @author Devavratt Bagayat
 *
 */
public class AppleDiscountRule extends DiscountRule {

	/**
	 * 
	 * @param mockBasketItemMap
	 */
	public AppleDiscountRule(Map<String, BasketItem> basketItemMap) {
		this.basketItemMap = basketItemMap;
		basketItem = basketItemMap.get("Apple");
		this.percentage = basketItem.getItem().getDiscountPercent();
		this.itemPrice = basketItem.getItem().getItemPrice();
		this.quantity = basketItem.getItemQuantity();
	}

	@Override
	public String toString() {
		return String.format("%s %s%% off: £-%s", basketItem.getItem().getName(),
				percentage.toString(),
				discountAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shopping.util.rules.DiscountRule#calculateDiscount()
	 */
	@Override
	public void calculateDiscount() {
		BigDecimal grossPrice = BigDecimal.ZERO;
		grossPrice =  itemPrice.multiply(new BigDecimal(quantity));

		if (basketItem.getItem().isDiscountApplied()) {
			discountAmount = discountAmount.add(grossPrice.multiply(percentage).divide(new BigDecimal(100)));
		}
		finalPrice = grossPrice.subtract(discountAmount);
	}

}
