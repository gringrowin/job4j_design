package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        printDuplicates(duplicatesVisitor.getAllFilesMap());
    }

    public static void printDuplicates(Map<FileProperty, List<Path>> files) {
        files.values()
                .stream()
                .filter(value -> value.size() > 1)
                .forEach(value -> value.forEach(System.out::println));
    }
}
