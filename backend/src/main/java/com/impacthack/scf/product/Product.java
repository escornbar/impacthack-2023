package com.impacthack.scf.product;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long productId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  public Product() {

  }

  public Product(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "Product{" +
            "productId=" + productId +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
  }
}
