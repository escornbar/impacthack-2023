package com.impacthack.scf.orderTrackingStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderTrackingStatus")
public class OrderTrackingStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  public OrderTrackingStatus() {

  }

  public OrderTrackingStatus(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "PurchaseOrderStatus{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
