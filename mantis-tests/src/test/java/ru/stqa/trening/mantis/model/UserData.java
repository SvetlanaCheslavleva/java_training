package ru.stqa.trening.mantis.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;



@Entity
@Table(name = "mantis_user_table")

public class UserData {

  @Id
  @Column(name = "id")
  private int id;
  //private int id = Integer.MAX_VALUE;

  @Column(name = "username")
  private String username;

  //@Column(name = "realname")
  //private String realname;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;



  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

 /* public UserData withRealname(String realname) {
    this.realname = realname;
    return this;
  }*/

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }



  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

 /* public String getRealname() {
    return realname;
  }*/


  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserData userData = (UserData) o;
    return id == userData.id && Objects.equals(username, userData.username) && Objects.equals(email, userData.email) && Objects.equals(password, userData.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, password);
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            '}';
  }

}
