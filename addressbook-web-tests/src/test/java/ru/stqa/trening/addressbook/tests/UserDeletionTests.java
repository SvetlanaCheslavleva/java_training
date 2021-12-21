package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserDeletionTests extends TestBase {

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
  public void testUserDeletion() {
    Users before = app.user().all();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    assertThat(app.user().count(), equalTo(before.size() - 1));
    Users after = app.user().all();
    assertThat(after, equalTo(before.without(deletedUser)));


    }

}
