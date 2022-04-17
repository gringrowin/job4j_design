package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4, 6));
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 4, 5);
        assertThat(input, is(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        ListUtils.addAfter(input, 3, 4);
        ListUtils.addAfter(input, 4, 5);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3, 4, 5)));
    }

    @Test
    public void whenRemoveIfPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        ListUtils.removeIf(input, s -> s % 2 != 0);
        assertThat(input, is(Arrays.asList(0, 2, 4, 6, 8)));
    }

    @Test
    public void whenReplaceIfPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        ListUtils.replaceIf(input, s -> s % 2 != 0, 12);
        assertThat(input, is(Arrays.asList(0, 12, 2, 12, 4)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 5, 6));
        ListUtils.removeAll(input, new ArrayList<>(Arrays.asList(0, 2, 4, 5)));
        assertThat(input, is(Arrays.asList(1, 3, 6)));
    }
}
