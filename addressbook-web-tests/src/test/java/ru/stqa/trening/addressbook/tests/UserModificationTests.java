package ru.stqa.trening.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.*;

public class UserModificationTests extends TestBase {

  @Test
  public void testUserModification(){
    app.getNavigationHelper().gotoHomePage();
    if (!app.getUserHelper().isThereAUser()){
      UserData user = new UserData("test_user_firstname", "test_user_lastname",
              "test_address", "test_phone", "test_email", null);
      app.getUserHelper().createUser(user);
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initUserModification(before.size() - 1);
    UserData user = new UserData(before.get(before.size() - 1).getId(), "3test_user_firstname", "3test_user_lastname",
            "3test_address", "3test_phone", "3test_email", null);
    app.getUserHelper().fillUserForm(user, false);
    app.getUserHelper().submitUserModification();
    app.getUserHelper().returnToHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
