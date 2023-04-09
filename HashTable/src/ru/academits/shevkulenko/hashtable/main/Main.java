package ru.academits.shevkulenko.hashtable.main;

import ru.academits.shevkulenko.hashtable.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable1 = new HashTable<>();

        System.out.println("Проверка на пустоту: " + hashTable1.isEmpty());

        hashTable1.add(1);
        hashTable1.add(2);
        hashTable1.add(3);

        System.out.println("Проверка на пустоту: " + hashTable1.isEmpty());
        System.out.println(hashTable1);
        System.out.println("Количество элементов в таблице: " + hashTable1.size());

        int number = 2;
        System.out.println("Проверка наличия элемента " + number + " : " + hashTable1.contains(2));

        System.out.print("Работа итератора: ");

        for (Integer element : hashTable1) {
            System.out.print(element + " ");
        }

        System.out.println();

        System.out.println(Arrays.toString(hashTable1.toArray()));

        Integer[] array = {1, 2, 3, 4};
        array = hashTable1.toArray(array);
        System.out.println(Arrays.toString(array));

        hashTable1.remove(2);
        System.out.println(hashTable1);
        System.out.println("Количество элементов в таблице: " + hashTable1.size());

        HashTable<Integer> hashTable2 = new HashTable<>(3);
        hashTable2.add(4);
        hashTable2.add(5);
        hashTable2.add(6);

        System.out.println(hashTable2);
        System.out.println("Количество элементов в таблице: " + hashTable2.size());

        hashTable1.addAll(hashTable2);

        System.out.println(hashTable1);
        System.out.println("Проверка наличия второй хэш-таблицы в первой " + hashTable1.containsAll(hashTable2));

        hashTable1.retainAll(hashTable2);
        System.out.println(hashTable1);

        hashTable1.removeAll(hashTable2);
        System.out.println(hashTable1);

        hashTable1.clear();
        System.out.println(hashTable1);
    }
}