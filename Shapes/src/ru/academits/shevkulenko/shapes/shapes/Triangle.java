package ru.academits.shevkulenko.shapes.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double x2;
    private final double x3;
    private final double y1;
    private final double y2;
    private final double y3;
    private final double side1Length;
    private final double side2Length;
    private final double side3Length;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        side1Length = getCutLength(x1, y1, x2, y2);
        side2Length = getCutLength(x2, y2, x3, y3);
        side3Length = getCutLength(x3, y3, x1, y1);
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public double getSide1Length() {
        return side1Length;
    }

    public double getSide2Length() {
        return side2Length;
    }

    public double getSide3Length() {
        return side3Length;
    }

    private static double getCutLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        double semiPerimeter = 0.5 * getPerimeter();
        return Math.sqrt(semiPerimeter * (semiPerimeter - side1Length) *
                (semiPerimeter - side2Length) * (semiPerimeter - side3Length));
    }

    @Override
    public double getPerimeter() {
        return side1Length + side2Length + side3Length;
    }

    @Override
    public String toString() {
        return String.format("Треугольник с координатами: (%.2f, %.2f), (%.2f, %.2f), (%.2f, %.2f)",
                x1, y1, x2, y2, x3, y3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 && x2 == triangle.x2 && x3 == triangle.x3
                && y1 == triangle.y1 && y2 == triangle.y2 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}