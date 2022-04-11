package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsCoach() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Coach"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Coach"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Coach"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIs  () {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Coach"));
        store.add(new Role("1", "Mentor"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Coach"));
    }

    @Test
    public void whenReplaceThenRoleIsMentor() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Coach"));
        store.replace("1", new Role("1", "Mentor"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Mentor"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Coach"));
        store.replace("10", new Role("10", "Mentor"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Coach"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Coach"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsCoach() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Coach"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Coach"));
    }
}
