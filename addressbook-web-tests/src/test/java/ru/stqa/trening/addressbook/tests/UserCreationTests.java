package ru.stqa.trening.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation()  {
    app.goTo().homePage();
    List<UserData> before = app.user().list();
    UserData user = new UserData().withUser_firstname("test_user_firstname").withUser_lastname("test_user_lastname")
            .withAddress("test_address").withPhone("test_phone").withEmail("test_email");
    app.user().create(user);
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
