package ru.stqa.trening.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
    public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("Test_1", "Test_2", "Test_3"));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1);
  }
}
