package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        printDuplicates(duplicatesVisitor.getDuplicates());
    }

    public static void printDuplicates(Map<Path, FileProperty> duplicates) {
        for (Path file : duplicates.keySet()) {
            System.out.println(file.toAbsolutePath());
            System.out.println(duplicates.get(file));
        }
    }
}
