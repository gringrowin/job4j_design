package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
           read.lines()
               .filter(s -> !s.isEmpty() && !s.startsWith("#"))
               .map(s -> s.split("=", 2))
               .forEach(s -> {
                   if (s.length == 2) {
                       if (s[0].isEmpty() || s[1].isEmpty()) {
                           throw new IllegalArgumentException("Error in config structure");
                       }
                       values.put(s[0], s[1]);
                   }
               });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./data/app.properties");
        config.load();
        System.out.println(config);

        String path = "./data/pair_with_empty_line.properties";
        Config config1 = new Config(path);
        config1.load();
        System.out.println(config1.values.values());
    }

}