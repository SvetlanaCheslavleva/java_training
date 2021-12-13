package ru.stqa.trening.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.UserData;

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

      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    }else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initUserCreation() {
    click(By.linkText("add new"));
  }

  public void initUserModification() {
      click(By.xpath("//img[@alt='Edit']"));
  }

  public void deleteModificationUser() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void submitUserModificatoin() {
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
}

