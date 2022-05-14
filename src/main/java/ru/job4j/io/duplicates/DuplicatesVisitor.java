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

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (allFilesMap.containsKey(fileProperty)) {
            System.out.println(allFilesMap.get(fileProperty).toAbsolutePath());
            System.out.println(file.toAbsolutePath());
        } else {
            allFilesMap.put(fileProperty, file);
        }
        return super.visitFile(file, attrs);
    }
}