package ru.stqa.trening.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test_1", "Test_2", "Test_3"));
    }
    app.getGroupHelper().selectGroup(before - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }

}
