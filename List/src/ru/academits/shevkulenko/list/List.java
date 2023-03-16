package ru.academits.shevkulenko.list;

public class List<T> {
    private ListItem<T> head;
    private int size;

    public List() {
    }

    public int getSize() {
        return size;
    }

    public T getFirstItem() {
        return head.getValue();
    }

    public T getByIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом" + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        if (index == 0) {
            return getFirstItem();
        }

        int counter = 0;
        T item = head.getValue();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == index) {
                item = p.getValue();
                break;
            } else {
                counter++;
            }
        }

        return item;
    }

    public T changeByIndex(int index, T item) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом" + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        int counter = 0;
        T changedItem = head.getValue();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (counter == index) {
                changedItem = p.getValue();
                p.setValue(item);
                break;
            } else {
                counter++;
            }
        }
        return changedItem;
    }

    public T removeByIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом" + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        if (index == 0) {
            T removedItem = head.getValue();
            head = head.getNext();
            size--;
            return removedItem;
        }

        int counter = 0;
        T removedItem = null;

        for (ListItem<T> p = head.getNext(), prev = head; p != null; prev = p, p = p.getNext()) {
            if (counter == index) {
                removedItem = p.getValue();
                prev.setNext(p.getNext());
                size--;
                break;
            } else {
                counter++;
            }
        }

        return removedItem;
    }

    public void addFirstItem(T item) {
        head = new ListItem<>(item, head);
        size++;
    }

    public void addByIndex(int index, T item) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом" + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        if (index == 0) {
            addFirstItem(item);
        }

        int counter = 0;

        for (ListItem<T> p = head.getNext(), prev = head; prev != null; prev = p, p = p.getNext()) {
            if (counter == index) {
                p = new ListItem<>(item, p);
                prev.setNext(p);
                size++;
                break;
            } else {
                counter++;
            }
        }
    }

    public boolean remove(T item) {
        boolean isDeleted = false;

        for (ListItem<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getValue().equals(item)) {
                isDeleted = true;
                p = p.getNext();

                if (prev != null) {
                    prev.setNext(p);
                }

                size--;
                break;
            }
        }

        return isDeleted;
    }

    public T removeFirstItem() {
        T removedItem = head.getValue();
        head = head.getNext();
        size--;
        return removedItem;
    }

    public void reverse() {
        ListItem<T> p = head;
        ListItem<T> prev = null;

        for (ListItem<T> next = p.getNext(); next != null; prev = p, p = next, next = next.getNext()) {
            p.setNext(prev);
        }

        p.setNext(prev);
        head = p;
    }

    public List<T> copy() {
        List<T> copy = new List<>();

        if (head == null) {
            return copy;
        }

        ListItem<T> copyItem = new ListItem<>(head.getValue());
        ListItem<T> next = head.getNext();
        copy.head = copyItem;

        int i = 1;
        while (i < getSize()) {
            copyItem.setNext(new ListItem<>(next.getValue()));
            next = next.getNext();
            copyItem = copyItem.getNext();
            i++;
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        ListItem<T> p = head;
        for (; p.getNext() != null; p = p.getNext()) {
            stringBuilder.append(p.getValue().toString()).append(", ");
        }

        stringBuilder.append(p.getValue().toString()).append("}");
        return stringBuilder.toString();
    }
}