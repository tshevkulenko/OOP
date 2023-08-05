package ru.academits.shevkulenko.list;

class ListItem<E> {
    private E value;
    private ListItem<E> next;

    public ListItem(E value) {
        this.value = value;
    }

    public ListItem(E value, ListItem<E> next) {
        this.value = value;
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public ListItem<E> getNext() {
        return next;
    }

    public void setNext(ListItem<E> next) {
        this.next = next;
    }
}