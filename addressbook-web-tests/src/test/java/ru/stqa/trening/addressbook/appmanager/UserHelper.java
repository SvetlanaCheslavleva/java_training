package ru.stqa.trening.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserHelper extends HelperBase{


  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }



  public void submitUserCreation() {
    click(By.xpath("//input[21]"));
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getUser_firstname());
    type(By.name("lastname"), userData.getUser_lastname());
    type(By.name("address"), userData.getAddress());
    type(By.name("mobile"), userData.getPhone());
    type(By.name("email"), userData.getEmail());


    if (creation){
      if (userData.getGroup() != null){
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
      }
    }else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initUserCreation() {
    click(By.linkText("add new"));
  }

  public void initUserModification(int id) {
    click(By.xpath("//a[@href='edit.php?id=" + id +"']"));  // //a[@href='edit.php?id=99']
  }

  public void selectUserById(int id)  {
    wd.findElement(By.cssSelector("input[value='" + id +"']")).click();  // "input[value='99']"
  }

  public void closeAlertPresent(){
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedUser() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void deleteModificationUser() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void submitUserModification() {
    click(By.xpath("//input[22]"));
  }

  public void create(UserData user) {
    initUserCreation();
    fillUserForm(user, true);
    submitUserCreation();
    userCache = null;
    homePage();
  }

  public void modify(UserData user) {
    initUserModification(user.getId());
    fillUserForm(user, false);
    submitUserModification();
    userCache = null;
    homePage();
  }

  public void delete(UserData user) {
    selectUserById(user.getId());
    deleteSelectedUser();
    closeAlert();
    pause();
    userCache = null;
  }

  public boolean isThereAUser() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public String findGroupNameInList(String groupName) {
    initUserCreation();
    Select dropdownGroup = new Select(wd.findElement(By.name("new_group")));
    String userGroupName = null;
    for (WebElement element: dropdownGroup.getOptions()) {
      if (element.getText().equals(groupName)) {
        userGroupName = groupName;
        break;
      }
    }
    return userGroupName;
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void pause() {
    try {
      Thread.yield();
      Thread.sleep(5000);
      Thread.yield();
    } catch (InterruptedException e){
      Thread.yield();
    }
  }

  private Users userCache = null;

  public Users all() {
    if (userCache != null){
      return new Users(userCache);
    }
    userCache = new Users();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
    for (WebElement element: elements) {
      List<WebElement> userParametrs = element.findElements(By.xpath(".//td"));
      String firstName = userParametrs.get(2).getText();
      String lastName = userParametrs.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      userCache.add(new UserData().withId(id).withUser_firstname(firstName).withUser_lastname(lastName));
    }
    return new Users(userCache);
  }

}


