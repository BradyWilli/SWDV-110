
import java.util.ArrayList;
import java.util.List;

public class ShapesPractice {

    abstract static class Shape {
        abstract double area();
        abstract String getShape();
    }

    static class Circle extends Shape {
        @Override
        double area() {
            return 5.5;
        }

        @Override
        String getShape() {
            return "Circle, " + area();
        }
    }

    static class Rectangle extends Shape {
        @Override
        double area() {
            return 8.3;
        }

        @Override
        String getShape() {
            return "Rectangle, " + area();
        }
    }

    static class Square extends Rectangle {
        @Override
        double area() {
            return 19.7;
        }

        @Override
        String getShape() {
            return "Square, " + area();
        }
    }

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle());
        shapes.add(new Rectangle());
        shapes.add(new Square());

        for (Shape shape : shapes) {
            System.out.println(shape.getShape());
        }
    }
}
