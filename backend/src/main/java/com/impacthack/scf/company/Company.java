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
  @Column(name="company_type")
  private CompanyType companyType;

  @Column(name="contact_person")
  private String contactPerson;

  public Company() {

  }

  public Company(String name, CompanyType companyType, String contactPerson) {
    this.name = name;
    this.companyType = companyType;
    this.contactPerson = contactPerson;
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

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "Company{" +
            "companyId=" + companyId +
            ", name='" + name + '\'' +
            ", companyType=" + companyType +
            ", contactPerson='" + contactPerson + '\'' +
            '}';
  }
}
