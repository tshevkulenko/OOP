package ru.academits.shevkulenko.arraylist;

import java.util.*;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] items;
    private int size;
    private int modificationsCount;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размер списка должен быть не менее 0");
        } else {
            items = (T[]) new Object[capacity];
        }
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
    public Iterator<T> iterator() {
        return new newIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <S> S[] toArray(S[] a) {
        S[] array = (S[]) toArray();
        if (a.length < size) {
            return array;
        }

        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        increaseCapacity();
        items[size] = t;
        size++;
        modificationsCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = this.indexOf(o);

        if (index >= 0) {
            this.remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(size + c.size());
        modificationsCount++;

        for (T item : c) {
            items[size] = item;
            size++;
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.isEmpty()) {
            return false;
        }

        if (index == size) {
            return addAll(c);
        }

        int length = c.size();
        ensureCapacity(size + length);
        System.arraycopy(items, index, items, index + length, size - index);

        size += length;
        modificationsCount++;

        for (T item : c) {
            items[index] = item;
            index++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;

        for (Object o : c) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(items[i], o)) {
                    remove(i);
                    isRemoved = true;
                    ++modificationsCount;
                    --i;
                }
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
        ++modificationsCount;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом" + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом" + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        T oldItem = get(index);
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом" + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        increaseCapacity();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        modificationsCount++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Не существует элемента с индексом" + index +
                    ". Заданный индекс должен быть в пределах от 0 до " + (size - 1));
        }

        modificationsCount++;
        T item = get(index);
        int length = size - index - 1;

        if (length > 0) {
            System.arraycopy(items, index + 1, items, index, length);
        }

        size--;
        items[size] = null;
        return item;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(items[i]))
                return i;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    public int getCapacity() {
        return items.length;
    }

    public void increaseCapacity() {
        if (size >= items.length) {
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
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        while (true) {
            stringBuilder.append(iterator.next());

            if (!iterator.hasNext()) {
                return stringBuilder.append(']').toString();
            }

            stringBuilder.append(',').append(' ');
        }
    }

    private class newIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int startModificationsCount = modificationsCount;

        @Override
        public boolean hasNext() {
            return currentIndex < size - 1;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (startModificationsCount != modificationsCount) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }
}