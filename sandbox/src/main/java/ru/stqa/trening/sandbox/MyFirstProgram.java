package ru.stqa.trening.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Sveta");

        Square s = new Square(5);
        System.out.println("Area of a square with a side " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Area of a rectangle with sides " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(0, 0);
        Point p2 = new Point(-1, -2);
        System.out.println("Distance between two points = "+ p1.distance(p2));
    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");

    }

   /* public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }*/
}