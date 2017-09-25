package com.shopping.domain;

/**
 * @author Devavratt Bagayat
 *
 */
public class ItemFactory {

	private static Item item;

	public static Item newInstance(String itemType) {

		switch (itemType) {
		case "Apple":
			item = new Apple();
			break;
		case "Milk":
			item = new Milk();
			break;
		case "Bread":
			item = new Bread();
			break;
		case "Soup":
			item = new Soup();
			break;
		default:
			throw new IllegalArgumentException("Invalid item type" + itemType);
		}

		return item;
	}
}
