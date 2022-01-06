package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void  ensurePreconditions(){
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test_1").withHeader("Test_2").withFooter("Test_3"));
    }
 }

  @Test
  public void testGroupModification(){
    Groups before = app.db().groups();
    GroupData modifiedGroup= before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("Test_1").withHeader("Test_2").withFooter("Test_3");

    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().Count(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

  }

}
