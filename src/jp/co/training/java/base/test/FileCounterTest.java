package jp.co.training.java.base.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jp.co.training.java.base.FileCounter;


public class FileCounterTest {
    private final int COUNT = 256;
    private final int UNIT = 8;
    private File file;           // テスト開始時に生成するファイルパス
    private File notExistFile;   // 存在しないファイルパス
    private FileCounter counter;

    @Before
    public void beforeTest() {
        file = new File("FileCounterTest.txt");
        notExistFile = new File(Long.toString(System.currentTimeMillis()) + ".txt");

        Writer out = null;
        try {
            out = new FileWriter(file);
            out.write(COUNT + ":" + UNIT);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            close(out);
        }
        counter = new FileCounter(file);
    }

    @After
    public void afterTest() {
        file.delete();
        notExistFile.delete();
    }

    @Test
    public void testFileCounterFile() {
        assertEquals(file, counter.getFile());
        assertEquals(false, counter.isAuto());
        assertEquals(COUNT, counter.getCount());
        assertEquals(UNIT, counter.getUnit());
    }

    @Test
    public void testFileCounterFileNotFoundFile() {
        FileCounter fc = new FileCounter(notExistFile);
        assertEquals(0, fc.getCount());
        assertEquals(1, fc.getUnit());
        assertEquals(false, counter.isAuto());
    }

    @Test
    public void testFileCounterFileBoolean() {
        FileCounter fc = new FileCounter(file, true);
        assertEquals(file, fc.getFile());
        assertEquals(true, fc.isAuto());
        assertEquals(COUNT, counter.getCount());
        assertEquals(UNIT, counter.getUnit());
    }

    @Test
    public void testFileCounterFileBooleanNotFoundFile() {
        FileCounter fc = new FileCounter(notExistFile, true);
        assertEquals(0, fc.getCount());
        assertEquals(1, fc.getUnit());
        assertEquals(true, fc.isAuto());
    }

    @Test
    public void testInc() {
        counter.inc();
        counter.inc();
        counter.inc();
        assertEquals(COUNT + UNIT + UNIT + UNIT, counter.getCount());
    }

    @Test
    public void testIncAuto() {
        int unit = 4;
        FileCounter fc = new FileCounter(file, true);
        fc.setUnit(unit);
        fc.inc();
        assertEquals(COUNT + unit, fc.getCount());

        int[] fileValues = readFile();
        assertEquals(COUNT + unit, fileValues[0]);
        assertEquals(unit, fileValues[1]);
    }

    @Test
    public void testDecAuto() {
        int unit = 3;
        FileCounter fc = new FileCounter(file, true);
        fc.setUnit(unit);
        fc.dec();
        assertEquals(COUNT - unit, fc.getCount());

        int[] fileValues = readFile();
        assertEquals(COUNT - unit, fileValues[0]);
        assertEquals(unit, fileValues[1]);
    }

    @Test
    public void testSave() {
        counter.inc();
        counter.inc();
        counter.save();
        int[] fileValues = readFile();
        assertEquals(COUNT + UNIT + UNIT, fileValues[0]);
        assertEquals(UNIT, fileValues[1]);
    }

    @Test(expected=java.lang.IllegalStateException.class)
    public void testSaveException() {
        FileCounter fc = new FileCounter(notExistFile);
        fc.inc();
        fc.setFile(file);
        file.setWritable(false, false); // 書き込み不可
        fc.save();
    }

    @Test
    public void testLoad() {
        FileCounter fc = new FileCounter(notExistFile);
        fc.setFile(file);
        fc.load();
        assertEquals(COUNT, fc.getCount());
        assertEquals(UNIT, fc.getUnit());
    }

    @Test(expected=java.lang.IllegalStateException.class)
    public void testLoadException() {
        FileCounter fc = new FileCounter(notExistFile);
        fc.load();
    }

    @Test
    public void testSetFile() {
        FileCounter fc = new FileCounter(notExistFile);
        fc.setFile(file);
        assertEquals(file, fc.getFile());
    }

    @Test
    public void testSetAuto() {
        FileCounter fc = new FileCounter(notExistFile);
        fc.setAuto(true);
        assertTrue(fc.isAuto());
    }

    @Ignore
    @Test
    public void testGetFile() {
        fail("まだ実装されていません");
    }

    @Ignore
    @Test
    public void testIsAuto() {
        fail("まだ実装されていません");
    }

    // ヘルパーメソッド
    private int[] readFile() {
        int[] fileValues = new int[2];
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String line = in.readLine();
            String[] countUnitStr = line.split(":");
            fileValues[0] = Integer.parseInt(countUnitStr[0]);
            fileValues[1] = Integer.parseInt(countUnitStr[1]);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            close(in);
        }

        return fileValues;
    }

    private void close(Reader in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ex) {}
        }
    }
    private void close(Writer out) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException ex) {}
        }
    }
}
