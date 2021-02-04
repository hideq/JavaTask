package jp.co.training.java.base.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jp.co.training.java.base.EnqueteAnswer;

public class EnqueteAnswerTest {
    private EnqueteAnswer enqAns;

    @Before
    public void beforeTest() {
        enqAns = new EnqueteAnswer();
        enqAns.add("メロン");
        enqAns.add("ぶどう");
        enqAns.add("みかん");
        enqAns.add("メロン");
        enqAns.add("いちご");
        enqAns.add("いちご");
        enqAns.add("桃");
        enqAns.add("桃");
        enqAns.add("りんご");
        enqAns.add("メロン");
        // メロン 3, ぶどう 1, みかん 1, いちご 2, 桃 2, りんご 1
    }

    @Ignore
    @Test
    public void testEnqueteAnswer() {
        fail("まだ実装されていません");
    }

    @Test
    public void testAdd() {
        enqAns.add("メロン");
        assertEquals(4, enqAns.getCount("メロン"));
    }

    @Test
    public void testNewAdd() {
        enqAns.add("じゃがいも");
        assertEquals(1, enqAns.getCount("じゃがいも"));
    }

    @Test(expected=NullPointerException.class)
    public void testAddNull() {
        enqAns.add(null);
    }

    @Test
    public void testContains() {
        assertTrue(enqAns.contains("りんご"));
        assertFalse(enqAns.contains("梨"));
    }

    @Test(expected=NullPointerException.class)
    public void testContainsNull() {
        enqAns.contains(null);
    }

    @Test
    public void testRemove() {
        enqAns.remove("ぶどう");
        assertFalse(enqAns.contains("ぶどう"));
    }

    public void testRemoveNotExist() {
        enqAns.remove("柿");
        assertTrue(true);
    }

    @Test(expected=NullPointerException.class)
    public void testRemoveNull() {
        enqAns.remove(null);
    }

    @Test
    public void testGetCount() {
        assertEquals(2, enqAns.getCount("いちご"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCountNotExist() {
        enqAns.getCount("柿");
    }

    @Test(expected=NullPointerException.class)
    public void testGetCountNull() {
        enqAns.getCount(null);
    }

    @Test
    public void testSetCount() {
        enqAns.setCount("りんご", 3);
        assertEquals(3, enqAns.getCount("りんご"));
    }

    @Test
    public void testSetCountNotExist() {
        enqAns.setCount("スイカ", 3);
        assertEquals(3, enqAns.getCount("スイカ"));
    }

    @Test(expected=NullPointerException.class)
    public void testSetCountNull() {
        enqAns.setCount(null, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetNegativeCount() {
        enqAns.setCount("パイナップル", -50);
    }

    @Test
    public void testGetAnswerList() {
        List<String> list = enqAns.getAnswerList();
        String[] ans = {"メロン", "ぶどう" , "みかん", "いちご", "桃", "りんご"};
        for (int i = 0 ; i < ans.length ; i++) {
            assertEquals(ans[i], list.get(i));
        }
    }

    @Test
    public void testGetAnswerListNoAnswer() {
        EnqueteAnswer ea = new EnqueteAnswer();
        List<String> list = ea.getAnswerList();
        assertEquals(0, list.size());
    }

    @Test
    public void testTotal() {
        assertEquals(10, enqAns.total());
    }

    @Test
    public void testTotalNone() {
        EnqueteAnswer ea = new EnqueteAnswer();
        assertEquals(0, ea.total());
    }

    @Test
    public void testToString() {
        // メロン 3, ぶどう 1, みかん 1, いちご 2, 桃 2, りんご 1
        String str = "メロン#3:ぶどう#1:みかん#1:いちご#2:桃#2:りんご#1";
        assertEquals(str, enqAns.toString());
    }

    @Test
    public void testToStringNone() {
        EnqueteAnswer ea = new EnqueteAnswer();
        assertEquals("none", ea.toString());
    }
}