package jp.co.training.java.base.test;

import static org.junit.Assert.*;
//import jp.co.vsn.training.java.base.Train;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jp.co.training.java.base.Train;

public class TrainTest {

    private static final String LINENAME = "山手線";
    private Train train;
    private String[] stations = {"東京", "有楽町", "新橋", "浜松町", "田町", "高輪ゲートウェイ", "品川"};

    @Before
    public void beforeTest() {
        this.train = new Train(LINENAME, stations);
    }

    @Ignore
    @Test
    public void testTrain() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testTrainNull() {
    	this.train = new Train(LINENAME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTrainOneStation() {
    	this.train = new Train(LINENAME, new String[]{"YRP野比"});
    }

    @Ignore
    @Test
    public void testGetCurrentStation() {

    }

    @Test
    public void testGetLineName() {
        assertEquals(LINENAME, train.getLineName());
    }

    @Test
    public void testGetNextStation() {
        assertEquals(stations[1], train.getNextStation());
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        assertNull(train.getNextStation());
    }

    @Test
    public void testGetStartingStation() {
        assertEquals(stations[0], train.getStartingStation());
    }

    @Test
    public void testGetTerminalStation() {
        assertEquals(stations[stations.length - 1], train.getTerminalStation());
    }

    @Test
    public void testGoNextStation() {
        train.goNextStation();
        train.goNextStation();
        assertEquals(stations[2], train.getCurrentStation());
    }

    @Test
    public void testGoNextStationTerminal() {
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        assertTrue(train.isTerminalStation());
    }

    @Test
    public void testIsStartingStation() {
        assertTrue(train.isStartingStation());
        train.goNextStation();
        train.goNextStation();
        assertFalse(train.isStartingStation());
    }

    @Test
    public void testIsNextTerminalStation() {
        assertEquals(false, train.isNextTerminalStation());
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        assertEquals(false, train.isNextTerminalStation());
        train.goNextStation();
        assertEquals(true, train.isNextTerminalStation());
    }

    @Test
    public void testIsTerminalStation() {
        assertEquals(false, train.isTerminalStation());
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        assertEquals(false, train.isTerminalStation());
        train.goNextStation();
        assertEquals(true, train.isTerminalStation());
    }
}
