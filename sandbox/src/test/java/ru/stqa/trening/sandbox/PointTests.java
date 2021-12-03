package ru.stqa.trening.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistance() {
    Point firstPoint = new Point(2, 8);
    Point secondPoint = new Point(1, 5);
    Assert.assertEquals(firstPoint.distance(secondPoint), 3.1622776601683795);
  }

  @Test
  public void testDistance2() {
    Point firstPoint = new Point(0, 0);
    Point secondPoint = new Point(-1, -2);
    Assert.assertEquals(secondPoint.distance(firstPoint), 2.23606797749979);
  }
}
