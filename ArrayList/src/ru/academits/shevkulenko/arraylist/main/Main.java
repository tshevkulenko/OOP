package ru.academits.shevkulenko.arraylist.main;

import ru.academits.shevkulenko.arraylist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        System.out.println("Проверка на пустоту: " + list1.isEmpty());

        list1.add(8);
        list1.add(9);

        System.out.println("Коллекция: " + list1);
        System.out.println("Проверка на пустоту: " + list1.isEmpty());
        System.out.println("Размер коллекции: " + list1.size());
        System.out.println("Вместимость коллекции: " + list1.getCapacity());

        list1.trimToSize();
        System.out.println("Вместимость после изменения: " + list1.getCapacity());

        System.out.print("Метод toArray: ");
        System.out.println(Arrays.toString(list1.toArray()));

        System.out.print("Метод toArray(T[] a): ");
        Integer[] array = new Integer[2];
        array = list1.toArray(array);
        System.out.println(Arrays.toString(array));

        System.out.print("Работа итератора: ");

        for (Integer number : list1) {
            System.out.print(number);
            System.out.print(" ");
        }

        System.out.println();

        ArrayList<Integer> list2 = new ArrayList<>(3);

        System.out.println("Размер коллекции: " + list2.size());
        System.out.println("Вместимость коллекции: " + list2.getCapacity());

        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(3);

        System.out.println("Коллекция: " + list2);
        System.out.println("Размер коллекции: " + list2.size());

        int item = 3;

        System.out.println("Индекс элемента " + item + " с начала коллекции: " + list2.indexOf(item));
        System.out.println("Индекс элемента " + item + " с конца коллекции: " + list2.lastIndexOf(item));
        System.out.println("Наличие элемента " + item + " в коллекции: " + list2.contains(item));

        list2.set(0, 0);
        System.out.println("Коллекция после изменения: " + list2);

        list2.increaseCapacity();
        System.out.println("Вместимость коллекции: " + list2.getCapacity());

        list2.ensureCapacity(10);
        System.out.println("Вместимость коллекции: " + list2.getCapacity());

        list2.add(1, 1);
        System.out.println("Коллекция после изменения: " + list2);

        System.out.println("Удаленный элемент: " + list2.remove(0));
        System.out.println("Коллекция после изменения: " + list2);
        System.out.println("Подтверждение удаления элемента: " + list2.remove((Integer) 3));
        System.out.println("Коллекция после изменения: " + list2);

        ArrayList<Integer> list3 = new ArrayList<>();
        list3.addAll(list1);

        System.out.println("Коллекция: " + list3);
        System.out.println("Проверка на наличие первой коллекции: " + list3.containsAll(list1));
        System.out.println("Проверка на наличие второй коллекции: " + list3.containsAll(list2));

        list3.addAll(1, list2);

        System.out.println("Коллекция: " + list3);
        System.out.println("Проверка на наличие второй коллекции: " + list3.containsAll(list2));

        list3.removeAll(list1);
        System.out.println("Коллекция: " + list3);

        list3.add(4);
        list3.add(5);
        System.out.println("Коллекция: " + list3);

        list3.retainAll(list2);
        System.out.println("Коллекция: " + list3);

        list3.clear();
        System.out.println("Коллекция: " + list3);
    }
}