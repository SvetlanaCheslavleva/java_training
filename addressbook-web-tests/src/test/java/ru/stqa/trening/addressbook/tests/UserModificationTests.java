package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().users().size() == 0) {
      app.goTo().homePage();
      UserData user = new UserData().withUserFirstname("test_user_firstname").withUserLastname("test_user_lastname")
              .withAddress("test_address").withHomePhone("test_phone").withMobilePhone("mobile").withWorkPhone("workPhone").withEmail("test_email");
      app.user().create(user);
    }
  }

  @Test
  public void testUserModification(){
    Users before = app.db().users();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData().withId(modifiedUser.getId()).withUserFirstname("3test_user_firstname")
            .withUserLastname("3test_user_lastname").withAddress("3test_address").withHomePhone("3test_phone").withMobilePhone("mobile").withWorkPhone("workPhone").withEmail("3test_email");
    app.goTo().homePage();
    app.user().modify(user);
    assertThat(app.user().count(), equalTo(before.size()));
    Users after = app.db().users();
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
    verifyUserListInUI();
  }
}
