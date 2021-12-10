package ru.stqa.trening.addressbook.model;

public class UserData {
  private final String user_firstname;
  private final String user_lastname;
  private final String address;
  private final String phone;
  private final String email;
  private String group;

  public UserData(String user_firstname, String user_lastname, String address, String phone, String email, String group) {
    this.user_firstname = user_firstname;
    this.user_lastname = user_lastname;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
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
}
