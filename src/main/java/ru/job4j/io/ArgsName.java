package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Unknown argument");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Error in argument structure");
        }
        Arrays.stream(args)
                .map(s -> {
                    if (!Pattern.matches("^-.+=.+", s)) {
                        throw new IllegalArgumentException("Error in argument structure");
                    }
                    return s.split("=", 2);
                })
                .forEach(s -> values.put(s[0].substring(1), s[1]));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));

        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName jvm1 = ArgsName.of(new String[] {});
        System.out.println(jvm1);

    }
}
