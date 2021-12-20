package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.user().all().size() == 0){
      UserData user = new UserData().withUser_firstname("test_user_firstname").withUser_lastname("test_user_lastname")
              .withAddress("test_address").withPhone("test_phone").withEmail("test_email");
      app.user().create(user);
    }
  }

  @Test
  public void testUserModification(){
    Users before = app.user().all();
    UserData modifiedUser = before.iterator().next();
   UserData user = new UserData().withId(modifiedUser.getId()).withUser_firstname("3test_user_firstname")
            .withUser_lastname("3test_user_lastname").withAddress("3test_address").withPhone("3test_phone").withEmail("3test_email");
    app.user().modify(user);
    Users after = app.user().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));

  }

}
