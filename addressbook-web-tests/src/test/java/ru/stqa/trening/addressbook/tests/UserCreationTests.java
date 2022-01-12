package ru.stqa.trening.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.Groups;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsersFromXml() throws IOException {
    File photo = new File("src/test/resources/stru.png");
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.allowTypes(new Class[]{ UserData.class });
      xstream.processAnnotations(UserData.class);
      List<UserData> users = (List<UserData>) xstream.fromXML(xml);
      return users.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validUsersFromJson() throws IOException {
    File photo = new File("src/test/resources/stru.png");
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/users.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson =new Gson();
      List<UserData> users = gson.fromJson(json, new TypeToken<List<UserData>>(){}.getType());
      return users.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validUsersFromJson")
  public void testUserCreation(UserData user)  {
/*    Groups groups = app.db().groups();
    UserData user = new UserData().withUserFirstname("test_user_firstname").withUserLastname("test_user_lastname")
            .withAddress("test_address").inGroup(groups.iterator().next());*/
    app.goTo().homePage();
    Users before = app.db().users();
    app.user().create(user);
    assertThat(app.user().count(), equalTo(before.size() + 1));
    Users after = app.db().users();
    assertThat(after, equalTo(before.withAdded(user.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
    verifyUserListInUI();
  }



  @Test(enabled = false)
  public void testBadUserCreation()  {
    app.goTo().homePage();
    Users before = app.db().users();
    UserData user = new UserData().withUserFirstname("test_user_firstname'").withUserLastname("test_user_lastname")
            .withAddress("test_address").withHomePhone("test_phone").withMobilePhone("mobile").withWorkPhone("workPhone").withEmail("test_email");
    app.user().create(user);
    assertThat(app.user().count(), equalTo(before.size()));
    Users after = app.db().users();
    assertThat(after, equalTo(before));
    verifyUserListInUI();
  }

 @Test(enabled = false)
  public void testUserInGroup()  {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/stru.png");
    UserData newUser = new UserData().withUserFirstname("test_user_firstname").withUserLastname("test_user_lastname")
            .withPhoto(photo).inGroup(groups.iterator().next());
   app.goTo().homePage();
   Users before = app.db().users();
   app.user().create(newUser);
   assertThat(app.user().count(), equalTo(before.size() + 1));
   Users after = app.db().users();
   assertThat(after, equalTo(before.withAdded(newUser.withId(after.stream().mapToInt((u) -> u.getId()).max().getAsInt()))));
   verifyUserListInUI();
  }

}
