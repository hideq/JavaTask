package jp.co.training.java.base;

/**
 * 車掌を表すクラスです。
 *
 * @author Hidenori Shima
 */
public class Conductor {

    // 電車
    private Train train;


    /**
     * 指定された Train オブジェクトを保持した Conductor（車掌）オブジェクトを構築します。
     * オブジェクトの構築がコンストラクタの役割！
     * @param train 電車
     */
    public Conductor(Train train) {
    	this.train = train;

// ここの定義はtrainのほうでやってくれるから書かずにOK
//    	train(String lineName, String[] stations);
//    	lineName = train.getLineName();
    }

    /**
     * <p>次の形式のアナウンスを行います。</p>
     * <p>＜アナウンス形式＞</p>
     * <p>
     * <dl>
     * <dt>A,現在の駅が終着一つ前の駅のとき：</dt>
     * <dd>(現在駅名)、(現在駅名)、この電車は(終着駅名)行きです。次は(終着駅名)、終点です。</dd>
     * <dt>B,現在の駅が終着駅のとき：</dt>
     * <dd>(現在駅名)、(現在駅名)、終点です。ご利用ありがとうございました。</dd>
     * <dt>C,現在の駅が上記以外の駅のとき：</dt>
     * <dd>(現在駅名)、(現在駅名)、この電車は(終着駅名)行きです。次は(次の停車駅名)です。</dd>
     * </dl>
     * </p>
     *
     * @return アナウンス文字列
     */
    public String announce() {
    	
//    	if (train.getNextStation().equals(train.getTerminalStation())) {
    	if (train.isNextTerminalStation()) {
    		return train.getCurrentStation() + "、" + train.getCurrentStation() + "、" + "この電車は" + train.getTerminalStation() + "行きです。次は" + train.getTerminalStation() + "、終点です。";
//    	} else if (train.getCurrentStation().equals(train.getTerminalStation())) {
    	} else if (train.isTerminalStation()) {
    		return train.getCurrentStation() + "、" + train.getCurrentStation() + "、終点です。ご利用ありがとうございました。";
    	} else {
    		return train.getCurrentStation() + "、" + train.getCurrentStation() + "、" + "この電車は" + train.getTerminalStation() + "行きです。次は" + train.getNextStation() + "です。";
    	}
    }

    /**
     * 動作確認用の実行メソッドです。
     * @param args
     */
    public static void main(String[] args) {
    	String[] stations = {"東京", "有楽町", "新橋", "浜松町", "田町", "高輪ゲートウェイ", "品川", "大崎"};
        Train train = new Train("山の手線", stations);
        // ここのtrainは実数値込められてる。それをコンストラクタに渡す。
        Conductor conductor = new Conductor(train);
        System.out.println(conductor.announce());
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        System.out.println(conductor.announce());
        train.goNextStation();
        train.goNextStation();
        train.goNextStation();
        System.out.println(conductor.announce());
        train.goNextStation();
        System.out.println(conductor.announce());
    }
}
