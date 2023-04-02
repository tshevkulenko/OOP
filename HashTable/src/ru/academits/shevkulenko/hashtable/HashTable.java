package ru.academits.shevkulenko.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final ArrayList<T>[] hashTable;
    private int size;
    private int modificationsCount;

    public HashTable() {
        int CAPACITY = 10;
        hashTable = (ArrayList<T>[]) new ArrayList[CAPACITY];
        size = 0;
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размер таблицы должен быть не менее 0");
        } else {
            hashTable = (ArrayList<T>[]) new ArrayList[capacity];
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

    private int getHash(Object o) {
        return Math.abs(o.hashCode() % hashTable.length);
    }

    @Override
    public boolean contains(Object o) {
        int hash = getHash(o);

        if (hashTable[hash] == null) {
            return false;
        }

        for (T element : hashTable[hash]) {
            if (element.equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<T>{
        private int currentIndex = -1;
        private int currentHash = 0;
        private final int modificationsCount = HashTable.this.modificationsCount;
        private int elementsCount = 0;

        @Override
        public boolean hasNext() {
            return elementsCount < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modificationsCount != HashTable.this.modificationsCount) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }

            while (hashTable[currentHash] == null || hashTable[currentHash].size() - 1 == currentIndex) {
                ++currentHash;
                currentIndex = -1;
            }

            if (currentIndex < hashTable[currentHash].size() - 1) {
                ++currentIndex;
            }

            ++elementsCount;
            return hashTable[currentHash].get(currentIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for(Object elem : this){
            array[i] = elem;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] array = (T1[]) toArray();

        if (a.length < size){
            return array;
        }

        System.arraycopy(array,0, a,0, size);
        return a;
    }

    @Override
    public boolean add(T t) {
        if (contains(t)) {
            return false;
        }

        int hash = getHash(t);

        if (hashTable[hash] == null) {
            hashTable[hash] = new ArrayList<>();
        }

        hashTable[hash].add(t);
        size++;
        modificationsCount++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (size != 0) {
            if (!contains(o)) {
                return false;
            }

            int hash = getHash(o);
            modificationsCount++;
            size--;
            return hashTable[hash].remove(o);
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (Object element : c) {
            if (!contains(element)) {
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

        boolean isAdded = false;

        for (T element : c){
            if (add(element)){
                isAdded = true;
            }
        }

        return isAdded;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;

        for (Object element : c){
            if (remove(element)){
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;

        for (ArrayList<T> list : hashTable) {
            if (list != null) {
                isRemoved = list.retainAll(c);
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size > 0) {
            ++modificationsCount;
        }

        Arrays.fill(hashTable, null);
        size = 0;
    }

    @Override
    public String toString(){
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;

        for (ArrayList<T> arrayList : hashTable){
            if (arrayList != null) {
                stringBuilder.append(index).append(arrayList).append('\n');
            } else {
                stringBuilder.append(index).append("[]\n");
            }

            index++;
        }

        return stringBuilder.toString();
    }
}
