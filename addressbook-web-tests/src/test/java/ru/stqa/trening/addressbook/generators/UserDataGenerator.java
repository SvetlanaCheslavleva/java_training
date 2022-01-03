package ru.stqa.trening.addressbook.generators;

import ru.stqa.trening.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<UserData> users = generateUsers(count);
    save(users, file);
  }

  private static void save(List<UserData> users, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (UserData user : users) {
      writer.write(String.format("%s;%s;%s;%s;%s\n", user.getUserFirstname(), user.getUserLastname(),
      user.getAddress(),user.getHomePhone(),user.getEmail()));
    }
    writer.close();
  }

  private static List<UserData> generateUsers(int count) {
    List<UserData> users = new ArrayList<UserData>();
    for (int i = 0; i<count; i++) {
      users.add(new UserData().withUserFirstname(String.format("firstName %s", i))
              .withUserLastname(String.format("lastName %s", i)).withAddress(String.format("address %s", i))
              .withHomePhone(String.format("homePhone %s", i)).withEmail(String.format("email %s", i)));
    }
    return users;
  }
}
