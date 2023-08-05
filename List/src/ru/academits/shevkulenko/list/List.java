package ru.academits.shevkulenko.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class List<E> {
    private ListItem<E> head;
    private int size;

    public int getSize() {
        return size;
    }

    public E getFirst() {
        checkEmptiness();
        return head.getValue();
    }

    public E getByIndex(int index) {
        checkIndex(index);

        ListItem<E> item = getItemByIndex(index);
        return item.getValue();
    }

    public E setByIndex(int index, E value) {
        checkIndex(index);

        ListItem<E> item = getItemByIndex(index);
        E oldValue = item.getValue();
        item.setValue(value);

        return oldValue;
    }

    public E removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            removeFirst();
        }

        ListItem<E> previousItem = getItemByIndex(index - 1);
        ListItem<E> currentItem = previousItem.getNext();
        E removedValue = currentItem.getValue();
        previousItem.setNext(currentItem.getNext());
        size--;

        return removedValue;
    }

    public void addFirst(E value) {
        head = new ListItem<>(value, head);
        size++;
    }

    public void addByIndex(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Заданный индекс должен быть в пределах от 0 до " + size + " включительно");
        }

        if (index == 0) {
            addFirst(value);
            return;
        }

        ListItem<E> previousItem = getItemByIndex(index - 1);
        ListItem<E> currentItem = previousItem.getNext();
        previousItem.setNext(new ListItem<>(value, currentItem));

        size++;
    }

    public boolean remove(E value) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.getValue(), value)) {
            head = head.getNext();
            size--;
            return true;
        }

        ListItem<E> previousItem = head;

        while (previousItem.getNext() != null) {
            ListItem<E> currentItem = previousItem.getNext();

            if (Objects.equals(currentItem.getValue(), value)) {
                previousItem.setNext(currentItem.getNext());
                size--;

                return true;
            }

            previousItem = currentItem;
        }

        return false;
    }

    public E removeFirst() {
        checkEmptiness();

        E removedValue = head.getValue();
        head = head.getNext();
        size--;
        return removedValue;
    }

    public void reverse() {
        if (size <= 1) {
            return;
        }

        ListItem<E> currentItem = head;
        ListItem<E> previousItem = null;

        for (ListItem<E> nextItem = currentItem.getNext(); nextItem != null; previousItem = currentItem, currentItem = nextItem, nextItem = nextItem.getNext()) {
            currentItem.setNext(previousItem);
        }

        currentItem.setNext(previousItem);
        head = currentItem;
    }

    public List<E> copy() {
        List<E> copy = new List<>();

        if (size == 0) {
            return copy;
        }

        copy.size = size;
        copy.head = new ListItem<>(head.getValue());

        for (ListItem<E> currentItem = head.getNext(), currentCopyItem = copy.head; currentItem != null; currentItem = currentItem.getNext()) {
            currentCopyItem.setNext(new ListItem<>(currentItem.getValue()));
            currentCopyItem = currentCopyItem.getNext();
        }

        return copy;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        ListItem<E> currentItem = head;

        for (; currentItem.getNext() != null; currentItem = currentItem.getNext()) {
            stringBuilder.append(currentItem.getValue()).append(", ");
        }

        stringBuilder.append(currentItem.getValue()).append('}');
        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом " + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }
    }

    private ListItem<E> getItemByIndex(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    private void checkEmptiness() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст");
        }
    }
}