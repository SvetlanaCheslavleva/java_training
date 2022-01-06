package ru.stqa.trening.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.UserData;

import java.util.List;

public class HbConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }


    @Test(enabled = false)
    public void testHbConnectionGroups() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<GroupData> result = session.createQuery( "from GroupData where deprecated = '0000-00-00'" ).list();
      for (GroupData group : result) {
        System.out.println(group);
      }
      session.getTransaction().commit();
      session.close();
    }

    @Test
    public void testHbConnectionUsers() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<UserData> result = session.createQuery( "from UserData where deprecated = '0000-00-00'" ).list();
      for (UserData user : result) {
        System.out.println(user);
      }
      session.getTransaction().commit();
      session.close();
    }

}
