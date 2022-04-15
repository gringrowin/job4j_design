package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddFirstInEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.addFirst(1);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenAddFirstInLink() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.addFirst(3);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(3));
    }

    @Test
    public void whenAddFirstAfterDeleteFirstInLink() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.addFirst(2);
        linked.addFirst(3);
        linked.deleteFirst();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }
}