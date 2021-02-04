package jp.co.training.java.base.test;

import static org.junit.Assert.*;
//import jp.co.vsn.training.java.base.Conductor;
//import jp.co.vsn.training.java.base.Train;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jp.co.training.java.base.Conductor;
import jp.co.training.java.base.Train;

public class ConductorTest {

    private Conductor conductor;
    private Train train;

    @Before
    public void beforeTest() {
        String[] stations = {"東京", "有楽町", "新橋", "浜松町", "田町", "高輪ゲートウェイ", "品川", "大崎"};
        this.train = new Train("山の手線", stations);
        this.conductor = new Conductor(this.train);
    }

    @Ignore
    @Test
    public void testConductor() {

    }

    @Test
    public void testAnnounce() {
        assertEquals("東京、東京、この電車は大崎行きです。次は有楽町です。", conductor.announce());
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        assertEquals("浜松町、浜松町、この電車は大崎行きです。次は田町です。", conductor.announce());
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        assertEquals("品川、品川、この電車は大崎行きです。次は大崎、終点です。", conductor.announce());
        train.goNextStation();
        assertEquals("大崎、大崎、終点です。ご利用ありがとうございました。", conductor.announce());
    }
}
