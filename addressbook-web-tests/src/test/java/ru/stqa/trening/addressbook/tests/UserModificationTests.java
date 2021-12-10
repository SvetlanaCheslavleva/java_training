package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getUserHelper().initUserModification();
    app.getUserHelper().fillUserForm(new UserData("3test_user_firstname", "3test_user_lastname", "3test_address", "3test_phone", "3test_email", null), false);
    app.getUserHelper().submitUserModificatoin();

  }
}
