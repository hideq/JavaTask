package jp.co.training.java.base.test;

import static org.junit.Assert.*;
//import jp.co.vsn.training.java.base.Counter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jp.co.training.java.base.Counter;

public class CounterTest {
    private static final int COUNT = 10;
    private static final int UNIT = 2;
    private Counter counter;

    @Before
    public void beforeTest() {
        this.counter = new Counter(COUNT, UNIT);
    }

    @Test
    public void testCounter() {
        Counter c = new Counter();
        assertEquals(0, c.getCount());
        assertEquals(1, c.getUnit());
    }

    @Test
    public void testCounterIntInt() {
        assertEquals(COUNT, counter.getCount());
        assertEquals(UNIT, counter.getUnit());
    }

    @Ignore
    @Test
    public void testGetCount() {

    }

    @Ignore
    @Test
    public void testGetUnit() {

    }

    @Test
    public void testSetUnit() {
        int setUnit = 4;
        assertEquals(UNIT, counter.getUnit());
        counter.setUnit(setUnit);
        assertEquals(setUnit, counter.getUnit());
    }

    @Test
    public void testInc() {
        assertEquals(COUNT, counter.getCount());
        Counter c = counter.inc();
        assertTrue("戻り値の型が一致しません", c != null || c instanceof Counter);
        assertEquals(COUNT + UNIT, c.getCount());
    }

    @Test
    public void testDec() {
        assertEquals(COUNT, counter.getCount());
        Counter c = counter.dec();
        assertTrue("戻り値の型が一致しません", c != null || c instanceof Counter);
        assertEquals(COUNT - UNIT, c.getCount());
    }

    @Test
    public void testBetween() {
        assertTrue(counter.between(9, 10));
        assertTrue(counter.between(10, 11));
        assertTrue(counter.between(-1, 20));
        assertFalse(counter.between(5, 9));
        assertFalse(counter.between(11, 20));
    }

    @Test
    public void testToString() {
        assertEquals(COUNT + ":" + UNIT, counter.toString());
    }

    @Test
    public void testParse() {
        counter.parse("45:5");
        assertEquals(45, counter.getCount());
        assertEquals(5, counter.getUnit());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testParseException01() {
        counter.parse("45:A");
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testParseException02() {
        counter.parse("45");
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testParseNull() {
        counter.parse(null);
    }
}
