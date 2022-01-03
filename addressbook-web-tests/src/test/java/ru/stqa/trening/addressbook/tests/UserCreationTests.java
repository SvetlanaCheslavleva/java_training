package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation()  {
    app.goTo().homePage();
    Users before = app.user().all();
    File photo = new File("src/test/resources/stru.png");
    UserData user = new UserData().withUserFirstname("test_user_firstname").withUserLastname("test_user_lastname")
            .withAddress("test_address").withHomePhone("test_phone").withMobilePhone("mobile").withWorkPhone("workPhone")
            .withEmail("test_email").withPhoto(photo);
    app.user().create(user);
    assertThat(app.user().count(), equalTo(before.size() + 1));
    Users after = app.user().all();
    assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
  }



  @Test(enabled = false)
  public void testBadUserCreation()  {
    app.goTo().homePage();
    Users before = app.user().all();
    UserData user = new UserData().withUserFirstname("test_user_firstname'").withUserLastname("test_user_lastname")
            .withAddress("test_address").withHomePhone("test_phone").withMobilePhone("mobile").withWorkPhone("workPhone").withEmail("test_email");
    app.user().create(user);
    assertThat(app.user().count(), equalTo(before.size()));
    Users after = app.user().all();
    assertThat(after, equalTo(before));
  }

}
