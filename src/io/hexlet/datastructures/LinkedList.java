package io.hexlet.datastructures;

import java.util.*;

public class LinkedList<T> implements List<T> {

    private Item<T> firstInList = null;

    private Item<T> lastInList = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        //return indexOf(o) != -1;
        // если лист пуст дальше можно не проверять
        if (firstInList == null) {
            return false;
        }
        // ищем по листу совпадающее значение
        for (T elementInItem : this) {
            if (Objects.equals(o, elementInItem)) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator(0);
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        final T[] newM = (T[]) new Object[this.size()];
        int i = 0;

        for (T element : this) {
            newM[i++] = element;
        }
        return newM;
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        if (a.length < size) {
            a = (T1[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }
        int i = 0;

        Object[] result = a;
        for (Item<T> e = firstInList; e != null; e = e.nextItem) {
            result[i++] = e.element;
        }
        if (a.length > size) {
            a[size] = null;
        }

        return a;
        // END
    }

    @Override
    public boolean add(final T newElement) {
        // BEGIN (write your solution here)
        //лист пуст, создаем с нуля, присваиваем первый и он же последний элемент
        if (this.size() == 0) {
            this.firstInList = new Item<>(newElement, null, null);
            this.lastInList = firstInList;
            //в листе один элемент, добавляем новый в конец и назначаем его ласт, назначаем
            // ссылку на него первому элементу
        } else if (this.size() == 1) {
            this.lastInList = new Item<>(newElement, this.firstInList, null);
            this.firstInList.nextItem = this.lastInList;
        } else {
            // в листе много элементов, фигачим последний в конец и ставим ссылку на него
            // "следующий" прежнему последнему элементу
            final Item<T> newLast = new Item<>(newElement, this.lastInList, null);
            this.lastInList.nextItem = newLast;
            this.lastInList = newLast;
        }
        // увеличиваем размер листа
        size++;
        return true;
        // END
    }

    @Override
    public void add(final int index,
                    final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        // нечего удалять, лист пуст
        if (size == 0) {
            return false;
        }
        // идем с начала листа
        Item<T> current = this.firstInList;
        // листаем, пока есть следующие не пустые элементы и пока мы не нашли нужный объект
        while (current.nextItem != null && !current.element.equals(o)) {
            current = current.nextItem;
        }
        // если дошли до нужного узла с искомым элементом, а не пролистали все и ничего не нашли
        if (current.element.equals(o)) {
            // список из одного элемента и мы удалим его
            if (this.size() == 1) {
                this.firstInList = null;
                this.lastInList = null;
            } else {
                // искомый элемент первый в списке
                if (current == firstInList) {
                    // назначили первым следующий элемент
                    firstInList = current.nextItem;
                    // а предыдущий у него занулили
                    firstInList.prevItem = null;
                }
                // искомый элемент последний в списке
                if (current == lastInList) {
                    // назначили последним предыдущий элемент
                    lastInList = current.prevItem;
                    // а следующий у него занулили
                    lastInList.nextItem = null;
                }
                // искомый элемент где-то в середине
                if (current.nextItem != null && current.prevItem != null) {
                    // у предыдущего элемента следующим назначили элемент следующий для текущего
                    // узла
                    current.prevItem.nextItem = current.nextItem;
                    // у следующего элемента предыдущим назначили элемент предыдущий для текущего
                    // узла
                    current.nextItem.prevItem = current.prevItem;
                }
            }
            size--;
            return true;
        }
        return false;
        // END
    }

    @Override
    public T remove(final int index) throws IndexOutOfBoundsException {
        // BEGIN (write your solution here)
        int i = 0;
        if (size <= index || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // ищем узел по индексу с первого элемента
        Item<T> current = this.firstInList;

        while (i != index) {
            current = current.nextItem;
            i++;
        }

        // нашли, удаляем в зависимости от положения в листе
        if (current != null) {
            if (this.size() == 1) {
                this.firstInList = null;
                this.lastInList = null;
            } else {
                if (current == firstInList) {
                    firstInList = current.nextItem;
                    firstInList.prevItem = null;
                }
                if (current == lastInList) {
                    lastInList = current.prevItem;
                    lastInList.nextItem = null;
                }
                if (current.nextItem != null && current.prevItem != null) {
                    current.prevItem.nextItem = current.nextItem;
                    current.nextItem.prevItem = current.prevItem;
                }
            }
            size--;
            // возвращаем значение удаленного узла
            return current.element;
        }
        return null;
        // END
    }

    private void remove(final Item<T> current) {
        // BEGIN (write your solution here)
        // удаляем переданный узел
        if (current != null) {
            if (this.size() == 1) {
                this.firstInList = null;
                this.lastInList = null;
            } else {
                // искомый элемент первый в списке
                if (current == firstInList) {
                    // назначили первым следующий элемент
                    firstInList = current.nextItem;
                    // а предыдущий у него занулили
                    firstInList.prevItem = null;
                }
                // искомый элемент последний в списке
                if (current == lastInList) {
                    // назначили последним предыдущий элемент
                    lastInList = current.prevItem;
                    // а следующий у него занулили
                    lastInList.nextItem = null;
                }
                // искомый элемент где-то в середине
                if (current.nextItem != null && current.prevItem != null) {
                    // у предыдущего элемента следующим назначили элемент следующий для текущего
                    // узла
                    current.prevItem.nextItem = current.nextItem;
                    // у следующего элемента предыдущим назначили элемент предыдущий для текущего
                    // узла
                    current.nextItem.prevItem = current.prevItem;
                }
            }
            // уменьшаем размер списка
            size--;
        }
        // END
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(final int index,
                          final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        this.removeIf(item -> !c.contains(item));
        return true;
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        this.firstInList = null;
        this.lastInList = null;
        size = 0;
        // END
    }

    @Override
    public List<T> subList(final int start,
                           final int end) {
        return null;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object o) {
        int index = size;
        // защита от NullPointerException
        if (o == null) {
            // идем от последнего узла к первому
            for (Item<T> x = lastInList; x != null; x = x.prevItem) {
                index--;
                if (x.element == null)
                    return index;
            }
        } else {
            for (Item<T> x = lastInList; x != null; x = x.prevItem) {
                index--;
                if (o.equals(x.element))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(final Object o) {
        // BEGIN (write your solution here)
        int index = 0;
        // защита от NullPointerException
        if (o == null) {
            for (Item<T> current = firstInList; current != null; current = current.nextItem) {
                if (current.element == null)
                    return index;
                index++;
            }
        } else {
            for (Item<T> current = firstInList; current != null; current = current.nextItem) {
                if (o.equals(current.element))
                    return index;
                index++;
            }
        }
        return -1;
        // END
    }

    @Override
    public T set(final int targetIndex,
                 final T element) {
        // BEGIN (write your solution here)
        if (targetIndex < 0 || targetIndex >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int index = 0;
        for (Item<T> x = firstInList; x != null; x = x.nextItem) {
            if (index == targetIndex) {
                // метод возвращает определенный тип, для приведения к нему исп доп.переменную
                T tempElement = x.element;
                x.element = element;
                return tempElement;
            }
            index++;
        }
        return null;
    }

    @Override
    public T get(final int index) {
        // BEGIN (write your solution here)
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        return getItemByIndex(index).element;
        // END
    }

    private Item<T> getItemByIndex(final int targetIndex) {
        // BEGIN (write your solution here) An auxiliary method for searching for node by index.
        int index = 0;
        // идем с первого до последнего не пустого узла
        for (Item<T> x = firstInList; x != null; x = x.nextItem) {
            // плюсим счетчик, как только совпал возвращаем узел
            if (index == targetIndex) return x;
            index++;
        }
        return null;
        // END
    }

    private static class Item<T> {

        private T element;

        private Item<T> nextItem;

        private Item<T> prevItem;

        Item(final T element,
             final Item<T> prevItem,
             final Item<T> nextItem) {
            this.element = element;
            this.nextItem = nextItem;
            this.prevItem = prevItem;
        }

        public Item<T> getNextItem() {
            return nextItem;
        }

        public Item<T> getPrevItem() {
            return prevItem;
        }
    }

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> currentItemInIterator;

        private Item<T> lastReturnedItemFromIterator;

        private int index;

        ElementsIterator(final int index) {
            // BEGIN (write your solution here)
            // если мы на последнем элементе, то null, иначе задаем текущий элемент по
            // текущему индексу
            this.currentItemInIterator = (index == size) ? null : getItemByIndex(index);
            this.index = index;
            // END
        }

        @Override
        public boolean hasNext() {
            // BEGIN (write your solution here)
            return LinkedList.this.size > index;
            // END
        }

        @Override
        public T next() {
            // BEGIN (write your solution here)
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // сохраняем текущий как последний
            lastReturnedItemFromIterator = currentItemInIterator;
            // в текущем получаем следующий
            currentItemInIterator = currentItemInIterator.getNextItem();
            // увеличивам индекс
            index++;
            // и возвращаем содержимое узла
            return lastReturnedItemFromIterator.element;
            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)
            return index > 0;
            // END
        }

        @Override
        public T previous() {
            // BEGIN (write your solution here)
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            // если текущий элемент null, мы в конце листа
            if (currentItemInIterator == null) {
                lastReturnedItemFromIterator = currentItemInIterator = lastInList;
            } else {
                // иначе присваиваем последнему текущий узел, а текущему предыдущий
                lastReturnedItemFromIterator
                        = currentItemInIterator
                        = currentItemInIterator.getPrevItem();
            }
             /*lastReturnedItemFromIterator
             = currentItemInIterator
             = (currentItemInIterator == null) ? lastInList : currentItemInIterator.prevItem;*/
            index--;
            return lastReturnedItemFromIterator.element;
            //END
        }

        @Override
        public void add(final T element) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)
            if (lastReturnedItemFromIterator == null) {
                throw new IllegalStateException();
            }
            // последнему узлу к которому обращались присваиваем знаечние
            lastReturnedItemFromIterator.element = element;
            // END
        }

        @Override
        public int previousIndex() {
            // BEGIN (write your solution here)
            return index - 1;
            // END
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)
            return index;
            // END
        }

        @Override
        public void remove() {
            // BEGIN (write your solution here)
            if (lastReturnedItemFromIterator == null) {
                throw new IllegalStateException();
            }
            // удаляем по значению последний узел к которому обращались
            LinkedList.this.remove(lastReturnedItemFromIterator);
            // и зануляем последний узел
            lastReturnedItemFromIterator = null;
            // уменьшаем индекс
            index--;
            // END
        }
    }
}
