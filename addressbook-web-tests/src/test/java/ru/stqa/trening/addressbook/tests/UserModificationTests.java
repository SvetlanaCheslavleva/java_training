package ru.stqa.trening.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.*;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.user().list().size() == 0){
      UserData user = new UserData().withUser_firstname("test_user_firstname").withUser_lastname("test_user_lastname")
              .withAddress("test_address").withPhone("test_phone").withEmail("test_email");
      app.user().create(user);
    }
  }

  @Test
  public void testUserModification(){
    List<UserData> before = app.user().list();
    int index = before.size() - 1;
    UserData user = new UserData().withId(before.get(index).getId()).withUser_firstname("3test_user_firstname")
            .withUser_lastname("3test_user_lastname").withAddress("3test_address").withPhone("3test_phone").withEmail("3test_email");
    app.user().modify(index, user);
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
