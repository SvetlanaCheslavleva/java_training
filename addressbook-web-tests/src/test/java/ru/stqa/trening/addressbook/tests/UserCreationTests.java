package ru.stqa.trening.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    UserData user = new UserData("test_user_firstname", "test_user_lastname", "test_address", "test_phone", "test_email", "Test_1");
    String userGroupName = app.getUserHelper().findGroupNameInList(user.getGroup());
    if (userGroupName == null){
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData(user.getGroup(), "Test_2", "Test_3"));
    }
    app.getUserHelper().createUser(user);
  }

}
