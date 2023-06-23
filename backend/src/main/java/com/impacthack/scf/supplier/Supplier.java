package com.impacthack.scf.supplier;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.company.Company;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "suppliers")
@AttributeOverride(name = "name", column = @Column(name = "name"))
@AttributeOverride(name = "address", column = @Column(name = "address"))
@AttributeOverride(name = "contactName", column = @Column(name = "contact_name"))
@AttributeOverride(name = "contactNo", column = @Column(name = "contact_no"))
public class Supplier extends Company{

  public Supplier() {
    super();
  }

  public Supplier(String name, String address, String contactName, String contactNo) {
    super(name, address, contactName, contactNo);
  }
}