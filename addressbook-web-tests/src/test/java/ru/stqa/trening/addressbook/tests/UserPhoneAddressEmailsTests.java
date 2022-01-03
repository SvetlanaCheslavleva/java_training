package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPhoneAddressEmailsTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.user().all().size() == 0){
    UserData user = new UserData().withUserFirstname("userFirstname").withUserLastname("userLastname")
            .withAddress("address").withHomePhone("homePhone").withMobilePhone("mobile").withWorkPhone("workPhone").withHomePhone2("homePhone2")
            .withEmail("email").withEmail2("email2").withEmail3("email3");
    app.user().create(user);
    }
  }


  @Test
  public void testUserPhonesAddressEmails() {
    app.goTo().homePage();
    UserData user = app.user().all().iterator().next();
    UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

    assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
    assertThat(user.getAddress(), equalTo(mergeAddress(userInfoFromEditForm)));
    assertThat(user.getAllEmails(), equalTo(mergeEmails(userInfoFromEditForm)));
    }


  private String mergePhones(UserData user) {
    return Arrays.asList(user.getHomePhone(), user.getMobilePhone(), user.getWorkPhone(), user.getHomePhone2())
            .stream().filter((s) -> ! s.equals(""))
            .map(UserPhoneAddressEmailsTests::cleanedPhone)
            .collect(Collectors.joining("\n"));
  }

  private String mergeAddress(UserData user) {
    return Arrays.asList(user.getAddress()).stream().filter((s) -> !s.equals(""))
            .map(UserPhoneAddressEmailsTests::cleanedAddress)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(UserData user) {
    return Arrays.asList(user.getEmail(), user.getEmail2(), user.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(UserPhoneAddressEmailsTests::cleanedEmail)
            .collect(Collectors.joining("\n"));
  }



  public static String cleanedPhone(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleanedAddress(String address){
    return address.replaceAll("\\s", "");
  }

  public static String cleanedEmail(String email){
    return email.replaceAll("\\s", "").replaceAll("", "");
  }

}
