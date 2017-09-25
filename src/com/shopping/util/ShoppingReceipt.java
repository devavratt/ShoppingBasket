package com.shopping.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.StringJoiner;

import com.shopping.util.rules.DiscountRule;

/**
 * @author Devavratt Bagayat
 *
 */
public class ShoppingReceipt {

	private BigDecimal subTotal;
	private BigDecimal total;
	private Collection<DiscountRule> discountRules;

	public ShoppingReceipt(BigDecimal subTotal, BigDecimal total, Collection<DiscountRule> discountRules) {
		this.total = total;
		this.subTotal = subTotal;
		this.discountRules = discountRules;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(String.format("Subtotal: £%s\n",
				subTotal.setScale(2, BigDecimal.ROUND_DOWN).toString()));
		output.append(getDiscountsText()).append("\n");
		output.append(String.format("Total: £%s", total.setScale(2, BigDecimal.ROUND_DOWN).toString()));
		return output.toString();
	}

	/**
	 * Create the discount text for final shopping receipt by looping through the
	 * discount rules
	 * 
	 * @return - detailed text of discounts applied
	 */
	private String getDiscountsText() {
		if (discountRules.isEmpty()) {
			return "(No offers available)";
		}
		StringJoiner discountText = new StringJoiner("\n");
		discountRules.forEach(discount -> discountText.add(discount.toString()));
		return discountText.toString();
	}

	/**
	 * @return the subTotal
	 */
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal
	 *            the subTotal to set
	 */
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * @return the discountRules
	 */
	public Collection<DiscountRule> getDiscounts() {
		return discountRules;
	}

	/**
	 * @param discountRules
	 *            the discountRules to set
	 */
	public void setDiscounts(Collection<DiscountRule> discountRules) {
		this.discountRules = discountRules;
	}

}
