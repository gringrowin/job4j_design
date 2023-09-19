package ru.job4j.io;

import ru.job4j.io.zip.ArgsName;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class CSVReader {

   public static void handle(ArgsName argsName) throws Exception {
       argsValid(argsName);
       String delimiter = argsName.get("delimiter");
       StringBuilder outLines = new StringBuilder();
       String[] filterColumns = argsName.get("filter").split(",");
       try (Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(argsName.get("path"))))) {
           if (scanner.hasNext()) {
               List<String> columns = List.of(scanner.nextLine().split(delimiter));
               if (!columns.containsAll(List.of(filterColumns))) {
                   throw new IndexOutOfBoundsException("The file does not contain all the required filters");
               }
               outLines.append(String.join(delimiter, filterColumns)).append(System.lineSeparator());
               while (scanner.hasNext()) {
                   List<String> filteredLine = new ArrayList<>();
                   String[] line = scanner.nextLine().split(delimiter);
                   for (String header : filterColumns) {
                       int index = columns.indexOf(header);
                       filteredLine.add(line[index]);
                   }
                   outLines.append(String.join(delimiter, filteredLine)).append(System.lineSeparator());
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       if ("stdout".equals(argsName.get("out"))) {
           System.out.println(outLines);
       } else {
           Files.writeString(Path.of(argsName.get("out")), outLines.toString());
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
}
