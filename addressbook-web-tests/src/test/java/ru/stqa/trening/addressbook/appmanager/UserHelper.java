package ru.stqa.trening.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserHelper extends HelperBase{


  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
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

  public void initUserModification(int index) {
       wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void selectUser(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

  public void createUser(UserData user) {
    initUserCreation();
    fillUserForm(user, true);
    submitUserCreation();
    returnToHomePage();
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

  public int getUserCount() {
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

  public List<UserData> getUserList() {
    List<UserData> users = new ArrayList<UserData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
    for (WebElement element: elements) {
      List<WebElement> userParametrs = element.findElements(By.xpath(".//td"));
      String firstName = userParametrs.get(2).getText();
      String lastName = userParametrs.get(1).getText();
      String id = element.findElement(By.tagName("input")).getAttribute("value");
      UserData user = new UserData(id, firstName, lastName, null, null, null, null);
      users.add(user);
    }
    return users;
  }
}


