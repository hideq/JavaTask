package jp.co.training.java.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * カウント数、変化量をファイルに書き込んだり、ファイルから読み込んだりするクラスです。<br/>
 * ファイルに書き込む内容は、Counter クラスのオブジェクトの文字列表現です。
 *
 * @author Hidenori Shima
 */
public class FileCounter extends Counter{

    // カウント数と変化量が記述されたファイルパス
    private File file;
    
    // 自動ファイル保存状態（true - 有効、false - 無効）
    private boolean isAuto;

    /**
     * ファイルを指定し、自動ファイル保存状態を無効にしてオブジェクトを構築します。<br/>
     * すでにファイルが存在する場合は、ファイルを読み込み、読み込んだ内容でカウント数と変化量を設定します。<br/>
     * ファイルが存在しない場合は、カウント数を 0、変化量を 1 に設定します。<br/>
     * ファイル内容の解析に失敗した場合、例外 java.lang.IllegalStateException をスローします。
     *
     * @param file ファイルパス
     * @throws IOException 
     * @throws IllegalStateException ファイル内容の解析に失敗した場合
     */
    public FileCounter(File file) {
    	super(0, 1);
    	this.file = file;
    	this.isAuto = false;
    	int[] fileValues = new int[2];
        BufferedReader in = null;

        try {
        	if (file.exists()) {
        		in = new BufferedReader(new FileReader(file));
        		String line = in.readLine();
        		String[] countUnitStr = line.split(":");
        		fileValues[0] = Integer.parseInt(countUnitStr[0]);
        		fileValues[1] = Integer.parseInt(countUnitStr[1]);
        		super.setUnit(fileValues[0]);
        		super.inc(); 	
        		super.setUnit(fileValues[1]);
        		in.close();
        	}
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new java.lang.IllegalStateException("ファイル内容の解析に失敗");
    	}
    }

    /**
     * ファイルを指定し、自動ファイル保存状態を設定してオブジェクトを構築します。<br/>
     * すでにファイルが存在する場合は、ファイルを読み込み、読み込んだ内容でカウント数と変化量を設定します。<br/>
     * ファイルが存在しない場合は、カウント数を 0、変化量を 1 に設定します。<br/>
     * ファイル内容の解析にに失敗した場合、例外 java.lang.IllegalStateException をスローします。
     *
     * @param file ファイルパス
     * @param isAuto true のとき自動保存する、falseのとき自動保存しない
     * @throws IllegalStateException ファイル内容の解析にに失敗した場合
     */
    public FileCounter(File file, boolean isAuto) {
    	super(0, 1);
    	this.file = file;
    	this.isAuto = isAuto;
    	int[] fileValues = new int[2];
        BufferedReader in = null;

        try {
        	if (file.exists()) {
        		in = new BufferedReader(new FileReader(file));
        		String line = in.readLine();
        		String[] countUnitStr = line.split(":");
        		fileValues[0] = Integer.parseInt(countUnitStr[0]);
        		fileValues[1] = Integer.parseInt(countUnitStr[1]);
        		super.setUnit(fileValues[0]);
        		super.inc(); 	
        		super.setUnit(fileValues[1]); 
        		in.close();
        	}
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new java.lang.IllegalStateException();
    	}
    }


    /**
     * 自動ファイル保存状態のときカウント数を増やした後、すぐにファイル書き込みを行います。<br/>
     * 自動ファイル保存状態でないとき、ファイルに書き込みを行いません。<br/>
     * ファイル書き込みに失敗したとき、例外 java.lang.IllegalStateException をスローします。
     *
     * @return このオブジェクトの参照
     * @throws IllegalStateException ファイル書き込みに失敗したとき
     */
    @Override
    public Counter inc() {
    	try {
    		super.inc();
            if (isAuto) {
            	FileWriter file = new FileWriter(this.file);
            	PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            	pw.println(super.toString());
                pw.close();
            }
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IllegalStateException("ファイルの書き込みに失敗");
    	}
        return this;
    }

    /**
     * 自動ファイル保存状態のときカウント数を減らした後、すぐにファイル書き込みを行います。<br/>
     * 自動ファイル保存状態でないとき、ファイルに書き込みを行いません。<br/>
     * ファイル書き込みに失敗したとき、例外 java.lang.IllegalStateException をスローします。
     *
     * @return このオブジェクトの参照
     * @throws IllegalStateException ファイル書き込みに失敗したとき
     */
    @Override
    public Counter dec() {
    	try {
    		super.dec();
            if (isAuto) {
            	FileWriter file = new FileWriter(this.file);
            	PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            	pw.println(super.toString());
                pw.close();
            }
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new IllegalStateException("ファイルの書き込みに失敗");
    	}
        return this;
    }


    /**
     * 現在のカウント数と変化量をファイルに書き込みを行います。<br/>
     * ファイル書き込みに失敗したとき、例外 java.lang.IllegalStateException をスローします。
     *
     * @throws IllegalStateException ファイル書き込みに失敗したとき
     */
    public void save() {
    	try {
    		FileWriter file = new FileWriter(this.file);
    		PrintWriter pw = new PrintWriter(file);
    		pw.print(super.toString());
    		pw.close();
    	} catch (IOException e) {
    		throw new IllegalStateException("ファイル書き込みに失敗");
    	}
    }

    /**
     * ファイルからカウント数と変化量を読み込み、設定します。<br/>
     * ファイル内容の解析に失敗した場合、例外 java.lang.IllegalStateException をスローします。
     *
     * @throws IllegalStateException ファイル内容の解析に失敗した場合
     */
    public void load() {
    	try {
    		int[] fileValues = new int[2];
    		BufferedReader in = null;
        
    		in = new BufferedReader(new FileReader(this.file));
    		String line = in.readLine();
    		String[] countUnitStr = line.split(":");
    		fileValues[0] = Integer.parseInt(countUnitStr[0]);
    		fileValues[1] = Integer.parseInt(countUnitStr[1]);
    		super.setUnit(fileValues[0]);
    		super.inc(); 	
    		super.setUnit(fileValues[1]); 
    		in.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new java.lang.IllegalStateException("ファイル内容の解析に失敗");
    	}
    }

    /**
     * 出力先または読込先のファイルパスを取得します。
     * @return 出力先または読込先のファイルパス
     */
    public File getFile() {
        return this.file;
    }

    /**
     * 出力先または読込先のファイルパスを設定します。
     * @param file 出力先または読込先のファイルパス
     */
    public void setFile(File file) {
    	this.file = file;
    }

    /**
     * 自動ファイル保存状態を取得します。</br>
     * true のとき自動ファイル保存する／false のとき自動ファイル保存しない。
     * @return 自動ファイル保存状態
     */
    public boolean isAuto() {
    		return isAuto;
    }
    
    /**
     * 自動ファイル保存状態を設定します。</br>
     * true のとき自動ファイル保存する／false のとき自動ファイル保存しない。
     * @param isAuto 自動ファイル保存状態
     */
    public void setAuto(boolean isAuto) {
    	this.isAuto = isAuto;
    	
    }

    /**
     * 動作確認のための実行メソッドです。
     * @param args
     */
    public static void main(String[] args) {

    }
}
