package com.impacthack.scf.distributor;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.company.Company;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "distributors")
@AttributeOverride(name = "name", column = @Column(name = "name"))
@AttributeOverride(name = "address", column = @Column(name = "address"))
@AttributeOverride(name = "contactName", column = @Column(name = "contact_name"))
@AttributeOverride(name = "contactNo", column = @Column(name = "contact_no"))
public class Distributor extends Company{

  public Distributor() {
    super();
  }

  public Distributor(String name, String address, String contactName, String contactNo) {
    super(name, address, contactName, contactNo);
  }
}