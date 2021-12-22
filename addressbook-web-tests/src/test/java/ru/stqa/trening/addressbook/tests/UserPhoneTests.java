package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPhoneTests  extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.user().all().size() == 0){
      UserData user = new UserData().withUserFirstname("test_user_firstname").withUserLastname("test_user_lastname")
              .withAddress("test_address").withHomePhone("test_phone").withMobilePhone("mobile").withWorkPhone("workPhone").withEmail("test_email");
      app.user().create(user);
    }
  }

  @Test
  public void testUserPhones() {
    app.goTo().homePage();
    UserData user = app.user().all().iterator().next();
    UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

    assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
    }

  private String mergePhones(UserData user) {
    return Arrays.asList(user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(UserPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
