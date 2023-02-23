package ru.academits.shevkulenko.main;

import ru.academits.shevkulenko.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(3);
        System.out.println("Вектор 1: " + vector1 + " размерностью " + vector1.getSize());

        Vector vector2 = new Vector(vector1);
        System.out.println("Вектор 2: " + vector2 + " размерностью " + vector2.getSize());

        double[] array = {1.1, 2.2, 3.3, 4.4};
        Vector vector3 = new Vector(array);
        System.out.println("Вектор 3: " + vector3 + " размерностью " + vector3.getSize());

        Vector vector4 = new Vector(5, array);
        System.out.println("Вектор 4: " + vector4 + " размерностью " + vector4.getSize());

        vector3.addVector(vector4);
        System.out.println("Обновленный вектор 3 после прибавления вектора 4: " + vector3);

        vector3.subtractVector(vector4);
        System.out.println("Обновленный вектор 3 после вычитания вектора 4: " + vector3);

        vector3.multiplyVectorByScalar(2);
        System.out.println("Обновленный вектор 3 после умножения на скаляр: " + vector3);

        vector3.reverseVector();
        System.out.println("Обновленный вектор 3 после разворота: " + vector3);

        System.out.println("Длина обновленного вектора 3: " + vector3.getVectorLength());

        System.out.println("Компонента вектора 3 с заданным индексом = " + vector3.getComponent(1));

        vector3.setComponent(1, 5.5);
        System.out.println("Обновленный вектор 3 после установки новой компоненты: " + vector3);

        System.out.println("Равенство обновленного вектора 3 вектору 4: " + vector3.equals(vector4));

        double[] newArray = {0, -1, 2, 3, -4};
        Vector vector5 = new Vector(newArray);
        System.out.println("Вектор 5: " + vector5 + " размерностью " + vector5.getSize());

        Vector vector6 = Vector.sumVectors(vector3, vector5);
        System.out.println("Вектор 6 - результат сложения векторов 3 и 5: " + vector6);

        Vector vector7 = Vector.subtractVectors(vector3, vector5);
        System.out.println("Вектор 7 - результат вычитания векторов 3 и 5: " + vector7);

        System.out.println("Скалярное произведение векторов 3 и 5: " + Vector.multiplyVectors(vector3, vector5));
    }
}