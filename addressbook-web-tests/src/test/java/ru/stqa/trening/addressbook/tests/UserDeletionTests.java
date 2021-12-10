package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion(){
    app.getNavigationHelper().gotoHomePage();
    app.getUserHelper().initUserModification();
    app.getUserHelper().deleteModificationUser();
  }

}
