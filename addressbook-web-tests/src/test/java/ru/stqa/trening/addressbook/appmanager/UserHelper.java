package ru.stqa.trening.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.trening.addressbook.model.UserData;

public class UserHelper extends HelperBase{


  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitUserCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillUserForm(UserData userData) {
    type(By.name("firstname"), userData.getUser_firstname());
    type(By.name("lastname"), userData.getUser_lastname());
    type(By.name("address"), userData.getAddress());
    type(By.name("mobile"), userData.getPhone());
    type(By.name("email"), userData.getEmail());
  }

  public void initUserCreation() {
    click(By.linkText("add new"));
  }

  public void gotoUserPage() {
    click(By.linkText("home"));
  }

  public void initUserModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[4]/td[8]/a/img"));
  }

  public void deleteModificationUser() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void submitUserModificatoin() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }
}

