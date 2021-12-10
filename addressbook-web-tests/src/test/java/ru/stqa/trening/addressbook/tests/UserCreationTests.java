package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getUserHelper().createUser(new UserData("test_user_firstname", "test_user_lastname", "test_address", "test_phone", "test_email", "Test_1"));
  }

}
