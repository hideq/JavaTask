package jp.co.training.java.base;

import java.io.File;


/**
 * ディレクトリ（フォルダ）の実用クラスです。
 *
 * @author Hidenori Shima
 */
public class DirUtil {

    /**
     * 指定パスのディレクトリを削除します。<br/>
     * ディレクトリの中に存在するファイルまたはディレクトリすべて削除します。<br/>
     * 指定パスがディレクトリでない、または存在しない場合、例外 IllegalArgumentException をスローします。
     *
     * @param dir 削除対象ディレクトリパス
     * @throws IllegalArgumentException 指定パスがディレクトリでない、または存在しない場合
     */
    public static void remove(File dir) {
    	//指定パスのディレクトリが存在しない場合
    	if (dir == null) {
    		throw new IllegalArgumentException("指定パスがnullです");
    	}
    	//指定パスがディレクトリではない場合
    	if(!dir.isDirectory()) {
    		dir.delete();
    		throw new IllegalArgumentException("指定パスがディレクトリではありません");
    	}
    	else 	if (dir.isDirectory()) {
    		if (dir.delete()) {
    			;
    		}
    		else {
    			removeRecursively(dir);
    		}
    		dir.delete();
    	}
    }

    /**
     * 再帰を利用してディレクトリの中に存在するファイルまたはディレクトリをすべて削除します。<br/>
     * @param dir 削除対象ディレクトリパス
     */
    public static void removeRecursively(File dir) {
    	File[] allFiles = dir.listFiles();
    	if (allFiles != null)  {
    		for (File children : allFiles) {
    			removeRecursively(children);
    			System.out.println("Remove file: " + dir.getPath());
    			children.delete();
    		}
    	}
    }


    /**
     * 動作確認用の実行メソッドです。
     * @param args;
     */

    public static void main(String[] args) {
    }
}


