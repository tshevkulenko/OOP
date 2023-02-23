package ru.academits.shevkulenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0");
        }

        components = new double[n];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0");
        }

        components = Arrays.copyOf(array, n);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < getSize() - 1; i++) {
            stringBuilder.append(components[i]).append(", ");
        }

        stringBuilder.append(components[getSize() - 1]).append("}");
        return stringBuilder.toString();
    }

    public void addVector(Vector vector) {
        int vectorSize = vector.getSize();

        if (getSize() < vectorSize) {
            components = Arrays.copyOf(components, vectorSize);
        }

        for (int i = 0; i < vectorSize; ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtractVector(Vector vector) {
        int vectorSize = vector.getSize();

        if (getSize() < vectorSize) {
            components = Arrays.copyOf(components, vectorSize);
        }

        for (int i = 0; i < vectorSize; ++i) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyVectorByScalar(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            components[i] *= scalar;
        }
    }

    public void reverseVector() {
        multiplyVectorByScalar(-1);
    }

    public double getVectorLength() {
        double componentSquaresSum = 0;

        for (double component : components) {
            componentSquaresSum += component * component;
        }

        return Math.sqrt(componentSquaresSum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Заданный индекс не существует");
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new IllegalArgumentException("Заданный индекс не существует");
        }

        components[index] = component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (getSize() != vector.getSize()) {
            return false;
        }

        for (int i = 0; i < getSize(); i++) {
            if (getComponent(i) != vector.getComponent(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Arrays.hashCode(components);
    }

    public static Vector sumVectors(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.addVector(vector2);
        return vector;
    }

    public static Vector subtractVectors(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtractVector(vector2);
        return vector;
    }

    public static double multiplyVectors(Vector vector1, Vector vector2) {
        double product = 0;
        int minVectorSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minVectorSize; ++i) {
            product += vector1.components[i] * vector2.components[i];
        }

        return product;
    }
}