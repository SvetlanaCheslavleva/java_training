package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion(){
    app.getUserHelper().gotoUserPage();
    app.getUserHelper().initUserModification();
    app.getUserHelper().deleteModificationUser();
  }

}
