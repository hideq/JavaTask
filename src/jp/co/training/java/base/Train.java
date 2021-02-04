package jp.co.training.java.base;

/**
 * 電車を表すクラスです。
 *
 * @author Hidenori Shima
 */
public class Train {

    // 路線の駅名配列
    private String[] stations;

    // 現在駅の位置（路線「stations」の添え字）
    private int currentIndex;

    // 路線名
    private String lineName;

    /**
     * 指定した路線の Train（電車）オブジェクトを構築します。<br/>
     * 現在の駅は始発駅に初期化されます。<br/>
     * stationsがnullまたは2駅未満の場合はIllegalArgumentExceptionをスローします。
     *
     * @param lineName 路線名
     * @param stations 路線の駅名配列
     * 
     * @throws IllegalArgumentException stationsがnullまたは2駅未満の場合
     */
    public Train(String lineName, String[] stations) {
    	this.lineName = lineName;
    	this.stations = stations;
    	this.currentIndex = 0;
    	if (stations == null) {
    		throw new IllegalArgumentException("staions[] is null");
    	}
    	if (stations.length < 2) {
    		throw new IllegalArgumentException("stations[] is only one station");
    	}
    }

    /**
     * 路線名を取得します。
     * @return 路線名
     */
    public String getLineName() {
    	return this.lineName;
    }

    /**
     * 現在の駅名を返します。
     * @return 現在の駅名
     */
    public String getCurrentStation() {
        return this.stations[currentIndex];
    }

    /**
     * 次の駅名の文字列を返します。<br/>
     * 現在の駅が終着駅の場合は、nullを返します。
     *
     * @return 次の駅名の文字列
     */
    public String getNextStation() {
    	if (currentIndex == stations.length - 1) {
    		return null;
    	} else {
    		return this.stations[currentIndex + 1];    		
    	}
    }

    /**
     * 始発駅の駅名の文字列を返します。
     * @return 始発駅の駅名の文字列
     */
    public String getStartingStation() {
        return stations[0];
    }

    /**
     * 終着駅の駅名の文字列を返します。
     * @return 執着駅の駅名の文字列
     */
    public String getTerminalStation() {
        return stations[stations.length - 1];
    }

    /**
     * 次の駅へ移動します。<br/>
     * 現在の駅が終着駅の場合は、何もしません。
     */
    public void goNextStation() {
    	if (currentIndex < stations.length - 1) {
    		currentIndex++;   		
    	}
    }

    /**
     * 現在の駅が始発駅かどうかを判定します。
     *
     * @return 現在の駅が始発駅のとき true、それ以外 false。
     */
    public boolean isStartingStation() {
        return currentIndex == 0;
    }

    /**
     * 次の駅が終着駅かどうかを判定します。<br/>
     * 現在の駅が終着駅の場合は、false を返します。
     *
     * @return 次の駅が終着駅の場合は true、それ以外 false
     */
    public boolean isNextTerminalStation() {
        return currentIndex + 1 == stations.length - 1;
    }

    /**
     * 現在の駅が終着駅かどうかを判定します。
     *
     * @return 現在の駅が終着駅の場合は true、それ以外 false。
     */
    public boolean isTerminalStation() {
        return currentIndex == stations.length - 1;
    }
}
