package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    final Map<FileProperty, Path> allFilesMap = new HashMap<>();
    final Map<Path, FileProperty> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (allFilesMap.containsKey(fileProperty)) {
            duplicates.putIfAbsent(allFilesMap.get(fileProperty), fileProperty);
            duplicates.putIfAbsent(file, fileProperty);
        } else {
            allFilesMap.put(fileProperty, file);
        }
        return super.visitFile(file, attrs);
    }

    public Map<Path, FileProperty> getDuplicates() {
        return duplicates;
    }
}