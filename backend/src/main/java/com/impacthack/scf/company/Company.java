package com.impacthack.scf.company;
import com.impacthack.scf.enums.CompanyType;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "companies")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long companyId;

  @Column(name = "name")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name="companyType")
  private CompanyType companyType;

  public Company() {

  }

  public Company(String name, CompanyType companyType) {
    this.name = name;
    this.companyType = companyType;
  }

  public long getId() {
    return companyId;
  }

  public String getTitle() {
    return name;
  }

  public void setTitle(String name) {
    this.name = name;
  }

  public CompanyType getCompanyType() {
    return companyType;
  }

  public void setCompanyType(CompanyType companyType) {
    this.companyType = companyType;
  }

  @Override
  public String toString() {
    return "Company{" +
            "companyId=" + companyId +
            ", name='" + name + '\'' +
            ", companyType=" + companyType +
            '}';
  }
}
