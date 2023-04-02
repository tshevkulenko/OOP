package ru.academits.shevkulenko.hashtable.main;

import ru.academits.shevkulenko.hashtable.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();

        System.out.println("Проверка на пустоту: " + hashTable.isEmpty());

        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(3);

        System.out.println("Проверка на пустоту: " + hashTable.isEmpty());
        System.out.println(hashTable);
        System.out.println("Количество элементов в таблице: " + hashTable.size());

        int number = 2;
        System.out.println("Проверка наличия элемента " + number + " : " + hashTable.contains(2));

        System.out.print("Работа итератора: ");

        for (Integer element : hashTable){
            System.out.print(element + " ");
        }

        System.out.println();

        System.out.println(Arrays.toString(hashTable.toArray()));

        Integer[] array = {1, 2, 3, 4};
        array = hashTable.toArray(array);
        System.out.println(Arrays.toString(array));

        hashTable.remove(2);
        System.out.println(hashTable);
        System.out.println("Количество элементов в таблице: " + hashTable.size());

        HashTable<Integer> hashTable1 = new HashTable<>(3);
        hashTable1.add(4);
        hashTable1.add(5);
        hashTable1.add(6);

        System.out.println(hashTable1);
        System.out.println("Количество элементов в таблице: " + hashTable1.size());

        hashTable.addAll(hashTable1);

        System.out.println(hashTable);
        System.out.println("Проверка наличия второй хэш-таблицы в первой " + hashTable.containsAll(hashTable1));

        hashTable.retainAll(hashTable1);
        System.out.println(hashTable);

        hashTable.removeAll(hashTable1);
        System.out.println(hashTable);

        hashTable.clear();
        System.out.println(hashTable);
    }
}
