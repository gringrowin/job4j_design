package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                matrix[row][cell] = (row + 1) * (cell + 1);

            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = multiple(9);
        try (FileOutputStream out = new FileOutputStream("./data/result.txt")) {
            for (int[] y : matrix) {
                out.write(System.lineSeparator().getBytes());
                for (int x : y) {
                    out.write(Integer.toString(x).getBytes());
                    out.write(" ".getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
