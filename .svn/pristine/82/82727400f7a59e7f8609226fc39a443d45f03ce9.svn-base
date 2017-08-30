package ps_dreambike;
/**
 * @hibernate.class table="DB_PURCHASE"
 * <p>Title: dreambike</p>
 * <p>Description: powerstone demo</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: jcreatsoft</p>
 * @author daquanda
 * @version 1.0
 */
public class Purchase {
  Long purchaseID = new Long( -1);
  BikeOrder order;
  String purchasePlan;
  /**
   * @hibernate.id column="PK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getPurchaseID() {
    return purchaseID;
  }

  /**
   * @hibernate.property
   * 		column="VC_PURCHASE_PLAN"
   * 		length="1000"
   * 		type="string"
   * @return String
   */
  public String getPurchasePlan() {
    return purchasePlan;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_ORDER_ID"
   * class="ps_dreambike.BikeOrder"
   * unique="true"
   * @return BikeOrder
   */
  public BikeOrder getOrder() {
    return order;
  }

  public void setOrder(BikeOrder order) {
    this.order = order;
  }

  public void setPurchasePlan(String purchasePlan) {
    this.purchasePlan = purchasePlan;
  }
  public void setPurchaseID(Long purchaseID) {
    this.purchaseID = purchaseID;
  }
}
