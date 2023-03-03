package ru.academits.shevkulenko.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, размерность равна " + dimension);
        }

        components = new double[dimension];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Размер массива должен быть больше 0, размер равен " + array.length);
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, размерность равна " + dimension);
        }

        components = Arrays.copyOf(array, dimension);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i < components.length - 1; i++) {
            stringBuilder.append(components[i]).append(", ");
        }

        stringBuilder.append(components[components.length - 1]).append("}");
        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double componentSquaresSum = 0;

        for (double component : components) {
            componentSquaresSum += component * component;
        }

        return Math.sqrt(componentSquaresSum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Заданный индекс должен быть в пределах от 0 до " + components.length +
                    "не включительно, индекс равен " + index);
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new ArrayIndexOutOfBoundsException("Заданный индекс должен быть в пределах от 0 до " + components.length +
                    "не включительно, индекс равен " + index);
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
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        return prime + Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.add(vector2);
        return vector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtract(vector2);
        return vector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double product = 0;
        int minVectorSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minVectorSize; ++i) {
            product += vector1.components[i] * vector2.components[i];
        }

        return product;
    }
}