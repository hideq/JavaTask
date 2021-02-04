package jp.co.training.java.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ショッピングカートを表すクラスです。
 *
 * @author Hidenori Shima
 */
public class Cart {

    /**
     * カート内のすべての商品データ。
     * キー（Key）が商品IDで、値（Value）が CartItem オブジェクト。
     */
    private Map<Integer, CartItem> cartData;

    /**
     * 新しいショッピングカートを作成します。
     */
    public Cart() {
    	this.cartData = new HashMap<Integer, CartItem>();
    }

    /**
     * カートに商品を追加します。<br/>
     * 既にカートに追加済みの商品であれば数量を加算します。<br/>
     * 引数 item が null または amount が負数の場合、IllegalArgumentException がスローされます。
     *
     * @param item 商品
     * @param amount 数量
     *
     * @throws IllegalArgumentException 引数 item が null または amount が負数の場合
     */
    public void add(Item item, int amount) {

    	if (item == null) {
    		throw new IllegalArgumentException("item is ull");
    	}
    	else if (amount < 0) {
    		throw new IllegalArgumentException("amount is negative");
    	}
    	// item already exsit, add amount
    	if (cartData.containsKey(item.getItemId())) {
    		// 現状ではCartItem(itemId, amount)クラスにアクセス手段がない。
    		// if文から既存の商品であることがわかる。→itemが突破口に！
    		// インスタンス化しているHashMapが使用可！HashMapインスタンス.get(key)でvalue値持ってくる。
    		//ちなみにkeyはitemインスタンス.getItemId()メソッドを使う。
    		//この持ってきたValueがCartItem(item情報群と個数)。商品IDというkeyでitem情報群と個数というValueにアクセスできる。
    		//目的であるカート(Hasmap)に入っている既存商品情報の個数部分をadd(増やしたい個数)で
    		//増産完了
    		cartData.get(item.getItemId()).addAmount(amount);
    	}
    	else {
    	//add item to HashMap
    		CartItem cartItem = new CartItem(item, amount);
    		this.cartData.put(item.getItemId(), cartItem);
    	}
    }

    /**
     * カート内の商品の数量を加算します。
     * amount が負数の場合、IllegalArgumentException がスローされます。
     * 指定された商品が存在しない場合は何もしません。
     *
     * @param itemId 商品ID
     * @param amount 追加数量
     */
    public void addAmount(Integer itemId, int amount) {
    	if (amount < 0) {
    		throw new IllegalArgumentException("amount is negative");
    	}
    	if (cartData.containsKey(itemId)) {
//    		cartData.put(itemId, cartData.get(itemId).addAmount(amount));
    		cartData.get(itemId).addAmount(amount);
    	}
    	else {
    		;
    	}
    }

    /**
     * カート内の商品の数量を取得します。
     * カート内に指定された商品IDが見つからない場合、-1 を返します。
     *
     * @param itemId 商品ID
     *
     * @return カート内の商品の数量
     */
    public int getAmount(Integer itemId) {
    	if (cartData.containsKey(itemId)) {
    		return cartData.get(itemId).getAmount();
    	}
    	else {
    	return -1;
    	}
    }

    /**
     * カート内の商品の数量を設定します。<br/>
     * 設定数量が 0 の場合、カートから商品を削除します。<br/>
     * amount が負数の場合、IllegalArgumentException がスローされます。<br/>
     * 指定された商品が存在しない場合は何もしません。
     *
     * @param itemId 商品ID
     * @param amount 設定数量
     *
     * @throws IllegalArgumentException amount が負数の場合
     */
    public void setAmount(Integer itemId, int amount) {
    	if (amount == 0) {
    		cartData.remove(itemId);
    	}
    	// error handling
    	else if (amount < 0) {
    		throw new IllegalArgumentException("Oh, you get a error");
    	}
    	if (cartData.containsKey(itemId)) {
    		cartData.get(itemId).setAmount(amount);
    	}
    	else {
    		;
    	}
    }

    /**
     * 指定された商品IDの商品がカート内に存在するかどうかを調べます。
     *
     * @param itemId 商品ID
     *
     * @return 商品IDが存在する場合はtrue 存在しない場合はfalse
     */
    public boolean hasItem(Integer itemId) {
        if (cartData.containsKey(itemId)) {
        	return true;
        }
        else {
    	return false;
        }
    }

    /**
     * カート内のすべての商品IDを数え上げる反復子を取得します。 ※iteratorのこと
     *
     * @return カート内のすべての商品IDを数え上げる反復子
     */
    public Iterator<Integer> itemIdIterator() {
//     Iterator<Integer> it = new Iterator();
    	// KeySetのSetはSet型にする的な意味
    	// iterator()メソッドは、前から順に取得する機能を持ったオブジェクトに変換してくれる
    	//（iterator()メソッドで順次前から取得。）
        Iterator<Integer> it = cartData.keySet().iterator();
    	return it;
    }

    /**
     * カート内の商品のリストを取得します。
     * @return カート内の商品のリスト
     */
    public List<CartItem> getCartItemList() {
    	List<CartItem> list = new ArrayList<CartItem>();
    	Iterator<Integer> it = itemIdIterator();
    	// hasNext()は次の要素があるか確認。移動はしてない。
    	while(it.hasNext()) {
    		 //現在のidが何だったかをnextが吐いてそれを代入。itが一段階移動します。
    		//せろてーぷ引っ張るhas、切り取るnext。それを代入した。一方通行。iteratorの特徴
    	        Integer cartDataKey = it.next();
    	        CartItem cartDataVal = cartData.get(cartDataKey);
    	        //cartItemを代入
    	        list.add(cartDataVal);
    	 }
    	return list;
    }

    /**
     * 指定された商品IDのカート内の商品情報を取得します。<br/>
     * 指定された商品が存在しない場合は null を返します。
     *
     * @param itemId 商品ID
     *
     * @return 商品IDのカート内の商品情報
     */
    public CartItem getCartItem(Integer itemId) {
    	if (cartData.containsKey(itemId)) {
    		return cartData.get(itemId);
    	}
        else {
        	return null;
        }
    }

    /**
     * カート内商品の総合計金額を計算します。
     * @return カート内商品の総合計金額
     */

    public int calcTotalSum() {
    	// itemId >>Item price * CartItem.amount
    	List<CartItem> cartItemList = getCartItemList();
    	// key list 取ってきてループ回す。
//    	int tmp = 0;
    	int result = 0;
    	for (int i = 0; i < cartItemList.size(); i++) {
//    		tmp =cartItemList.get(i).getItem().getPrice()  * cartItemList.get(i).getAmount();
//    		result += tmp;
    		// 取得してきたcartItemList一覧から、一(=i)商品目の情報get＝CartItem(パソコン情報群、数)に侵入
    		//CartItemの持つ変数のitemに侵入したいので、そのクラスのgetItemメソッドで侵入
    		//Itemクラスの持つpriceをgetPriceメソッドで取得！
    		// Goalはリストn行目商品のItemクラスにあるPriceを引っ張ってきたい。
    		//result +=cartItemList.get(i).getItem().getPrice()  * cartItemList.get(i).getAmount();
    		// 取得したものがcartItemなので、calcTotalPriceでOK！
    		result += cartItemList.get(i).calcTotalPrice();
    	}
    	return result;
    }

    /**
     * カートの内容をクリアします。
     */
    public void clear() {
    	cartData.clear();
    }

    /**
     * カート内に追加した商品の数を取得します。<br/>
     * 各商品の数量のことでありません。
     *
     * @return カート内に追加した商品の数
     */
    public int size() {
        return cartData.size();
    }

    /**
     * 指定された商品をカートから削除します。<br/>
     * 存在しない商品の場合は何もしません。
     *
     * @param itemId 商品ID
     */
    public void removeItem(Integer itemId) {
    	if (cartData.containsKey(itemId)) {
    		cartData.remove(itemId);
    	}
    }

    /**
     * 動作確認のための実行メソッドです。
     * @param args
     */
    public static void main(String[] args) {
    		Cart cart = new Cart();
    		Item item1, item2, item3;
            item1 = new Item();
            item1.setItemId(100);
            item1.setItemname("プログラミング作法");
            item1.setPrice(2800);
            item1.setCategoryCode("B3");
            item1.setCategoryName("書籍");
            item1.setExplanation("説明_100");
            item1.setImageName("image-B3TPP.jpg");
            item1.setOriginalId("ISBN-7561-3649-4");

            item2 = new Item();
            item2.setItemId(200);
            item2.setItemname("ソフトウェア・テストの技法");
            item2.setPrice(3200);
            item2.setCategoryCode("B3");
            item2.setCategoryName("書籍");
            item2.setExplanation("説明_200");
            item2.setImageName("image-B3SWT.jpg");
            item2.setOriginalId("ISBN-7649-0329-6");

            item3 = new Item();
            item3.setItemId(300);
            item3.setItemname("「教え上手」になるためのスキル");
            item3.setPrice(1500);
            item3.setCategoryCode("B3");
            item3.setCategoryName("書籍");
            item3.setExplanation("説明_300");
            item3.setImageName("image-B3LRG.jpg");
            item3.setOriginalId("ISBN-86063-062-9");

            cart.add(item1, 3);
            cart.add(item2, 5);
            cart.add(item1, 2);



    }
}
