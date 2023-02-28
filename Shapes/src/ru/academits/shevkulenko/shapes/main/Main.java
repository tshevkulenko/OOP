package ru.academits.shevkulenko.shapes.main;

import ru.academits.shevkulenko.shapes.*;
import ru.academits.shevkulenko.shapes.comparators.ShapeAreaComparator;
import ru.academits.shevkulenko.shapes.comparators.ShapePerimeterComparator;

import java.util.Arrays;

public class Main {
    private static Shape getShapeWithMaxArea(Shape... shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeAreaComparator());
        return shapes[shapes.length - 1];
    }

    private static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        if (shapes.length < 2) {
            return null;
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());
        return shapes[shapes.length - 2];
    }

    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(4.3),
                new Triangle(1, 1, 3, 1, 1, 5),
                new Rectangle(7, 8),
                new Square(10)
        };

        for (int i = 0; i < shapes.length; i++) {
            System.out.println(shapes[i]);
            System.out.println("Ширина равна " + shapes[i].getWidth());
            System.out.println("Высота равна " + shapes[i].getHeight());
            System.out.println("Площадь равна " + shapes[i].getArea());
            System.out.println("Периметр равен " + shapes[i].getPerimeter());
            System.out.println("Хэш " + shapes[i].hashCode());

            if (i != 0) {
                System.out.println("Равенство предыдущей фигуре: " + shapes[i].equals(shapes[i - 1]));
            }

            System.out.println();
        }

        Shape maxAreaShape = getShapeWithMaxArea(shapes);
        System.out.println("Первая по площади фигура: " + maxAreaShape);
        System.out.println();

        Shape secondPerimeterShape = getShapeWithSecondPerimeter(shapes);
        System.out.println("Вторая по периметру фигура: " + secondPerimeterShape);
    }
}