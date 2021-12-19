package ru.stqa.trening.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase {

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
  public void testUserDeletion() {
    List<UserData> before = app.user().list();
    int index = before.size() - 1;
    app.user().delete(index);
    List<UserData> after = app.user().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
    }

}
