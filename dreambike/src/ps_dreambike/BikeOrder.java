package ps_dreambike;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @hibernate.class table="DB_ORDER"
 * <p>Title: dreambike</p>
 * <p>Description: powerstone demo</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: jcreatsoft</p>
 * @author daquanda
 * @version 1.0
 */
public class BikeOrder {
  private Long orderID = new Long( -1);
  private String productID;
  private String customeID;
  private String customeEmail;
  private String priceDetail;
  private String price;
  private String techState;
  private String techDetail;
  private String stockState;
  private String stockDetail;
  private String emailRefuse;
  private String emailReceiveNote;
  private Produce produce;
  private Purchase purchase;

  /**
   * @hibernate.id column="PK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getOrderID() {
    return orderID;
  }

  public void setOrderID(Long orderID) {
    this.orderID = orderID;
  }

  /**
   * @hibernate.property
   * 		column="VC_PRODUCT_ID"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getProductID() {
    return productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  /**
   * @hibernate.property
   * 		column="VC_CUSTOME_ID"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getCustomeID() {
    return customeID;
  }

  public void setCustomeID(String customeID) {
    this.customeID = customeID;
  }

  /**
   * @hibernate.property
   * 		column="VC_CUSTOME_EMAIL"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getCustomeEmail() {
    return customeEmail;
  }

  public void setCustomeEmail(String customeEmail) {
    this.customeEmail = customeEmail;
  }

  /**
   * @hibernate.property
   * 		column="VC_PRICE_DETAIL"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getPriceDetail() {
    return priceDetail;
  }

  public void setPriceDetail(String priceDetail) {
    this.priceDetail = priceDetail;
  }

  /**
   * @hibernate.property
   * 		column="VC_PRICE"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  /**
   * @hibernate.property
   * 		column="VC_TECH_STATE"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getTechState() {
    return techState;
  }

  public void setTechState(String techState) {
    this.techState = techState;
  }

  /**
   * @hibernate.property
   * 		column="VC_TECH_DETAIL"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getTechDetail() {
    return techDetail;
  }

  public void setTechDetail(String techDetail) {
    this.techDetail = techDetail;
  }

  /**
   * @hibernate.property
   * 		column="VC_STOCK_STATE"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getStockState() {
    return stockState;
  }

  public void setStockState(String stockState) {
    this.stockState = stockState;
  }

  /**
   * @hibernate.property
   * 		column="VC_STOCK_DETAIL"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getStockDetail() {
    return stockDetail;
  }

  public void setStockDetail(String stockDetail) {
    this.stockDetail = stockDetail;
  }

  /**
   * @hibernate.property
   * 		column="VC_EMAIL_RECEIVE_NOTE"
   * 		length="1500"
   * 		type="string"
   * @return String
   */
  public String getEmailReceiveNote() {
    return emailReceiveNote;
  }

  /**
   * @hibernate.property
   * 		column="VC_EMAIL_REFUSE"
   * 		length="1500"
   * 		type="string"
   * @return String
   */
  public String getEmailRefuse() {
    return emailRefuse;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_PRODUCE_ID"
   * class="ps_dreambike.Produce"
   * @return Produce
   */
  public Produce getProduce() {
    return produce;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_PURCHASE_ID"
   * class="ps_dreambike.Purchase"
   * @return Purchase
   */
  public Purchase getPurchase() {
    return purchase;
  }

  public void setEmailReceiveNote(String emailReceiveNote) {
    this.emailReceiveNote = emailReceiveNote;
  }

  public void setEmailRefuse(String emailRefuse) {
    this.emailRefuse = emailRefuse;
  }

  public void setProduce(Produce produce) {
    this.produce = produce;
  }

  public void setPurchase(Purchase purchase) {
    this.purchase = purchase;
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("roleID", this.getOrderID().toString())
        .append("roleName", this.getProductID())
        .append("roleName", this.getPrice())
        .append("roleName", this.getTechState())
        .append("roleName", this.getStockState())
        .append("roleName", this.getCustomeEmail()).toString();
  }

}
