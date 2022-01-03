package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/stru.png");
    list.add(new Object[] {new UserData().withUserFirstname("firstName1").withUserLastname("lastName1")
            .withAddress("address1").withHomePhone("phone1").withEmail("email1").withPhoto(photo)});
    list.add(new Object[] {new UserData().withUserFirstname("firstName2").withUserLastname("lastName2")
            .withAddress("address2").withHomePhone("phone2").withEmail("email2").withPhoto(photo)});
    list.add(new Object[] {new UserData().withUserFirstname("firstName3").withUserLastname("lastName3")
            .withAddress("address3").withHomePhone("phone3").withEmail("email3").withPhoto(photo)});
    return list.listIterator();
  }

  @Test(dataProvider = "validUsers")
  public void testUserCreation(UserData user)  {
    app.goTo().homePage();
    Users before = app.user().all();
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
