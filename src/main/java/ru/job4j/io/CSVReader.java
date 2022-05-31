package ru.job4j.io;

import ru.job4j.io.zip.ArgsName;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        argsValid(argsName);
        List<List<String>> lineIn = new ArrayList<>();
        List<List<String>> lineOut = new ArrayList<>();
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(argsName.get("path"))));
        while (scanner.hasNext()) {
            lineIn.add(List.of(scanner.nextLine().split(";")));
        }
        if (lineIn.size() != 0 && lineIn.get(0).size() != 0) {
            int numberLine = lineIn.size();
            int numberColumns = lineIn.get(0).size();
            String[] filter = argsName.get("filter").split(",");
            int[] filterIndex = new int[filter.length - 1];
            for (var f : filter) {
                for (String columnTitle : lineIn.get(0)) {
                    if (columnTitle.equals(f)) {
                        filterIndex
                    };
                }
            }

        }

    }

    private static boolean argsValid(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Incorrect source file extension");
        }
        if (!Pattern.matches("[;,]", argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Incorrect source file extension");
        }
        if (!argsName.get("out").endsWith(".csv")
                && !argsName.get("out").equals("stdout")) {
            throw new IllegalArgumentException("Incorrect target path");
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = new File("./data/source.csv");
        File target = new File("./data/target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        System.out.println(Files.readString(target.toPath()));
    }
}
