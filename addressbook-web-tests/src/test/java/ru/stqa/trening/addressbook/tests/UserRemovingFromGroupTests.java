package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.Groups;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserRemovingFromGroupTests  extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().users().size() == 0) {
      app.goTo().homePage();
      UserData user = new UserData().withUserFirstname("test_user_firstname").withUserLastname("test_user_lastname");
      app.user().create(user);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test_1"));
    }

  }

  @Test
  public void testUserRemovingFromGroup () {
    app.goTo().homePage();
    UserData user = selectUser();
    GroupData groupForDel = selectedGroup(user);
    Groups before = user.getGroups();
    app.goTo().homePage();
    app.user().selGroupForDel(groupForDel.getId());
    app.user().removeContactFromGroup(user, groupForDel.getId());
    UserData contactsAfter = selectUserById(user);
    Groups after = contactsAfter.getGroups();
    assertThat(after, equalTo(before.without(groupForDel)));

  }

  private UserData selectUserById(UserData user) {
    Users userById = app.db().users();
    return userById.iterator().next().withId(user.getId());
  }

  private GroupData selectedGroup(UserData deleteUser) {
    UserData user = selectUserById(deleteUser);
    Groups deletedUser = user.getGroups();
    return deletedUser.iterator().next();
  }

  private UserData selectUser() {
    Users users = app.db().users();
    for (UserData user : users) {
      if (user.getGroups().size() > 0) {
        return user;
      }
    }
      UserData addUser = app.db().users().iterator().next();
      app.user().addUserInGroup(addUser, app.db().groups().iterator().next());
      return addUser;
    }

}
