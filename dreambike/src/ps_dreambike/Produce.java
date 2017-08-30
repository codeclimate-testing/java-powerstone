package ps_dreambike;

/**
 * @hibernate.class table="DB_PRODUCE"
 * <p>Title: dreambike</p>
 * <p>Description: powerstone demo</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: jcreatsoft</p>
 * @author daquanda
 * @version 1.0
 */
public class Produce {
  Long produceID = new Long( -1);
  BikeOrder order;
  String producePlan;
  /**
   * @hibernate.id column="PK_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getProduceID() {
    return produceID;
  }

  /**
   * @hibernate.property
   * 		column="VC_PRODUCE_PLAN"
   * 		length="1000"
   * 		type="string"
   * @return String
   */
  public String getProducePlan() {
    return producePlan;
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

  public void setProduceID(Long produceID) {
    this.produceID = produceID;
  }

  public void setProducePlan(String producePlan) {
    this.producePlan = producePlan;
  }
}
