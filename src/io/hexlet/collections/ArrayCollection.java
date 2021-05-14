package io.hexlet.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayCollection<T> implements Collection<T> {

    private T[] array = (T[]) new Object[1];

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (T element : this) {
            if (o.equals(element)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        T[] arrayNew = (T[]) new Object[size];
        System.arraycopy(array, 0, arrayNew, 0, size);
        return arrayNew;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length >= size) {
            System.arraycopy(array, 0, a, 0, size);
            return a;
        }
        T1[] arrayNew = (T1[]) new Object[size];
        System.arraycopy(array, 0, arrayNew, 0, size);
        return arrayNew;
    }

    @Override
    public boolean add(final T t) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size++] = t;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if (array[size - 1].equals(o)) {
            size--;
            return true;
        }

        for (int i = 0; i <= size - 1; i++) {
            if (o.equals(array[i])) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (Object element : c) {
            remove(element);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (int i = 0; i < size(); i++) {
            T element = array[i];
            if (!c.contains(element)) {
                remove(element);
                i--;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        array = (T[]) new Object[1];
        size = 0;
    }

    private class ElementsIterator implements Iterator<T> {
        private int index = 0;

        private boolean wasNextCalled = false;

        @Override
        public boolean hasNext() {
            return ArrayCollection.this.size > index;
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            wasNextCalled = true;
            return ArrayCollection.this.array[index++];
        }

        @Override
        public void remove() throws IllegalStateException {
            if (!wasNextCalled) throw new IllegalStateException();
            wasNextCalled = false;
            ArrayCollection.this.remove(--index);
        }
    }
}
