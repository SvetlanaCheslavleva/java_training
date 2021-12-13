package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification(){
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereAUser()){
      UserData user = new UserData("test_user_firstname", "test_user_lastname", "test_address", "test_phone", "test_email", "Test_1");
      String userGroupName = app.getUserHelper().findGroupNameInList(user.getGroup());
      if (userGroupName == null){
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData(user.getGroup(), "Test_2", "Test_3"));
      }
      app.getUserHelper().createUser(user);
    }
    app.getUserHelper().initUserModification();
    app.getUserHelper().fillUserForm(new UserData("3test_user_firstname", "3test_user_lastname", "3test_address", "3test_phone", "3test_email", null), false);
    app.getUserHelper().submitUserModificatoin();

  }
}
