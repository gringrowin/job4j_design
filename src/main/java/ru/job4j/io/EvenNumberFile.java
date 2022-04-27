package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
               text.append((char) read);
            }
            Arrays.stream(text.toString()
                    .split(System.lineSeparator()))
                    .map(Integer::parseInt)
                    .forEach(x -> System.out.println(x + " четное? " + (x % 2 == 0)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
