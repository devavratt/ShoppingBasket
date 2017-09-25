package com.shopping.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.shopping.domain.Item;
import com.shopping.domain.ItemFactory;
import com.shopping.util.BasketItem;
import com.shopping.util.ShoppingReceipt;
import com.shopping.util.rules.DiscountRule;
import com.shopping.util.rules.DiscountRuleFactory;

/**
 * @author Devavratt Bagayat
 *
 */
public class ShoppingImpl implements Shopping {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shopping.services.Shopping#processShopping(java.lang.String[])
	 */
	@Override
	public String processShopping(String[] items) {

		// 1. create shopping basket
		Map<String, BasketItem> basketItemMap = createShoppingBasket(items);

		// 2. create discounts
		List<DiscountRule> discounts = createDiscountRules(items, basketItemMap);

		// 3. create receipt
		String output = createReceipt(basketItemMap, discounts);

		return output;

	}

	/**
	 * Create a map of the shopping basket with unique items and their total
	 * quantity requested
	 * 
	 * @param items
	 *            - array of item names
	 * @return - map of shopping basket
	 */
	private Map<String, BasketItem> createShoppingBasket(String[] items) {
		Map<String, BasketItem> basketItemMap = new HashMap<String, BasketItem>();

		for (String basketItem : items) {
			if (basketItemMap.keySet().contains(basketItem)) {
				BasketItem p = basketItemMap.get(basketItem);
				p.setItemQuantity(p.getItemQuantity() + 1);
				basketItemMap.put(basketItem, p);
			} else {
				Item item = getItem(basketItem);
				BasketItem p = new BasketItem(item, 1);
				basketItemMap.put(basketItem, p);
			}
		}

		return basketItemMap;
	}

	/**
	 * Compute various discounts offered on the products and create a list of
	 * discounts
	 * 
	 * @param items
	 *            - array of item names
	 * @param basketItemMap
	 *            - map of the shopping basket
	 * @return - List of discount rules
	 */
	private List<DiscountRule> createDiscountRules(String[] items, Map<String, BasketItem> basketItemMap) {
		List<DiscountRule> discounts = new ArrayList<DiscountRule>();

		basketItemMap.entrySet().forEach(entry -> {
			if (basketItemMap.get(entry.getKey()).getItem().isDiscountApplied()) {
				DiscountRule discountRule = DiscountRuleFactory.newInstance(entry.getKey(), basketItemMap);
				discountRule.calculateDiscount();
				discounts.add(discountRule);
			}
		});

		return discounts;
	}

	/**
	 * Create the final shopping receipt computing the total discount amount, gross
	 * and net amount
	 * 
	 * @param basketItemMap
	 *            - map of the shopping basket
	 * @param discounts
	 *            - list of computed discounts
	 * @return - receipt contents
	 */
	private String createReceipt(Map<String, BasketItem> basketItemMap, List<DiscountRule> discounts) {
		BigDecimal subTotal = BigDecimal.ZERO, totalDiscounts = BigDecimal.ZERO, total = BigDecimal.ZERO;

		subTotal = getSubTotal(basketItemMap);

		for (DiscountRule dR : discounts) {
			totalDiscounts = totalDiscounts.add(dR.getDiscountAmount());
		}

		total = subTotal.subtract(totalDiscounts);
		ShoppingReceipt sR = new ShoppingReceipt(subTotal, total, discounts);
		return sR.toString();

	}

	/**
	 * Compute the gross total of all items in the shopping basket
	 * 
	 * @param mockBasketItemMap
	 *            - map of the shopping basket
	 * @return gross total of the shopping
	 */
	protected BigDecimal getSubTotal(Map<String, BasketItem> basketItemMap) {
		BigDecimal subTotal = BigDecimal.ZERO;

		for (Iterator<BasketItem> entries = basketItemMap.values().iterator(); entries.hasNext();) {
			BasketItem entry = entries.next();
			subTotal = subTotal.add(entry.getItem().getItemPrice().multiply(new BigDecimal(entry.getItemQuantity())));
			// subTotal += (entry.getItemQuantity() * entry.getItem().getItemPrice());
		}
		return subTotal;
	}

	/**
	 * Get instance of item
	 * 
	 * @param basketItem
	 *            - item name
	 * @return instance of item
	 */
	protected Item getItem(String basketItem) {
		Item item = ItemFactory.newInstance(basketItem);
		return item;
	}

}
