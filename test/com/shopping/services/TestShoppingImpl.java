package com.shopping.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.shopping.domain.Apple;
import com.shopping.domain.Bread;
import com.shopping.domain.Item;
import com.shopping.domain.Milk;
import com.shopping.domain.Soup;
import com.shopping.services.ShoppingImpl;
import com.shopping.util.BasketItem;

/**
 * @author Devavratt Bagayat
 *
 */
public class TestShoppingImpl {

	@Mock
	Item mockApple1, mockApple2, mockBread, mockMilk, mockSoup1, mockSoup2;

	@Spy
	ShoppingImpl componentToTest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		mockApple1 = Mockito.mock(Apple.class);
		mockApple1.setName("Apple");
		mockApple1.setItemPrice(new BigDecimal(1.0));
		mockApple1.setDiscountApplied(true);
		mockApple1.setDiscountPercent(new BigDecimal(10));
		mockApple1.setQuantity(1);
		mockApple2 = Mockito.mock(Apple.class);
		mockApple2.setName("Apple");
		mockApple2.setItemPrice(new BigDecimal(1.0));
		mockApple2.setDiscountApplied(true);
		mockApple2.setDiscountPercent(new BigDecimal(10));
		mockApple2.setQuantity(1);

		mockMilk = Mockito.mock(Milk.class);
		mockMilk.setName("Milk");
		mockMilk.setDiscountApplied(false);
		mockMilk.setItemPrice(new BigDecimal(1.30));
		mockMilk.setQuantity(1);

		mockSoup1 = Mockito.mock(Soup.class);
		mockSoup1.setName("Soup");
		mockSoup1.setItemPrice(new BigDecimal(0.65));
		mockSoup1.setDiscountApplied(false);
		mockSoup1.setQuantity(1);
		mockSoup2 = Mockito.mock(Soup.class);
		mockSoup2.setName("Soup");
		mockSoup2.setItemPrice(new BigDecimal(0.65));
		mockSoup2.setDiscountApplied(false);
		mockSoup2.setQuantity(1);

		mockBread = Mockito.mock(Bread.class);
		mockBread.setName("Bread");
		mockBread.setDiscountApplied(true);
		mockBread.setItemPrice(new BigDecimal(0.80));
		mockBread.setDiscountPercent(new BigDecimal(50));
		mockBread.setQuantity(1);
	}

	/**
	 * Test for an Invalid Item
	 * 
	 * Test method for
	 * {@link com.shopping.services.services.ShoppingImpl#createShoppingBasket(java.lang.String[])}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateShoppingBasket_InvalidItem() {
		String[] items = { "Fish" };
		componentToTest.processShopping(items);
	}

	/**
	 * Test method for
	 * {@link com.shopping.services.services.ShoppingImpl#createShoppingBasket(java.lang.String[])}.
	 */
	@Test
	public final void testCreateShoppingBasket_SingleItem_NoDiscount() {
		String[] items = { "Milk" };
		String expected = "Subtotal: £1.30\n(No offers available)\nTotal: £1.30";

		Mockito.when(componentToTest.getItem("Milk")).thenReturn(mockMilk);
		Mockito.when(mockMilk.getItemPrice()).thenReturn(new BigDecimal(1.30));
		Mockito.when(mockMilk.isDiscountApplied()).thenReturn(false);
		String actual = componentToTest.processShopping(items);

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.shopping.services.services.ShoppingImpl#createShoppingBasket(java.lang.String[])}.
	 */
	@Test
	public final void testCreateShoppingBasket_SingleItem_Discount() {
		String[] items = { "Apple" };
		String expected = "Subtotal: £1.00\nApple 10% off: £-0.10\nTotal: £0.90";

		Mockito.when(componentToTest.getItem("Apple")).thenReturn(mockApple1);
		Mockito.when(mockApple1.getName()).thenReturn("Apple");
		Mockito.when(mockApple1.getItemPrice()).thenReturn(new BigDecimal(1.0));
		Mockito.when(mockApple1.isDiscountApplied()).thenReturn(true);
		Mockito.when(mockApple1.getDiscountPercent()).thenReturn(new BigDecimal(10.0));
		String actual = componentToTest.processShopping(items);

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.shopping.services.services.ShoppingImpl#createShoppingBasket(java.lang.String[])}.
	 */
	@Test
	public final void testCreateShoppingBasket_TwoSameItems_Discount() {
		String[] items = { "Apple", "Apple" };
		String expected = "Subtotal: £2.00\nApple 10% off: £-0.20\nTotal: £1.80";

		Mockito.when(componentToTest.getItem("Apple")).thenReturn(mockApple1);
		Mockito.when(mockApple1.getName()).thenReturn("Apple");
		Mockito.when(mockApple1.getItemPrice()).thenReturn(new BigDecimal(1.0));
		Mockito.when(mockApple1.isDiscountApplied()).thenReturn(true);
		Mockito.when(mockApple1.getDiscountPercent()).thenReturn(new BigDecimal(10.0));
		Mockito.when(componentToTest.getItem("Apple")).thenReturn(mockApple2);
		Mockito.when(mockApple2.getName()).thenReturn("Apple");
		Mockito.when(mockApple2.getItemPrice()).thenReturn(new BigDecimal(1.0));
		Mockito.when(mockApple2.isDiscountApplied()).thenReturn(true);
		Mockito.when(mockApple2.getDiscountPercent()).thenReturn(new BigDecimal(10.0));
		String actual = componentToTest.processShopping(items);

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.shopping.services.services.ShoppingImpl#createShoppingBasket(java.lang.String[])}.
	 */
	@Test
	public final void testCreateShoppingBasket_TwoSameItems_NoDiscount() {
		String[] items = { "Soup", "Soup" };
		String expected = "Subtotal: £1.30\n(No offers available)\nTotal: £1.30";

		Mockito.when(componentToTest.getItem("Soup")).thenReturn(mockSoup1);
		Mockito.when(mockSoup1.getName()).thenReturn("Soup");
		Mockito.when(mockSoup1.getItemPrice()).thenReturn(new BigDecimal(0.65));
		Mockito.when(mockSoup1.isDiscountApplied()).thenReturn(false);
		Mockito.when(componentToTest.getItem("Soup")).thenReturn(mockSoup2);
		Mockito.when(mockSoup2.getName()).thenReturn("Soup");
		Mockito.when(mockSoup2.getItemPrice()).thenReturn(new BigDecimal(0.65));
		Mockito.when(mockSoup2.isDiscountApplied()).thenReturn(false);
		String actual = componentToTest.processShopping(items);

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.shopping.services.services.ShoppingImpl#createShoppingBasket(java.lang.String[])}.
	 */
	@Test
	public final void testCreateShoppingBasket_TwoItems_NoDiscount() {
		String[] items = { "Soup", "Milk" };
		String expected = "Subtotal: £1.95\n(No offers available)\nTotal: £1.95";

		Mockito.when(componentToTest.getItem("Soup")).thenReturn(mockSoup1);
		Mockito.when(mockSoup1.getItemPrice()).thenReturn(new BigDecimal(0.65));
		Mockito.when(mockSoup1.isDiscountApplied()).thenReturn(false);
		Mockito.when(componentToTest.getItem("Milk")).thenReturn(mockMilk);
		Mockito.when(mockMilk.getItemPrice()).thenReturn(new BigDecimal(1.30));
		Mockito.when(mockMilk.isDiscountApplied()).thenReturn(false);
		String actual = componentToTest.processShopping(items);

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.shopping.services.services.ShoppingImpl#createShoppingBasket(java.lang.String[])}.
	 */
	@Test
	public final void testCreateShoppingBasket_ThreeItems_Discount() {
		String[] items = { "Soup", "Soup", "Bread" };
		String expected = "Subtotal: £2.10\nBread 50% off: £-0.40\nTotal: £1.70";
		
		Mockito.when(componentToTest.getItem("Soup")).thenReturn(mockSoup1);
		Mockito.when(mockSoup1.getName()).thenReturn("Soup");
		Mockito.when(mockSoup1.getItemPrice()).thenReturn(new BigDecimal(0.65));
		Mockito.when(componentToTest.getItem("Soup")).thenReturn(mockSoup2);
		Mockito.when(mockSoup2.getName()).thenReturn("Soup");
		Mockito.when(mockSoup2.getItemPrice()).thenReturn(new BigDecimal(0.65));
		Mockito.when(componentToTest.getItem("Bread")).thenReturn(mockBread);
		Mockito.when(mockBread.getName()).thenReturn("Bread");
		Mockito.when(mockBread.getItemPrice()).thenReturn(new BigDecimal(0.80));
		Mockito.when(mockBread.isDiscountApplied()).thenReturn(true);
		Mockito.when(mockBread.getDiscountPercent()).thenReturn(new BigDecimal(50));
		
		BasketItem mockSoupBI = Mockito.mock(BasketItem.class);
		mockSoupBI.setItemQuantity(2);
		mockSoupBI.setItem(mockSoup1);
		BasketItem mockBreadBI = Mockito.mock(BasketItem.class);
		mockBreadBI.setItemQuantity(1);
		mockBreadBI.setItem(mockBread);
		Map<String, BasketItem> basketItemMap = new HashMap<String, BasketItem>();
		basketItemMap.put("Soup", mockSoupBI);
		basketItemMap.put("Bread", mockBreadBI);
		Mockito.when(mockSoupBI.getItem()).thenReturn(mockSoup1);
		Mockito.when(mockBreadBI.getItem()).thenReturn(mockBread);
		
		String actual = componentToTest.processShopping(items);

		assertEquals(expected, actual);
	}


}
