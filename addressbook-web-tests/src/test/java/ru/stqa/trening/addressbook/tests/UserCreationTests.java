package ru.stqa.trening.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation()  {
    app.getNavigationHelper().gotoHomePage();
    List<UserData> before = app.getUserHelper().getUserList();
    UserData user = new UserData("test_user_firstname", "test_user_lastname", "test_address", "test_phone", "test_email", null);
    app.getUserHelper().createUser(user);
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
