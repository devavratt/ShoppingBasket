package com.shopping.util.rules;

import java.util.Map;

import com.shopping.util.BasketItem;

/**
 * @author Devavratt Bagayat
 *
 */
public class DiscountRuleFactory {
	private static DiscountRule discountRule;

	public static DiscountRule newInstance(String discountRuleFor, Map<String, BasketItem> basketItemMap) {

		switch (discountRuleFor) {
		case "Apple":
			discountRule = new AppleDiscountRule(basketItemMap);
			break;
		case "Milk":
			break;
		case "Bread":
			discountRule = new BreadDiscountRule(basketItemMap);
			break;
		case "Soup":
			break;
		default:
			throw new IllegalArgumentException("Invalid Discount Rule" + discountRuleFor);
		}

		return discountRule;
	}

}
