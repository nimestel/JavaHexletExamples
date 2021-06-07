package io.hexlet.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private final List<Pair> keyAndValues = new ArrayList<>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return keyAndValues.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return size() == 0;
        // END
    }

    @Override
    public boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        for (Pair p : keyAndValues) {
            if (p.getKey().equals(key)) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        for (Pair p : keyAndValues) {
            if (p.getValue().equals(value)) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)
        for (Pair p : keyAndValues) {
            if (p.getKey().equals(key)) {
                return p.getValue();
            }
        }
        return null;
        // END
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        // BEGIN (write your solution here)
        V result = get(key);
        return result == null ? defaultValue : result;
        // END
    }

    @Override
    public V put(K key, V value) {
        // BEGIN (write your solution here)
        V result = value;
        // если такого ключа еще нет, добавляем
        if (!containsKey(key)) {
            keyAndValues.add(new Pair(key, value));
            return null;
        }
        // иначе ищем ключ
        for (Pair p : keyAndValues) {
            if (p.getKey().equals(key)) {
                // сохраняем в переменную старое значение
                result = p.getValue();
                // назначаем новое
                p.setValue(value);
                break;
            }
        }
        // и возвращаем старое затертое
        return result;
        // END
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)
        V value = null;
        // проходим по всем ключам
        for (int i = 0; i < keyAndValues.size(); i++) {
            // получаем значение
            final Pair p = keyAndValues.get(i);
            // если ключ этого значения равен искомому
            if (p.getKey().equals(key)) {
                // сохраняем значение удаляемого значения
                value = p.getValue();
                // удаляем его
                keyAndValues.remove(i);
                // и возвращаем значение
                return value;
            }
        }
        return null;
        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Entry<K, V>>) (Set) m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        keyAndValues.clear();
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : keyAndValues) {
            keys.add(p.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        List<V> values = new ArrayList<>();
        for (Pair p : keyAndValues) {
            values.add(p.getValue());
        }
        return values;
        // END
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>) (Set) new HashSet<>(keyAndValues);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) {
                return false;
            }
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode())
                    ^ (value == null ? 0 : value.hashCode());
        }
    }
}