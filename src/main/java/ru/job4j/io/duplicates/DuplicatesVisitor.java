package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    final Map<FileProperty, List<Path>> allFilesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (allFilesMap.putIfAbsent(fileProperty, new ArrayList<>(List.of(file))) != null) {
            allFilesMap.get(fileProperty).add(file);
        }
        return super.visitFile(file, attrs);
    }

    public Map<FileProperty, List<Path>> getAllFilesMap() {
        return allFilesMap;
    }
}