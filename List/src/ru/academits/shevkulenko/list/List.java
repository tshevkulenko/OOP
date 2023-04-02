package ru.academits.shevkulenko.list;

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

        if (index == 0) {
            return getFirst();
        }

        ListItem<T> item = findByIndex(index);
        return item.getValue();
    }

    public T setByIndex(int index, T value) {
        checkIndex(index);

        ListItem<T> item = findByIndex(index);
        T oldValue = item.getValue();
        item.setValue(value);

        return oldValue;
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            T removedValue = head.getValue();
            head = head.getNext();
            size--;
            return removedValue;
        }

        ListItem<T> previousItem = findByIndex(index - 1);
        ListItem<T> currentItem = findByIndex(index);
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
        }

        ListItem<T> currentItem = findByIndex(index);
        ListItem<T> previousItem = findByIndex(index - 1);
        ListItem<T> newItem = new ListItem<>(value, currentItem);
        previousItem.setNext(newItem);
        size++;
    }

    public boolean remove(T value) {
        if (value == null) {
            return false;
        }

        if (head.equals(value)) {
            head = head.getNext();
            size--;
            return true;
        }

        for (ListItem<T> currentItem = head, previousItem = null; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (currentItem.getValue().equals(value)) {
                currentItem = currentItem.getNext();

                if (previousItem != null) {
                    previousItem.setNext(currentItem);
                }

                size--;
                return true;
            }
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
        checkEmptiness();

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

        if (head == null) {
            return copy;
        }

        ListItem<T> copyValue = new ListItem<>(head.getValue());
        ListItem<T> nextItem = head.getNext();
        copy.head = copyValue;

        int i = 1;
        while (i < size) {
            copyValue.setNext(new ListItem<>(nextItem.getValue()));
            nextItem = nextItem.getNext();
            copyValue = copyValue.getNext();
            i++;
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

    private ListItem<T> findByIndex(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    private void checkEmptiness() {
        if (size == 0) {
            throw new NullPointerException("Список пуст");
        }
    }
}