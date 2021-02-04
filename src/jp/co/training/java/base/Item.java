package jp.co.training.java.base;

import java.io.Serializable;

/**
 * 商品を表すクラスです。
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer itemId;       // 商品ID
    private String itemname;      // 商品名
    private String categoryCode;  // 分類コード
    private String categoryName;  // 分類名
    private Integer price;        // 値段
    private String explanation;   // 商品説明
    private String imageName;     // 画像ファイル名
    private String originalId;    // 商品固有のID

    /**
     * 商品ID を取得します。
     *
     * @return 商品ID
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 商品ID を設定します。
     *
     * @param itemId 商品ID
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * 商品名を取得します。
     *
     * @return 商品名
     */
    public String getItemName() {
        return itemname;
    }

    /**
     * 商品名を設定します。
     *
     * @param itemname 商品名
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * 分類コードを取得します。
     *
     * @return 分類コード
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * 分類コードを設定します。
     *
     * @param code 分類コード
     */
    public void setCategoryCode(String code) {
        this.categoryCode = code;
    }

    /**
     * 分類名を取得します。
     *
     * @return 分類名
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 分類名を設定します。
     *
     * @param name 分類名
     */
    public void setCategoryName(String name) {
        this.categoryName = name;
    }

    /**
     * 値段を取得します。
     *
     * @return 値段
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 値段を設定します。
     *
     * @param price 値段
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 商品説明を取得します。
     *
     * @return 商品説明
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * 商品説明を設定します。
     *
     * @param explanation 商品説明
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * 画像ファイル名を取得します。
     *
     * @return 画像ファイル名
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * 画像ファイル名を設定します。
     *
     * @param imageName 画像ファイル名
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * 商品固有ID を取得します。
     *
     * @return 商品固有ID
     */
    public String getOriginalId() {
        return originalId;
    }

    /**
     * 商品固有IDを設定します。
     *
     * @param originalId 商品固有ID
     */
    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }
}
