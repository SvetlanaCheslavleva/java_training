package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.UserData;

public class UserAddressTests extends TestBase{

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
  public void testAddressPhones() {
    
  }
}
