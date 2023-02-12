package ru.academits.shevkulenko.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double x2;
    private final double x3;
    private final double y1;
    private final double y2;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    private static double getCutLength(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private double getSide1() {
        return getCutLength(x1, x2, y1, y2);
    }

    private double getSide2() {
        return getCutLength(x2, x3, y2, y3);
    }

    private double getSide3() {
        return getCutLength(x3, x1, y3, y1);
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
        double semiPerimeter = getPerimeter() * 0.5;
        return Math.sqrt(semiPerimeter * (semiPerimeter - getSide1()) *
                (semiPerimeter - getSide2()) * (semiPerimeter - getSide3()));
    }

    @Override
    public double getPerimeter() {
        return getSide1() + getSide2() + getSide3();
    }

    @Override
    public String toString() {
        return String.format("Треугольник со сторонами %.2f, %.2f и %.2f", getSide1(), getSide2(), getSide3());
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