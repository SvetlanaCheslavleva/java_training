package ru.stqa.trening.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("user")
@Entity
@Table(name = "addressbook")

public class UserData {
  @XStreamOmitField
  @Id
  @Column(name = "Id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String userFirstname;

  @Expose
  @Column(name = "lastname")
  private String userLastname;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Expose
  @Transient
  private String homePhone2;

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Transient
  private String email;

  @Expose
  @Transient
  private String email2;

  @Expose
  @Transient
  private String email3;

  @Expose
  @Transient
  private String allEmails;

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @Expose
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

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

  public UserData withHomePhone2(String homePhone2) {
    this.homePhone2 = homePhone2;
    return this;
  }

  public UserData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public UserData withAddress(String address) {
    this.address = address;
    return this;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }


  public UserData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }


  public UserData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public UserData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public UserData withPhoto(File photo) {
    this.photo = photo.getPath();
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


  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getHomePhone2() {
    return homePhone2;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    } else {
      return null;
    }
 }


  public Groups getGroups() {
    return new Groups(groups);
  }

  public UserData inGroup(GroupData group) {
    groups.add(group);
    return this;
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
