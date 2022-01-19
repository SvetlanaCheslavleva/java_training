package ru.stqa.trening.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.trening.mantis.model.MailMessage;
import ru.stqa.trening.mantis.model.UserData;
import ru.stqa.trening.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ResetPasswordTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws MessagingException, IOException {
    long now = System.currentTimeMillis();
    Users users = app.db().users();
    UserData selectedUser = users.iterator().next();
    String username = selectedUser.getUsername();
    String email = selectedUser.getEmail();
    String newPassword = String.format("newPassword");
    app.registration().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.registration().resetUserPassword(username);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(username, newPassword));
  }
  public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
