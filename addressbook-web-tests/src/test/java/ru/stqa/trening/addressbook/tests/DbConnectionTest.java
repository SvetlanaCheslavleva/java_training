package ru.stqa.trening.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.trening.addressbook.model.GroupData;
import ru.stqa.trening.addressbook.model.Groups;
import ru.stqa.trening.addressbook.model.UserData;
import ru.stqa.trening.addressbook.model.Users;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnectionGroup() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(groups);

    } catch (SQLException ex) {
      System.out.println("SQLExeption: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError " + ex.getErrorCode());
    }
  }

  @Test
  public void testDbConnectionUser() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id, firstname, lastname, address from addressbook");
      Users users = new Users();
      while (rs.next()) {
        users.add(new UserData().withId(rs.getInt("id")).withUserFirstname(rs.getString("firstname")).
                withUserLastname(rs.getString("lastname")).withAddress(rs.getString("address")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(users);

    } catch (SQLException ex) {
      System.out.println("SQLExeption: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError " + ex.getErrorCode());
    }
  }
}
