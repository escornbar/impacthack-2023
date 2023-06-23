package com.impacthack.scf.loanType;

import jakarta.persistence.*;

@Entity
@Table(name = "LoanType")
public class LoanType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  public LoanType() {

  }

  public LoanType(String name) {
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

  @Override
  public String toString() {
    return "LoanType{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
