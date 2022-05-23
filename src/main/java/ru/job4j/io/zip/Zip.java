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

    private static boolean argsValid(ArgsName argsName, String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Need three arguments");
        }
        if (!Files.isDirectory(Paths.get(argsName.get("d")))) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Incorrect source file extension");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Incorrect target file extension");
        }
        return true;
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (argsValid(argsName, args)) {
            Zip zip = new Zip();
            List<Path> sources = Search.search(Paths.get(argsName.get("d")), p -> !p.toFile()
                    .getName()
                    .endsWith(argsName.get("e")));
            Path target = Paths.get(argsName.get("o"));
            zip.packFiles(sources, target);
        }
    }
}
