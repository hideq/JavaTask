package jp.co.training.java.base;

/**
 * 数値のカウントを行うカウンタのクラスです。
 *
 * @author Hidenori Shima
 */
public class Counter {

	// カウント数
    private int count;

    // １カウントで変化する数（変化量）
    private int unit;

    /**
     * カウント数を 0、変化量を 1 で初期化したCounterオブジェクトを構築します。
     */
    public Counter() {
    	this.count = 0;
    	this.unit = 1;
    }

    /**
     * カウント数、変化量をそれぞれ指定した値で初期化したCounterオブジェクトを構築します。
     *
     * @param count カウント数
     * @param unit 変化量
     */
    public Counter(int count, int unit) {
    	this.count = count;
    	this.unit = unit;
    }

    /**
     * 現在のカウント数を取得します。
     *
     * @return カウント数
     */
    public int getCount() {
        return this.count;
    }


    /**
     * 現在の変化量を取得します。
     *
     * @return カウント数
     */
    public int getUnit() {
        return this.unit;
    }


    /**
     * 変化量を指定した値にします。
     *
     * @param unit 変化量
     */
    public void setUnit(int unit) {
    	this.unit = unit;
    }


    /**
     * 現在のカウント数に変化量分増やします。
     *
     * @return このオブジェクトへの参照
     */
    public Counter inc() {
    	this.count += this.unit;
    	return this;
        //
    }

    /**
     * 現在のカウント数に変化量分減らします。
     *
     * @return このオブジェクトへの参照
     */
    public Counter dec() {
        this.count -= this.unit;
    	return this;
    }

    /**
     * 現在のカウント数が a 以上 b 以下のとき true, そうでないとき false を返します。
     *
     * @param a 最小値
     * @param b 最大値
     */
    public boolean between(int a, int b) {
//        boolean result;
//        if (this.count >= a && this.count <= b) {
//        	result = true;
//        } else {
//        	result = false;
//        }
    	return this.count >= a && this.count <= b;
    }
    
    // return  


    /**
     * オブジェクトの文字列表現を返します。
     * 文字列表現の書式は「カウンタ値:変化量」です。
     * 例えば、カウント数が「123」、変化量が「3」のオブジェクトの文字列表現は「123:3」です。
     *
     * @return オブジェクトの文字列表現
     */
    @Override
    public String toString() {
    	return this.count + ":" + this.unit;
    }


    /**
     * 引数で指定されたCounterオブジェクトの文字列表現（カウンタ値:変化量）を解析し、
     * カウント数、変化量を設定します。
     * 解析に失敗した場合、例外IllegalArgumentExceptionをスローします。
     *
     * @param objStr Counterオブジェクトの文字列表現
     *
     * @throws IllegalArgumentException 解析に失敗した場合
     */
    public void parse(String objStr) {
    	try {
    		if (objStr == null) {
    			throw new IllegalArgumentException("入力値が存在しません");
    		}
    		String[] nums = objStr.split(":");
    		if (nums.length == 1) {
 				throw new IllegalArgumentException("入力値が不足しています");
		}
   			this.count = Integer.parseInt(nums[0]);
 			this.unit = Integer.parseInt(nums[1]);
    	// 数字以外が引数に含まれていた場合はエラーを返す
    	} catch (NumberFormatException e) {
    		throw new IllegalArgumentException("Analysis failed");

//		 引数の要素数が1つの場合はエラーを返す
//    	} catch (ArrayIndexOutOfBoundsException e) {
//    		throw new IllegalArgumentException("Analysis failed");
    	}
    }




    /**
     * 動作確認用の実行メソッドです。
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
    	Counter c1 = new Counter();
    	Counter c2 = new Counter(123,3);
    	System.out.println(c1.count + ":" + c1.unit);
    	System.out.println(c2.count + ":" + c2.unit);
    	System.out.println(c2.getCount());
    	System.out.println(c2.getUnit());
    	c2.setUnit(5);
    	System.out.println(c2.getUnit());
    	System.out.println(c2.inc());
//    	System.out.println(c2.inc().inc());
    	c2.setUnit(10);
    	System.out.println(c2.dec());
    	System.out.println(c2.between(120, 125));
    	System.out.println(c2.toString());
    	c2.parse("140");
    	System.out.println(c2.count + ":" + c2.unit);

    }
}