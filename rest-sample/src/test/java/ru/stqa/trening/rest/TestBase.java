package ru.stqa.trening.rest;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.trening.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {

  protected static ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    //app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    //app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }

  public boolean isIssueOpen(int issueId) throws MalformedURLException, RemoteException {
    if(app.soap().getIssueStatus(issueId).equals("Rresolved") || app.soap().getIssueStatus(issueId).equals("Closed")) {
      return false;
    } else {
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) throws MalformedURLException, RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
