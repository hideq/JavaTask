package jp.co.training.java.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * アンケートの回答数を集計するクラスです。<br/>
 * 例えば、「好きな果物は何ですか？」という質問があり、<br/>
 *<br/>
 * Aさんの回答は、メロン<br/>
 * Bさんの回答は、イチゴ<br/>
 * Cさんの回答は、桃<br/>
 * Dさんの回答は、バナナ<br/>
 * Eさんの回答は、バナナ<br/>
 * Fさんの回答は、メロン<br/>
 * Gさんの回答は、ぶどう<br/>
 * Hさんの回答は、桃<br/>
 *<br/>
 * であったとき、以下のように回答を集計します。<br/>
 * <br/>
 * メロン(2)<br/>
 * イチゴ(1)<br/>
 * 桃(2)<br/>
 * バナナ(2)<br/>
 * ぶどう(1)
 *
 * @author Hidneori Shima
 */
public class EnqueteAnswer {

    /**
     * アンケートの回答とその回答数を格納します。<br/>
     * キーが回答内容で、キーに紐づく値が回答数です。<br/>
     * 回答数は {@link jp.co.vsn.training.java.jp.co.vsn.training.java.base.test.Counter} オブジェクトです。
     */
    private Map<String, Counter> ansCountMap;

    /**
     * 新しいアンケート回答集計オブジェクトを生成します。
     */
    public EnqueteAnswer() {
    	this.ansCountMap = new LinkedHashMap<String, Counter>();
    }

    /**
     * 回答を追加します。</br>
     * 既に追加済みの回答の場合、現在の回答数に +1 します。</br>
     * まだ追加されていない回答の場合、回答数を 1 で追加します。</br>
     * 指定した回答が null のとき、NullPointerException がスローされます。
     *
     * @param ans 回答
     * @throws NullPointerException 指定した回答が null のとき
     */
    public void add(String ans) {
    	if (ans == null) {
    		throw new NullPointerException("ans is null");
    	}
    	if (ansCountMap.containsKey(ans)) {
    		ansCountMap.get(ans).setUnit(1);
    		ansCountMap.get(ans).inc();
    	}
    	else {
    		ansCountMap.put(ans, new Counter());
    		ansCountMap.get(ans).inc();
    	}
    }

    /**
     * 指定した回答がすでに追加されているかどうかを確認します。<br/>
     * 追加されている場合は true、追加されていない場合は false。<br/>
     * 指定した回答が null のとき、NullPointerException がスローされます。
     *
     * @param ans 回答
     * @return 指定した回答がすでに追加されている場合 true 追加されていない場合 false
     * @throws NullPointerException 指定した回答が null のとき
     */
    public boolean contains(String ans) {
    	if (ans == null) {
    		throw new NullPointerException("ans is null");
    	}
    	else if (ansCountMap.containsKey(ans)) {
    		return true;
    	}
    	else {
    	return false;
    	}
    }

    /**
     * 指定した回答を削除します。<br/>
     * 回答が見つからない場合は何もしません。<br/>
     * 指定回答が null のとき、NullPointerException がスローされます。
     *
     * @param ans 回答
     * @throws NullPointerException 指定回答が null のとき
     */
    public void remove(String ans) {
    	if (ans == null) {
    		throw new NullPointerException("ans is null");
    	}
    	else if (ansCountMap.containsKey(ans)) {
    		ansCountMap.remove(ans);
    	}

    }

    /**
     * 指定回答の回答数を取得します。<br/>
     * 指定回答が存在しない場合は、IllegalArgumentException がスローされます。<br/>
     * 指定回答が null のとき、NullPointerException がスローされます。
     *
     * @param ans 回答
     * @return 指定回答の回答数
     * @throws IllegalArgumentException 指定回答が存在しない場合
     * @throws NullPointerException 指定回答が null のとき
     */
    public int getCount(String ans) {
    	if (ans == null) {
    		throw new NullPointerException("ans is null");
    	}
    	else if (!ansCountMap.containsKey(ans)) {
    		throw new IllegalArgumentException("ans is not exist");
    	}
    	else {
    		return ansCountMap.get(ans).getCount();
    	}
    }

    /**
     * 指定回答の回答数を設定します。<br/>
     * 指定回答が存在しない場合は、新しく回答を設定回答数で追加します。<br/>
     * 指定回答が null のとき、NullPointerException がスローされます。<br/>
     * また、負数が設定されたとき、IllegalArgumentException がスローされます。
     *
     * @param ans 回答
     * @param count 指定回答の回答数
     * @throws NullPointerException 指定回答が null のとき
     * @throws IllegalArgumentException 設定回答数が負数のとき
     */
    public void setCount(String ans, int count) {
    	if (ans == null) {
    		throw new NullPointerException("ans is null");
    	}
    	else if (count < 0) {
    		throw new IllegalArgumentException("count is not negative");
    	}
    	else if (!ansCountMap.containsKey(ans)) {
    		add(ans);
    		ansCountMap.get(ans).setUnit(count - 1);
    		ansCountMap.get(ans).inc();
    	}
    	else {
    		int currentCount = ansCountMap.get(ans).getCount();
    		ansCountMap.get(ans).setUnit(Math.abs(count - currentCount));
    		if (count > currentCount) {
    			ansCountMap.get(ans).inc();
    		}
    		else if (count < currentCount) {
    			ansCountMap.get(ans).dec();
    		}
    	}
    }

    /**
     * 回答リストを返します。<br/>
     * １つも回答が追加されていないときはサイズ 0 のリストを返します。
     *
     * @return 回答リスト
     */
    public List<String> getAnswerList() {
    	// list has one argument!!
    	List<String> list = new ArrayList<String>();
    	Iterator<String> it = ansCountMap.keySet().iterator();
        while (it.hasNext()) {
        	String ansCountMapKey = it.next();
//        	Counter ansCountMapValue = ansCountMap.get(ansCountMapKey);
        	list.add(ansCountMapKey);
        }
        return list;
        }

     // サイズ０のリストとは、nullではなくインスタンス化されたままのもの
     //   if (list.isEmpty()) {
     //   	;
     //   }


    /**
     * 回答の総数を取得します。
     * @return 回答の総数
     */
    public int total() {
    	List<String> answerList = getAnswerList();
    	int result = 0;
    	for (int i = 0; i < answerList.size(); i++) {
    		result += ansCountMap.get(answerList.get(i)).getCount();
    	}
    	return result;
    }

    /**
     * オブジェクトの文字列表現を返します。<br/>
     * １つも回答が追加されていないときは「none」という文字列を返します。</br>
     *
     * <dl>
     * <dt>フォーマット</dt>
     * <dd>回答#回答数:回答#回答数: ... :回答#回答数</dd>
     * <dt>例</dt>
     * <dd>メロン#2:イチゴ#1:桃#2:バナナ#2:ぶどう#1</dd>
     * </dl>
     *
     * @return オブジェクトの文字列表現
     */
    @Override
    public String toString() {
    	List<String> answerList = getAnswerList();
    	String result = "";
    	if (ansCountMap.isEmpty()) {
    		return "none";
    	}
    	// get mapkey and get mapvalue  while (keylist exist)
    	int i = 0;
    	result += answerList.get(i) + "#" + ansCountMap.get(answerList.get(i)).getCount();
    	for (i = 1; i < answerList.size(); i++) {
    		result += ":" + answerList.get(i) + "#" + ansCountMap.get(answerList.get(i)).getCount();
    	}
    	return result;
    }
    /**
     * 動作確認用の実行メソッドです。
     * @param args
     */
    public static void main(String[] args) {
    	EnqueteAnswer enqAns = new EnqueteAnswer();
    	 enqAns.add("鬼滅の刃");
         enqAns.add("デジモンアドベンチャー");
         enqAns.add("鬼滅の刃");
         enqAns.add("ONE PIECE");
         enqAns.add("バクマン");
         enqAns.add("DRAGON BALL");
         enqAns.add("銀魂");
         enqAns.add("シャーマンキング");
         enqAns.add("シャーマンキング");
         enqAns.add("ガンダムSEED");

         System.out.println("頭文字Dはリストにある？：" + enqAns.contains("頭文字D"));
         enqAns.remove("デジモンアドベンチャー");
         enqAns.getCount("鬼滅の刃");
         enqAns.setCount("鬼滅の刃", 5);
         System.out.println("-----JumpMangaRanking!!----");
         System.out.println(enqAns.getAnswerList());
         enqAns.remove("ガンダムSEED");
         enqAns.setCount("ONE PIECE", 3);
         System.out.println("total count:" + enqAns.total());
         System.out.println(enqAns.toString());
    }
}
