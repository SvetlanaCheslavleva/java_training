package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsers() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/stru.png");
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/user.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new UserData().withUserFirstname(split[0]).withUserLastname(split[1])
              .withAddress(split[2]).withHomePhone(split[3]).withEmail(split[4]).withPhoto(photo)});
      line = reader.readLine();
    }
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
