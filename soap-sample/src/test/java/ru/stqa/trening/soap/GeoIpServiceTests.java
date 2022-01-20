package ru.stqa.trening.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("95.24.226.101");
    assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }

  @Test(enabled = true)
  public void testInvalidIp() {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("95.24.226.xxx");
    assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>48</State></GeoIP>");
  }
}
