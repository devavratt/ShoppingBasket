package com.shopping.util.rules;

import java.math.BigDecimal;
import java.util.Map;

import com.shopping.util.BasketItem;

/**
 * @author Devavratt Bagayat
 *
 */
public class DiscountRule {

	public Map<String, BasketItem> basketItemMap;
	public BasketItem basketItem;
	public BigDecimal percentage = BigDecimal.ZERO;
	public BigDecimal itemPrice = BigDecimal.ZERO;
	public int quantity;
	public BigDecimal discountAmount = BigDecimal.ZERO;
	public BigDecimal finalPrice = BigDecimal.ZERO;

	/**
	 * @return the basketItem
	 */
	public BasketItem getBasketItem() {
		return basketItem;
	}

	/**
	 * @return the percentage
	 */
	public BigDecimal getPercentage() {
		return percentage;
	}

	/**
	 * @return the itemPrice
	 */
	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	/**
	 * @return the discountAmount
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * @return the finalPrice
	 */
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	@Override
	public String toString() {
		return "(No offers available)\n";
	}

	/**
	 * Calculate the total discount amount to applied to this item
	 */
	public void calculateDiscount() {
		BigDecimal grossPrice = BigDecimal.ZERO;
		grossPrice =  itemPrice.multiply(new BigDecimal(quantity));

		finalPrice = grossPrice.subtract(discountAmount);
	}
}
