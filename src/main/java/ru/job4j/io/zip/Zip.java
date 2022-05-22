package ru.job4j.io.zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip =
                     new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean argsValid(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Need three arguments");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Incorrect source file extension");
        }
        if (!args[2].endsWith(".zip")) {
            throw new IllegalArgumentException("Incorrect target file extension");
        }
        return true;
    }

    public static void main(String[] args) {
        if (argsValid(args)) {
            ArgsName name = ArgsName.of(args);
            Zip zip = new Zip();
            List<Path> sources = Search.search(Paths.get(name.get("d")), p -> !p.toFile()
                    .getName()
                    .endsWith(name.get("e")));
            Path target = Paths.get(name.get("o"));
            zip.packFiles(sources, target);
        }
    }
}
