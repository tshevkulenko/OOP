package ru.academits.shevkulenko.hashtable;

import java.util.*;

@SuppressWarnings("unchecked")
public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] lists;
    private int size;
    private int modificationsCount;
    private final static int DEFAULT_CAPACITY = 10;

    public HashTable() {
        lists = (ArrayList<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размер таблицы должен быть больше 0, сейчас " + capacity);
        }

        lists = (ArrayList<E>[]) new ArrayList[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private final int startModificationsCount = modificationsCount;
        private int listIndex = -1;
        private int arrayIndex;
        private int iteratedElementsCount;

        @Override
        public boolean hasNext() {
            return iteratedElementsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (startModificationsCount != modificationsCount) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }

            while (lists[arrayIndex] == null || lists[arrayIndex].size() - 1 == listIndex) {
                ++arrayIndex;
                listIndex = -1;
            }

            ++listIndex;
            ++iteratedElementsCount;
            return lists[arrayIndex].get(listIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (Object e : this) {
            array[i] = e;
            i++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] array = (T[]) toArray();

        if (a.length < size) {
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }

        System.arraycopy(array, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);
        size++;
        modificationsCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] != null && lists[index].remove(o)) {
            modificationsCount++;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int removedElementsCount = 0;

        for (Object element : c) {
            int i = getIndex(element);

            if (lists[i] != null && lists[i].size() > 0) {
                while (lists[i].remove(element)) {
                    removedElementsCount++;
                }
            }
        }

        size -= removedElementsCount;
        modificationsCount++;
        return removedElementsCount != 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int removedElementsCount = 0;

        for (ArrayList<E> list : lists) {
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (!c.contains(list.get(i))) {
                        list.remove(list.get(i));
                        removedElementsCount++;
                        modificationsCount++;
                        size--;
                        i--;
                    }
                }
            }
        }

        return removedElementsCount != 0;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        if (size > 0) {
            ++modificationsCount;
        }

        Arrays.fill(lists, null);
        size = 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;

        for (ArrayList<E> arrayList : lists) {
            if (arrayList != null) {
                stringBuilder.append(index).append(arrayList).append(System.lineSeparator());
            } else {
                stringBuilder.append(index).append("[]").append(System.lineSeparator());
            }

            index++;
        }

        return stringBuilder.toString();
    }
}