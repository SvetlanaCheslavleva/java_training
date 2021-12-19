package ru.stqa.trening.addressbook.model;

import java.util.Objects;

public class UserData {

  private int id = Integer.MAX_VALUE;
  private String user_firstname;
  private String user_lastname;
  private String address;
  private String phone;
  private String email;
  private String group;


  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUser_firstname(String user_firstname) {
    this.user_firstname = user_firstname;
    return this;
  }

  public UserData withUser_lastname(String user_lastname) {
    this.user_lastname = user_lastname;
    return this;
  }

  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public UserData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withGroup(String group) {
    this.group = group;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getUser_firstname() {
    return user_firstname;
  }

  public String getUser_lastname() {
    return user_lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }


  @Override
  public String toString() {
    return "UserData{" +
            "id='" + id + '\'' +
            ", user_firstname='" + user_firstname + '\'' +
            ", user_lastname='" + user_lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return Objects.equals(user_firstname, userData.user_firstname) && Objects.equals(user_lastname, userData.user_lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user_firstname, user_lastname);
  }

}
