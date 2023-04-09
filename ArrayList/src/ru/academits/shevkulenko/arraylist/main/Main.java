package ru.academits.shevkulenko.arraylist.main;

import ru.academits.shevkulenko.arraylist.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Проверка на пустоту: " + list.isEmpty());
        list.add(8);
        list.add(9);
        System.out.println("Коллекция: " + list);
        System.out.println("Проверка на пустоту: " + list.isEmpty());
        System.out.println("Размер коллекции: " + list.size());
        System.out.println("Вместимость коллекции: " + list.getCapacity());
        list.trimToSize();
        System.out.println("Вместимость после изменения: " + list.getCapacity());

        System.out.print("Метод toArray: ");
        System.out.println(Arrays.toString(list.toArray()));

        System.out.print("Метод toArray(S[] a): ");
        Integer[] array = new Integer[2];
        array = list.toArray(array);
        System.out.println(Arrays.toString(array));

        System.out.print("Работа итератора: ");
        for (Integer number : list) {
            System.out.print(number);
            System.out.print(" ");
        }

        System.out.println();

        ArrayList<Integer> list1 = new ArrayList<>(3);
        System.out.println("Размер коллекции: " + list1.size());
        System.out.println("Вместимость коллекции: " + list1.getCapacity());

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(3);
        System.out.println("Коллекция: " + list1);
        System.out.println("Размер коллекции: " + list1.size());

        int item = 3;
        System.out.println("Индекс элемента " + item + " с начала коллекции: " + list1.indexOf(item));
        System.out.println("Индекс элемента " + item + " с конца коллекции: " + list1.lastIndexOf(item));
        System.out.println("Наличие элемента " + item + " в коллекции: " + list1.contains(item));

        list1.set(0, 0);
        System.out.println("Коллекция после изменения: " + list1);

        list1.increaseCapacity();
        System.out.println("Вместимость коллекции: " + list1.getCapacity());

        list1.ensureCapacity(10);
        System.out.println("Вместимость коллекции: " + list1.getCapacity());

        list1.add(1, 1);
        System.out.println("Коллекция после изменения: " + list1);

        System.out.println("Удаленный элемент: " + list1.remove(0));
        System.out.println("Коллекция после изменения: " + list1);

        System.out.println("Подтверждение удаления элемента: " + list1.remove((Integer)3));
        System.out.println("Коллекция после изменения: " + list1);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.addAll(list);
        System.out.println("Коллекция: " + list2);
        System.out.println("Проверка на наличие первой коллекции: " + list2.containsAll(list));
        System.out.println("Проверка на наличие второй коллекции: " + list2.containsAll(list1));

        list2.addAll(1, list1);
        System.out.println("Коллекция: " + list2);
        System.out.println("Проверка на наличие второй коллекции: " + list2.containsAll(list1));

        list2.removeAll(list);
        System.out.println("Коллекция: " + list2);

        list2.add(4);
        list2.add(5);

        list2.retainAll(list1);
        System.out.println("Коллекция: " + list2);

        list2.clear();
        System.out.println("Коллекция: " + list2);
    }
}
