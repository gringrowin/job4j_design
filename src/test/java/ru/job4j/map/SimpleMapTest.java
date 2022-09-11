package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    private Iterator<String> it;

    @Before
    public void setUp() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        it = map.iterator();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnTrueWhenHasNext() {
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void whenPutNotNull() {
        Map<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("key", 1));
        assertTrue(map.put("key2", 1));
        assertFalse(map.put("key", 1));
    }

    @Test
    public void whenGetKeyExist() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("key", 1);
        map.put("key2", 5);
        Assert.assertThat(1, is(map.get("key")));
        Assert.assertThat(5, is(map.get("key2")));
    }

    @Test
    public void whenGetKeyNotExist() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("key", 1);
        Assert.assertThat(null, is(map.get("key2")));
    }

    @Test
    public void whenRemoveKeyNotExist() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("key", 1);
        assertFalse(map.remove("key2"));
    }

    @Test
    public void whenRemoveExistKey() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("key", 1);
        map.put("key2", 5);
        assertTrue(map.remove("key2"));
        assertTrue(map.remove("key"));
    }
}
