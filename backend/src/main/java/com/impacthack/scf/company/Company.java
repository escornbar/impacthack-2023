package com.impacthack.scf.company;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impacthack.scf.company.Company;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "companies")
@Inheritance(strategy = InheritanceType.JOINED)

public class Company{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name="address")
  private String address;

  @Column(name="contact_name")
  private String contactName;

  @Column(name="contact_no")
  private String contactNo;

  public Company() {

  }

  public Company(String name, String address, String contactName, String contactNo) {
    this.name = name;
    this.address = address;
    this.contactName = contactName;
    this.contactNo = contactNo;
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

  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContactNo() {
    return contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  @java.lang.Override
  public java.lang.String toString() {
    return "Company{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", contactName='" + contactName + '\'' +
            ", contactNo='" + contactNo + '\'' +
            '}';
  }
}
