package jp.co.training.java.base.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import jp.co.training.java.base.DirUtil;

public class DirUtilTest {

    @Before
    public void beforeTest() {

    }

    @Test
    public void testRemove() {
        File dir = createDir();

        DirUtil.remove(dir);
        assertFalse(dir.exists());
    }

    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testRemoveException() {
        File tempDir = new File(Long.toString(System.currentTimeMillis()));
        DirUtil.remove(tempDir);
    }

    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testRemoveNull() {
        DirUtil.remove(null);
    }
    
    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testRemoveFile() {
    	File f = new File("tmp.txt");
    	try {
	    	f.createNewFile();
	        DirUtil.remove(f);
    	} catch (IOException ioe) {
    		
    	} finally {
    		if (f.exists()) {
    			f.delete();
    		}
    	}
    }    

    // ヘルパーメソッド
    private File createDir() {
        // ディレクトリの深さ
        final int DEEPNESS = 5;
        String rootDirName = "DirUtilTest";

        StringBuffer path = new StringBuffer(rootDirName);
        for (int i = 0 ; i < DEEPNESS ; i++) {
            path.append("/dir" + i);
        }

        // create deep directory
        File dir = new File(path.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        createFiles(dir, 5);
        createFiles(dir.getParentFile(), 4);
        createFiles(dir.getParentFile().getParentFile(), 3);
        createFiles(dir.getParentFile().getParentFile().getParentFile(), 2);
        createFiles(dir.getParentFile().getParentFile().getParentFile().getParentFile(), 1);

        return new File(rootDirName);
    }

    // dirディレクトリに num個の新しいファイルを作成
    private void createFiles(File dir, int num) {
        if (dir.isFile())
            return;

        try {
            for (int i = 0 ; i < num ; i++) {
                File f = new File(dir, "file" + i);
                f.createNewFile();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
