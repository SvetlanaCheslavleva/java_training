package ru.stqa.trening.addressbook.model;

import java.util.Objects;

public class UserData {

  private int id = Integer.MAX_VALUE;
  private String userFirstname;
  private String userLastname;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String allPhones;
  private String email;
  private String group;


  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUserFirstname(String userFirstname) {
    this.userFirstname = userFirstname;
    return this;
  }

  public UserData withUserLastname(String userLastname) {
    this.userLastname = userLastname;
    return this;
  }

  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public UserData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public UserData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public UserData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
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

  public String getUserFirstname() {
    return userFirstname;
  }

  public String getUserLastname() {
    return userLastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getAllPhones() {
    return allPhones;
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
            ", user_firstname='" + userFirstname + '\'' +
            ", user_lastname='" + userLastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id && Objects.equals(userFirstname, userData.userFirstname) && Objects.equals(userLastname, userData.userLastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userFirstname, userLastname);
  }

}
