package ru.academits.shevkulenko.range.main;

import ru.academits.shevkulenko.range.range.Range;

import java.util.Scanner;

public class Main {
    private static void print(double from, double to, double length, boolean isInside) {
        System.out.printf("Диапазон находится в пределах от %f до %f. Длина диапазона равна %f. " +
                        "Результат проверки на принадлежность диапазону введенного вещественного числа: %b%n",
                from, to, length, isInside);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите любое вещественное число:");
        double number = scanner.nextDouble();

        Range range1 = new Range(-1.2, -0.5);
        Range range2 = new Range(-5.1, 0.7);

        range2.setFrom(-10.3);
        range2.setTo(-6.2);

        System.out.println("Первый диапазон:");
        print(range1.getFrom(), range1.getTo(), range1.getLength(), range1.isInside(number));

        System.out.println("Второй диапазон:");
        print(range2.getFrom(), range2.getTo(), range2.getLength(), range2.isInside(number));

        System.out.println("Ищу пересечение...");
        Range range3 = range1.getCross(range2);
        if (range3 == null) {
            System.out.println("Пересечения нет");
        } else {
            print(range3.getFrom(), range3.getTo(), range3.getLength(), range3.isInside(number));
        }

        System.out.println("Ищу объединение...");
        Range[] range4 = range1.getUnion(range2);
        for (Range range : range4) {
            print(range.getFrom(), range.getTo(), range.getLength(), range.isInside(number));
        }

        System.out.println("Ищу разность...");
        Range[] range5 = range1.getDifference(range2);
        if (range5.length == 0) {
            System.out.println("Диапазона разности нет");
        } else {
            for (Range range : range5) {
                print(range.getFrom(), range.getTo(), range.getLength(), range.isInside(number));
            }
        }
    }
}