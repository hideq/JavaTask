package jp.co.training.java.base.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jp.co.training.java.base.Cart;
import jp.co.training.java.base.CartItem;
import jp.co.training.java.base.Item;

public class CartTest {
    Cart cart;
    Item item1, item2, item3;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        item1 = new Item();
        item1.setItemId(100);
        item1.setItemname("プログラミング作法");
        item1.setPrice(2800);
        item1.setCategoryCode("B3");
        item1.setCategoryName("書籍");
        item1.setExplanation("説明_100");
        item1.setImageName("image-B3TPP.jpg");
        item1.setOriginalId("ISBN-7561-3649-4");

        item2 = new Item();
        item2.setItemId(200);
        item2.setItemname("ソフトウェア・テストの技法");
        item2.setPrice(3200);
        item2.setCategoryCode("B3");
        item2.setCategoryName("書籍");
        item2.setExplanation("説明_200");
        item2.setImageName("image-B3SWT.jpg");
        item2.setOriginalId("ISBN-7649-0329-6");

        item3 = new Item();
        item3.setItemId(300);
        item3.setItemname("「教え上手」になるためのスキル");
        item3.setPrice(1500);
        item3.setCategoryCode("B3");
        item3.setCategoryName("書籍");
        item3.setExplanation("説明_300");
        item3.setImageName("image-B3LRG.jpg");
        item3.setOriginalId("ISBN-86063-062-9");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAdd() {
        cart.add(item1, 3);
        cart.add(item2, 5);
        cart.add(item1, 2);

        assertEquals(5, cart.getAmount(100));
        assertEquals(5, cart.getAmount(200));

        try {
            cart.add(item2, 0);
        } catch (Exception ex) {
            fail("例外が発生しました: " + ex);
        }

        try {
            cart.add(item2, -1);
            fail("例外が発生しませんでした。");
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        try {
            cart.add(null, 0);
            fail("例外が発生しませんでした。");
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddAmount() {
        cart.add(item1, 1);
        int id = item1.getItemId();

        cart.addAmount(id, 100);
        assertEquals(101, cart.getAmount(id));

        try {
            cart.addAmount(999, 0);
            cart.addAmount(id, 1);
        } catch (Exception ex) {
            fail("例外が発生しました: " + ex);
        }

        try {
            cart.addAmount(id, -1000);
            fail("例外が発生しませんでした。");
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testSetAmount() {
        cart.add(item1, 1);
        int id = item1.getItemId();

        cart.setAmount(id, 100);
        assertEquals(100, cart.getAmount(id));

        try {
            cart.setAmount(999, 0);
        } catch (Exception ex) {
            fail("例外が発生しました: " + ex);
        }

        try {
            cart.addAmount(id, -1);
            fail("例外が発生しませんでした: " + cart.getAmount(id));
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testHasItem() {
        cart.add(item1, 10);
        int id = item1.getItemId();
        assertTrue(cart.hasItem(id));
        assertFalse(cart.hasItem(1000));
    }

    @Test
    public void testItemIdIterator() {
        cart.add(item1, 1);
        cart.add(item2, 2);
        cart.add(item3, 3);
        Iterator<Integer> it = cart.itemIdIterator();
        while (it.hasNext()) {
            assertTrue(cart.hasItem(it.next()));
        }
    }

    @Test
    public void testGetCartItemList() {
        cart.add(item1, 1);
        cart.add(item2, 2);
        cart.add(item3, 3);
        List<CartItem> list = cart.getCartItemList();
        assertEquals(3, list.size());
    }

    @Test
    public void testGetCartItem() {
        cart.add(item1, 1);
        cart.add(item2, 2);
        cart.add(item3, 3);
        CartItem citem = cart.getCartItem(200);
        assertEquals(2, citem.getAmount());
        assertNull(cart.getCartItem(12345));  // Not Exists
    }

    @Test
    public void testCalcTotalSum() {
        assertEquals(0, cart.calcTotalSum());

        cart.add(item1, 1);
        cart.add(item2, 2);
        cart.add(item3, 3);
        assertEquals(2800 * 1 + 3200 * 2 + 1500 * 3, cart.calcTotalSum());
    }

    @Test
    public void testRemoveItem() {
        cart.add(item1, 1);
        cart.add(item2, 2);
        cart.add(item3, 3);
        cart.removeItem(200);
        assertFalse(cart.hasItem(200));

        try {
            cart.removeItem(999);
        } catch (Exception ex) {
            fail("例外が発生しました: " + ex);
        }
    }

    @Test
    public void testSize() {
        cart.add(item1, 1);
        cart.add(item2, 2);
        cart.add(item3, 3);
        assertEquals(3, cart.size());
    }

    @Test
    public void testClear() {
        cart.add(item1, 1);
        cart.add(item2, 2);
        cart.add(item3, 3);
        cart.clear();
        assertEquals(0, cart.size());
    }
}
