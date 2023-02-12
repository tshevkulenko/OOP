package ru.academits.shevkulenko.main;

import ru.academits.shevkulenko.shapes.*;

import java.util.Arrays;

public class Main {
    private static Shape getShapeWithMaxArea(Shape... shapes) {
        Arrays.sort(shapes, new AreaComparator());
        return shapes[shapes.length - 1];
    }

    private static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        Arrays.sort(shapes, new PerimeterComparator());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape s1 = new Square(4.5);
        System.out.println(s1.toString());
        System.out.println("Ширина равна " + s1.getWidth());
        System.out.println("Высота равна " + s1.getHeight());
        System.out.println("Площадь равна " + s1.getArea());
        System.out.println("Периметр равен " + s1.getPerimeter());
        System.out.println("Хэш " + s1.hashCode());
        System.out.println();

        Shape s2 = new Rectangle(5.3, 7.2);
        System.out.println(s2.toString());
        System.out.println("Ширина равна " + s2.getWidth());
        System.out.println("Высота равна " + s2.getHeight());
        System.out.println("Площадь равна " + s2.getArea());
        System.out.println("Периметр равен " + s2.getPerimeter());
        System.out.println("Хэш " + s2.hashCode());
        System.out.println("Равенство фигуре 1: " + s2.equals(s1));
        System.out.println();

        Shape s3 = new Triangle(1, 1, 2, 1, 1, 4);
        System.out.println(s3.toString());
        System.out.println("Ширина равна " + s3.getWidth());
        System.out.println("Высота равна " + s3.getHeight());
        System.out.println("Площадь равна " + s3.getArea());
        System.out.println("Периметр равен " + s3.getPerimeter());
        System.out.println("Хэш " + s3.hashCode());
        System.out.println("Равенство фигуре 2: " + s3.equals(s2));
        System.out.println();

        Shape s4 = new Circle(1.5);
        System.out.println(s4.toString());
        System.out.println("Ширина равна " + s4.getWidth());
        System.out.println("Высота равна " + s4.getHeight());
        System.out.println("Площадь равна " + s4.getArea());
        System.out.println("Периметр равен " + s4.getPerimeter());
        System.out.println("Хэш " + s4.hashCode());
        System.out.println("Равенство фигуре 3: " + s4.equals(s3));
        System.out.println();

        Shape[] shapes = {s1, s2, s3, s4, new Circle(4.3), new Triangle(1, 1, 3, 1, 1, 5),
                new Rectangle(7, 8), new Square(10)};

        Shape maxAreaShape = getShapeWithMaxArea(shapes);
        System.out.println("Первая по площади фигура: " + maxAreaShape.toString());
        System.out.println();

        Shape secondPerimeterShape = getShapeWithSecondPerimeter(shapes);
        System.out.println("Вторая по периметру фигура: " + secondPerimeterShape.toString());
    }
}