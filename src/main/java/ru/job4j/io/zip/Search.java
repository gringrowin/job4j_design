package ru.job4j.io.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        if (argsValid(args)) {
            Path start = Paths.get(args[0]);
            search(start, p -> p.toFile().getName()
                    .endsWith(args[1]))
                    .forEach(System.out::println);
        }
    }

    private static boolean argsValid(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Need two arguments");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Incorrect file extension");
        }
        return true;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searcher.getPaths();
    }
}
