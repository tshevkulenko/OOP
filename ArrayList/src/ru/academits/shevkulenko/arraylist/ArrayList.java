package ru.academits.shevkulenko.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modificationsCount;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Размер списка не может быть меньше 0. Переданный размер равен " + capacity);
        }

        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        add(size, element);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index >= 0) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом " + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        ensureCapacity(size + c.size());
        System.arraycopy(items, index, items, index + c.size(), size - index);

        int i = index;

        for (E element : c) {
            items[i] = element;
            ++i;
        }

        modificationsCount++;
        size += c.size();

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;

        for (Object o : c) {
            if (contains(o)) {
                remove(o);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;

        for (int i = 0; i < size; i++) {
            if (!c.contains(items[i])) {
                remove(i);
                i--;
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        ++modificationsCount;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом " + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        increaseCapacity();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        modificationsCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        modificationsCount++;
        E removedItem = items[index];
        int length = size - index - 1;

        if (length > 0) {
            System.arraycopy(items, index + 1, items, index, length);
        }

        size--;
        items[size] = null;
        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public int getCapacity() {
        return items.length;
    }

    public void increaseCapacity() {
        if (size >= items.length) {
            if (items.length == 0) {
                items = Arrays.copyOf(items, 1);
            }

            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{");

        for (int i = 0; i < size; ++i) {
            string.append(items[i]).append(",");
        }

        if (size > 0) {
            string.deleteCharAt(string.length() - 1);
        }

        return string.append("}").toString();
    }

    private class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModificationsCount = modificationsCount;

        @Override
        public boolean hasNext() {
            return currentIndex < size - 1;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (initialModificationsCount != modificationsCount) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом " + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }
    }
}