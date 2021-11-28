package ru.stqa.trening.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests2 {
  @Test
  public void testDistance(){
    Point firstPoint = new Point(2,8);
    Point secondPoint = new Point(1, 5);
    Assert.assertEquals(secondPoint.distance(firstPoint), 3.1622776601683795);
  }
}
