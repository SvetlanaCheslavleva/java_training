package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion(){
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereAUser()){
      UserData user = new UserData("test_user_firstname", "test_user_lastname", "test_address", "test_phone", "test_email", null);
      app.getUserHelper().createUser(user);
    }
    app.getUserHelper().initUserModification();
    app.getUserHelper().deleteModificationUser();
  }

}
