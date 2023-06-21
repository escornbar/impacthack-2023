package com.impacthack.scf.invoiceStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "invoiceStatus")
public class InvoiceStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  public InvoiceStatus() {

  }

  public InvoiceStatus(String name) {
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
    return "InvoiceStatus{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
