package com.impacthack.scf.purchaseOrderStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "purchaseOrderStatus")
public class PurchaseOrderStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  public PurchaseOrderStatus() {

  }

  public PurchaseOrderStatus(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
