package io.hexlet.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayCollectionTest {

    Collection<Integer> testInstance;

    @BeforeEach
    public void setUp() {
        testInstance = new ArrayCollection<>();
    }

    @Test
    public void testHasNextInIterator() {
        assertFalse(testInstance.iterator().hasNext());

        testInstance.add(65);
        assertTrue(testInstance.iterator().hasNext());
    }

    @Test
    public void testNextInIterator() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            testInstance.iterator().next();
        });
    }

    @Test
    public void testSizeWhenSizeIs0() {

        testInstance.add(1);
        testInstance.remove(1);
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testSizeWhenSizeIs1() {
        testInstance.add(10);
        assertEquals(1, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() {
        testInstance.add(1);
        testInstance.remove(1);
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testIsEmptyWhenIsNoEmpty() {
        testInstance.add(10);
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContains() {
        testInstance.add(1);
        testInstance.add(2);

        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testIterator() {
        for (int i = 3; i < 15; i++) {
            testInstance.add(i);
        }
        int i = 0;
        System.out.println("\n testIterator");
        System.out.format("Size: %s\n", testInstance.size());
        for (final Integer iT : testInstance) {
            System.out.format("Element %s: %s \n", i++, iT);
        }
        if (i != testInstance.size()) {
            throw new RuntimeException("ElementsIterator does not working!");
        }
    }

    @Test
    public void testToArray() {
        for (int i = 1; i < 15; i++) {
            testInstance.add(i);
        }
        testInstance.remove(10);
        testInstance.remove(8);
        if (testInstance.toArray().length != testInstance.size()) {
            throw new RuntimeException("The returned array does not "
                    + "match the length of the "
                    + "collection.");
        }
    }

    @Test
    public void testAdd() {
        for (int i = -10; i < 15; i++) {
            testInstance.add(i);
        }

        assertEquals(25, testInstance.size());
        assertFalse(testInstance.isEmpty());

    }

    @Test
    public void testRemoveFirstElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveCenterElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.remove(3);

        assertEquals(4, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.remove(4);

        assertEquals(3, testInstance.size());
        assertFalse(testInstance.contains(4));
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveEverElementFromBeginToEnd() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance.remove(1);
        assertEquals(3, testInstance.size());
        assertFalse(testInstance.contains(1));
        assertFalse(testInstance.isEmpty());

        testInstance.remove(2);
        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(3);
        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(4);
        assertEquals(0, testInstance.size());
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testRemoveEverElementFromEndToBegin() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance.remove(4);
        assertEquals(3, testInstance.size());
        assertFalse(testInstance.contains(4));
        assertFalse(testInstance.isEmpty());

        testInstance.remove(3);
        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(2);
        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(1);
        assertEquals(0, testInstance.size());
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testRemoveOneThroughOne() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance.remove(2);
        assertEquals(3, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(1);
        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(4);
        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(3);
        assertEquals(0, testInstance.size());
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testRemoveRandomElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        Random rnd = new Random();

        while (!testInstance.isEmpty()) {
            int i = rnd.nextInt(9) + 1;
            testInstance.remove(i);
            assertFalse(testInstance.contains(i));
        }
    }

    @Test
    public void testContainsAll() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(3);
        testInstance2.add(4);
        testInstance.addAll(testInstance2);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() {
        final ArrayCollection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);


        testInstance2.add(3);
        testInstance2.add(2);
        testInstance2.add(4);

        testInstance.removeAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(2));
    }

    @Test
    public void testRetainAll3() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(2, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertFalse(testInstance.contains(4));
        assertFalse(testInstance.contains(5));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testRetainAll2() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
        assertTrue(testInstance.contains(5));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testRetainAll1() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testClear() {
        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }


    @Test
    public void testToArrayWhenInputArrayIsNull() {
        final Collection<Object> testInstance = new ArrayCollection<>();
        testInstance.add("a");
        testInstance.add("b");
        testInstance.add("c");

        final String[] input = null;
        try {
            testInstance.toArray(input);
            fail("The toArray method does not throw an exception when the incoming array is null.");
        } catch (final NullPointerException e) {}
    }

    @Test
    public void testToArrayWhenInputArrayHaveOtherType() {
        final Collection<String> testInstance = new ArrayCollection<>();
        testInstance.add("a");
        testInstance.add("b");
        testInstance.add("c");

        final Integer[] input = new Integer[3];
        try {
            testInstance.toArray(input);
            fail("The toArray method does not throw an exception when the incoming array is of a different type.");
        } catch (final ArrayStoreException e) {}
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveIdenticalSize() {
        Random r = new Random();

        final Collection<Integer> testInstance = new ArrayCollection<>();
        final Integer[] controlArray = {r.nextInt(99)
                , r.nextInt(99)
                , r.nextInt(99)
                , r.nextInt(99)
                , r.nextInt(99)};

        testInstance.addAll(Arrays.asList(controlArray));


        final Integer[] input = new Integer[testInstance.size()];

        final Integer[] result = testInstance.toArray(input);
        assertArrayEquals(input, result);
        assertArrayEquals(input, controlArray);
        assertArrayEquals(controlArray, result);
    }

    @Test
    public void testToArrayWhenInputArrayBiggest() {
        final Collection<Integer> testInstance = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[4];

        final Integer[] result = testInstance.toArray(input);
        assertArrayEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertNull(result[3]);
        assertEquals(4, result.length);
    }

}