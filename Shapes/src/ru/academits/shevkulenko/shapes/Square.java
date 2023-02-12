package ru.academits.shevkulenko.shapes;

public class Square implements Shape {
    private double length;

    public Square(double length) {
        this.length = length;
    }

    @Override
    public double getWidth() {
        return length;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getArea() {
        return Math.pow(length, 2);
    }

    @Override
    public double getPerimeter() {
        return length * 4;
    }

    @Override
    public String toString() {
        return "Квадрат со стороной длиной " + length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;
        return length == square.length;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Double.hashCode(length);
    }
}