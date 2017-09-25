package com.shopping.util;

import com.shopping.domain.Item;

/**
 * @author Devavratt Bagayat
 *
 */
public class BasketItem {
	
	private Item item;
	private int itemQuantity;

	/**
	 * @param item
	 * @param itemQuantity
	 */
	public BasketItem(Item item, int itemQuantity) {
		this.item = item;
		this.itemQuantity = itemQuantity;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the itemQuantity
	 */
	public int getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
}
