package ru.academits.shevkulenko.list.main;

import ru.academits.shevkulenko.list.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new List<>();

        list.addFirstItem(3);
        list.addFirstItem(2);
        list.addFirstItem(1);

        System.out.println(list);
        System.out.println("Количество элементов в списке: " + list.getSize());
        System.out.println("Первый элемент в списке: " + list.getFirstItem());

        int index = 1;
        System.out.println("Элемент списка по индексу " + index + ": " + list.getByIndex(index));

        System.out.println("Измененный элемент списка: " + list.changeByIndex(index, 4));
        System.out.println("Список после изменения: " + list);

        System.out.println("Удаленный элемент списка: " + list.removeByIndex(index));
        System.out.println("Список после удаления элемента: " + list);

        list.addByIndex(index, 5);
        System.out.println("Список после вставки элемента: " + list);

        if (list.remove(5)) {
            System.out.println("Элемент удален");
        } else {
            System.out.println("В списке нет такого элемента");
        }

        System.out.println("Список после удаления: " + list);

        list.reverse();
        System.out.println("Развернутый список: " + list);

        List<Integer> copy = list.copy();
        System.out.println("Скопированный список: " + copy);

        System.out.println("Удаленный элемент списка: " + list.removeFirstItem());
        System.out.println("Список после удаления элемента: " + list);
    }
}