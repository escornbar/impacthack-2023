package com.impacthack.scf.orderItem;

public class OrderItemDTO{


  private long orderItemId;
  private long purchaseOrder;
  private long product;
  private int orderItemQuantity;
  private double orderItemUnitPrice;

  public OrderItemDTO() {

  }

  public OrderItemDTO(long purchaseOrder, long product, int orderItemQuantity, double orderItemUnitPrice) {
    this.purchaseOrder = purchaseOrder;
    this.product = product;
    this.orderItemQuantity = orderItemQuantity;
    this.orderItemUnitPrice = orderItemUnitPrice;
  }

  public long getOrderItemId() {
    return orderItemId;
  }

  public void setOrderItemId(long orderItemId) {
    this.orderItemId = orderItemId;
  }

  public long getPurchaseOrder() {
    return purchaseOrder;
  }

  public void setPurchaseOrder(long purchaseOrder) {
    this.purchaseOrder = purchaseOrder;
  }

  public long getProduct() {
    return product;
  }

  public void setProduct(long product) {
    this.product = product;
  }

  public int getOrderItemQuantity() {
    return orderItemQuantity;
  }

  public void setOrderItemQuantity(int orderItemQuantity) {
    this.orderItemQuantity = orderItemQuantity;
  }

  public double getOrderItemUnitPrice() {
    return orderItemUnitPrice;
  }

  public void setOrderItemUnitPrice(double orderItemUnitPrice) {
    this.orderItemUnitPrice = orderItemUnitPrice;
  }
}
