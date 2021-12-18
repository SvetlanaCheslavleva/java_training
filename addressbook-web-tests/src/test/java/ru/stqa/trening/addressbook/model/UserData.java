package ru.stqa.trening.addressbook.model;

import java.util.Objects;

public class UserData {
  private final String id;
  private final String user_firstname;
  private final String user_lastname;
  private final String address;
  private final String phone;
  private final String email;
  private String group;


  public UserData( String user_firstname, String user_lastname, String address, String phone, String email, String group) {
    this.id = null;
    this.user_firstname = user_firstname;
    this.user_lastname = user_lastname;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }

  public UserData(String id, String user_firstname, String user_lastname, String address, String phone, String email, String group) {
    this.id = id;
    this.user_firstname = user_firstname;
    this.user_lastname = user_lastname;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }

  public String getId() {
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return Objects.equals(id, userData.id) && Objects.equals(user_firstname, userData.user_firstname) && Objects.equals(user_lastname, userData.user_lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user_firstname, user_lastname);
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id='" + id + '\'' +
            ", user_firstname='" + user_firstname + '\'' +
            ", user_lastname='" + user_lastname + '\'' +
            '}';
  }

}
