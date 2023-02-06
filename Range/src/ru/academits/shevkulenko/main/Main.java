package ru.academits.shevkulenko.main;
import ru.academits.shevkulenko.range.Range;

import java.util.Scanner;

public class Main {
    private static void print(Range range, double number) {
        System.out.printf("%s. Длина диапазона равна %f. " +
                        "Результат проверки на принадлежность диапазону введенного вещественного числа: %b%n",
                range.toString(), range.getLength(), range.isInside(number));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите любое вещественное число:");
        double number = scanner.nextDouble();

        Range range1 = new Range(-1.2, -0.5);
        Range range2 = new Range(-5.1, 0.7);

        range2.setFrom(-10.3);
        range2.setTo(-6.2);

        System.out.print("Первый диапазон: ");
        print(range1, number);

        System.out.print("Второй диапазон: ");
        print(range2, number);

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Пересечения нет");
        } else {
            System.out.println("Диапазон пересечения: " + intersection.toString());
        }

        Range[] union = range1.getUnion(range2);

        for (Range range : union) {
            System.out.println("Диапазон объединения: " + range.toString());
        }

        Range[] difference = range1.getDifference(range2);

        if (difference.length == 0) {
            System.out.println("Диапазона разности нет");
        } else {
            for (Range range : difference) {
                System.out.println("Диапазон разности: " + range.toString());
            }
        }
    }
}