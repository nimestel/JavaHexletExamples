package io.hexlet.datastructures;

import java.util.*;

public class HashSet<T> implements Set<T> {

    static final Boolean EXIST = true;

    final Map<T, Boolean> elements = new HashMap<>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return elements.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return elements.isEmpty();
        // END
    }

    @Override
    public boolean contains(Object o) {
        // BEGIN (write your solution here)
        return elements.containsKey(o);
        // END
    }

    @Override
    public Iterator<T> iterator() {
        // BEGIN (write your solution here)
        return elements.keySet().iterator();
        // END
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        return elements.keySet().toArray();
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        return elements.keySet().toArray(a);
        // END
    }

    @Override
    public boolean add(T t) {
        // BEGIN (write your solution here)
        return elements.put(t, EXIST) == null;
        // END
    }

    @Override
    public boolean remove(Object o) {
        // BEGIN (write your solution here)
        return elements.remove(o) == EXIST;
        // END
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // BEGIN (write your solution here)
        boolean result = false;
        if (c != null) {
            for (Object col : c) {
                result = false;
                if (col == null) return false;
                for (T element : elements.keySet()) {
                    if (element.equals(col)) result = true;
                }
                if (!result) return false;
            }
        }
        return result;
        // END
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        // BEGIN (write your solution here)
        if (c != null) {
            for (T element : c) {
                elements.put(element, EXIST);
            }
        }
        return true;
        // END
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // BEGIN (write your solution here)
        return true;
        // END
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // BEGIN (write your solution here)
        if (c != null) {
            for (Object col : c) {
                elements.remove((T) col);
            }
            return true;
        }
        return false;
        // END
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        elements.clear();
        // END
    }

    @Override
    public boolean equals(Object o) {
        // BEGIN (write your solution here)
        return elements.equals(o);
        // END
    }

    @Override
    public int hashCode() {
        // BEGIN (write your solution here)
        return elements.hashCode();
        // END
    }
}