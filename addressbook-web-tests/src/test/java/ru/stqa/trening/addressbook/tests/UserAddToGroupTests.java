package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.Groups;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.core.IsEqual.equalTo;

public class UserAddToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.goTo().homePage();
      UserData user = new UserData().withUserFirstname("test_user_firstname").withUserLastname("test_user_lastname");
      app.user().create(user);
    } else  if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test_1"));
    }

 }

  @Test
  public void testUserAddToGroup () {
    app.goTo().homePage();
    UserData user = selectedUser();
    Groups before = user.getGroups();
    GroupData groupForAdd = selectedGroup(user);
    app.user().addUserInGroup(user, groupForAdd);
    Groups after = app.db().getUserFromDb(user.getId()).getGroups();
    equalTo(before.withAdded(groupForAdd));
  }

  public UserData selectedUser() {
    Users users = app.db().users();
    Groups groups = app.db().groups();
    for (UserData user : users) {
      if (user.getGroups().size() < groups.size()) {
        return user;
      }
    }
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test1"));
    app.goTo().homePage();
    return users.iterator().next();
  }

  public GroupData selectedGroup(UserData user) {
    Groups all = app.db().groups();
    Collection<GroupData> freeGroups = new HashSet<GroupData>(all);
    freeGroups.removeAll(user.getGroups());
    return freeGroups.iterator().next();
  }

}
