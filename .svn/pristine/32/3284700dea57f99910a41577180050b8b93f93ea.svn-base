package ps_dreambike;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DreambikeManagerImpl
    extends HibernateDaoSupport
    implements DreambikeManager {
  public void saveOrder(BikeOrder bikeOrder) {
    getHibernateTemplate().saveOrUpdate(bikeOrder);
  }

  public BikeOrder getOrder(String orderID) {
    return (BikeOrder) getHibernateTemplate().load(BikeOrder.class,
        new Long(orderID));
  }

  public void saveProduce(Produce produce) {
    getHibernateTemplate().saveOrUpdate(produce);
  }

  public Produce getProduce(String produceID) {
    return (Produce) getHibernateTemplate().load(Produce.class,
                                                 new Long(produceID));
  }

  public void savePurchase(Purchase purchase) {
    getHibernateTemplate().saveOrUpdate(purchase);
  }

  public Purchase getPurchase(String purchaseID) {
    return (Purchase) getHibernateTemplate().load(Purchase.class,
                                                  new Long(purchaseID));
  }

  public BikeOrder order(BikeOrder bikeOrder) {
    saveOrder(bikeOrder);
    return bikeOrder;
  }

  public BikeOrder price(BikeOrder bikeOrder) {
    BikeOrder result = getOrder(bikeOrder.getOrderID().toString());
    result.setPrice(bikeOrder.getPrice());
    result.setPriceDetail(bikeOrder.getPriceDetail());
    saveOrder(result);
    return result;
  }

  public BikeOrder tech(BikeOrder bikeOrder) {
    BikeOrder result = getOrder(bikeOrder.getOrderID().toString());
    result.setTechState(bikeOrder.getTechState());
    result.setTechDetail(bikeOrder.getTechDetail());
    saveOrder(result);
    return result;
  }

  public BikeOrder stock(BikeOrder bikeOrder) {
    BikeOrder result = getOrder(bikeOrder.getOrderID().toString());
    result.setStockState(bikeOrder.getStockState());
    result.setStockDetail(bikeOrder.getStockDetail());
    saveOrder(result);
    return result;
  }

  public Purchase planPurch(String bikeOrderID, Purchase purchase) {
    BikeOrder order = getOrder(bikeOrderID);
    purchase.setOrder(order);
    savePurchase(purchase);
    order.setPurchase(purchase);
    saveOrder(order);
    return purchase;
  }

  public Produce planProduce(String bikeOrderID, Produce produce) {
    BikeOrder order = getOrder(bikeOrderID);
    produce.setOrder(order);
    saveProduce(produce);
    order.setProduce(produce);
    saveOrder(order);
    return produce;
  }

  public BikeOrder emailRefuse(BikeOrder bikeOrder) {
    BikeOrder result = getOrder(bikeOrder.getOrderID().toString());
    result.setEmailRefuse(bikeOrder.getEmailRefuse());
    saveOrder(result);
    return result;
  }

  public BikeOrder emailReceiveNote(BikeOrder bikeOrder) {
    BikeOrder result = getOrder(bikeOrder.getOrderID().toString());
    result.setEmailReceiveNote(bikeOrder.getEmailReceiveNote());
    saveOrder(result);
    return result;
  }
}
