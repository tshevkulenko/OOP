package ru.academits.shevkulenko.list;

import java.util.NoSuchElementException;

public class List<T> {
    private ListItem<T> head;
    private int size;

    public int getSize() {
        return size;
    }

    public T getFirst() {
        checkEmptiness();
        return head.getValue();
    }

    public T getByIndex(int index) {
        checkIndex(index);

        ListItem<T> item = getItemByIndex(index);
        return item.getValue();
    }

    public T setByIndex(int index, T value) {
        checkIndex(index);

        ListItem<T> item = getItemByIndex(index);
        T oldValue = item.getValue();
        item.setValue(value);

        return oldValue;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            removeFirst();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        ListItem<T> currentItem = previousItem.getNext();
        T removedValue = currentItem.getValue();
        previousItem.setNext(currentItem.getNext());
        size--;

        return removedValue;
    }

    public void addFirst(T value) {
        head = new ListItem<>(value, head);
        size++;
    }

    public void addByIndex(int index, T value) {
        checkIndex(index);

        if (index == 0) {
            addFirst(value);
            return;
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        ListItem<T> currentItem = previousItem.getNext();
        previousItem.setNext(new ListItem<>(value, currentItem));

        size++;
    }

    public boolean remove(T value) {
        if (head == null) {
            return false;
        }

        if (head.getValue().equals(value)) {
            head = head.getNext();
            size--;
            return true;
        }

        ListItem<T> previousItem = head;

        while (previousItem.getNext() != null) {
            ListItem<T> currentItem = previousItem.getNext();

            if (currentItem.getValue().equals(value)) {
                previousItem.setNext(currentItem.getNext());
                size--;

                return true;
            }

            previousItem = currentItem;
        }

        return false;
    }

    public T removeFirst() {
        checkEmptiness();

        T removedValue = head.getValue();
        head = head.getNext();
        size--;
        return removedValue;
    }

    public void reverse() {
        if (size == 0) {
            return;
        }

        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        for (ListItem<T> nextItem = currentItem.getNext(); nextItem != null; previousItem = currentItem, currentItem = nextItem, nextItem = nextItem.getNext()) {
            currentItem.setNext(previousItem);
        }

        currentItem.setNext(previousItem);
        head = currentItem;
    }

    public List<T> copy() {
        List<T> copy = new List<>();
        copy.size = size;

        if (size == 0) {
            return copy;
        }

        copy.head = new ListItem<T>(head.getValue());

        for (ListItem<T> currentItem = head.getNext(), currentCopyItem = copy.head; currentItem != null; currentItem = currentItem.getNext()) {
            ListItem<T> newItem = new ListItem<T>(currentItem.getValue());
            currentCopyItem.setNext(newItem);
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

        ListItem<T> currentItem = head;

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

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> item = head;

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