package datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sales_contact")
public class SalesContact {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;

   @Column(name = "name")
   private String name;

   @Column(name = "address")
   private String address;

   @Column(name = "phone")
   private String phoneNumber;

   @Column(name = "email")
   private String email;

   public SalesContact() {
   }

   public SalesContact(Integer id, String name, String address, String phoneNumber, String email) {
      this.id = id;
      this.name = name;
      this.address = address;
      this.phoneNumber = phoneNumber;
      this.email = email;
   }

   public SalesContact(String name, String address, String phoneNumber, String email) {
      this.name = name;
      this.address = address;
      this.phoneNumber = phoneNumber;
      this.email = email;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   @Override
   public String toString() {
      return "SalesContact: " + this.id + ", " + this.name + ", " + this.address + ", " + this.phoneNumber + ", " + this.email;
   }
}
