package ru.job4j.io;

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
        if (args.length == 2) {
            if (!Files.isDirectory(Path.of(args[0]))) {
                throw new IllegalArgumentException("Incorrect path");
            }
            if (!args[1].startsWith(".")) {
                throw new IllegalArgumentException("Incorrect file extension");
            }
            return true;
        }
        throw new IllegalArgumentException("Need two arguments");
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
       return searcher.getPaths();
    }
}
