package ru.stqa.trening.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.util.List;

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
    type(By.name("firstname"), userData.getUserFirstname());
    type(By.name("lastname"), userData.getUserLastname());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userData.getHomePhone());
    type(By.name("mobile"), userData.getMobilePhone());
    type(By.name("work"), userData.getWorkPhone());
    type(By.name("email"), userData.getEmail());
    attach(By.name("photo"), userData.getPhoto());


    if (creation){
      if (userData.getGroups().size() > 0){
        Assert.assertTrue(userData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroups().iterator().next().getName());
      }
    }else{
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initUserCreation() {
    click(By.linkText("add new"));
  }

  public void initUserModificationById(int id) {
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
    initUserModificationById(user.getId());
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
      List<WebElement> userParameters = element.findElements(By.xpath(".//td"));
      String firstName = userParameters.get(2).getText();
      String lastName = userParameters.get(1).getText();
      String allPhones = userParameters.get(5).getText();
      String address = userParameters.get(3).getText();
      String allEmails = userParameters.get(4).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      userCache.add(new UserData().withId(id).withUserFirstname(firstName).withUserLastname(lastName)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return new Users(userCache);
  }

  public UserData infoFromEditForm(UserData user) {
    initUserModificationById(user.getId());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String home2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new UserData().withId(user.getId()).withUserFirstname(firstName).withUserLastname(lastName)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withHomePhone2(home2).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

 /* private void initUserModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='$s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../../"));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

 //   wd.findElement(By.cssSelector(String.format("a[href='edit.php&id=%s']", id))).click();
  }*/
}


