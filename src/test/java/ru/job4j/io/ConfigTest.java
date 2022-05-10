package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Maxim Pavlishin"));
        assertThat(config.value("comment"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithEmptyLines() {
        String path = "./data/pair_with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("password"), is("123=123"));
        assertThat(config.value("login"), is("max"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWihoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutKeyAndValue() {
        String path = "./data/pair_without_key_and_value.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutSplit() {
        String path = "./data/pair_without_split.properties";
        Config config = new Config(path);
        config.load();
    }
}