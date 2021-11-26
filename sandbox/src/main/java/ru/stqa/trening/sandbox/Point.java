package ru.stqa.trening.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point secondPoint) {
    return Math.sqrt(Math.pow((this.x - secondPoint.x), 2) + Math.pow((this.y - secondPoint.y), 2));
  }

}
