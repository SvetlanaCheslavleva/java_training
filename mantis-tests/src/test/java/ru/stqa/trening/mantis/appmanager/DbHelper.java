package ru.stqa.trening.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.trening.mantis.model.UserData;
import ru.stqa.trening.mantis.model.Users;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;
  private ApplicationManager app;

  public DbHelper(ApplicationManager app) {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData where username !='administrator'" ).list();
    for (UserData user : result) {
      System.out.println(user);
    }
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public UserData getUserFromDb(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    UserData single = (UserData) session.createQuery(String.format("from UserData where id = %s", id)).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return single;
  }

}
