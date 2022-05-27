package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers));
             PrintWriter out = new PrintWriter(new FileOutputStream(path))) {
            Scanner scanner = new Scanner(System.in);
            boolean chatIsOn = true;
            while (chatIsOn) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> readPhrases() {
        return null;
    }

    private void saveLog(List<String> log) {

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatlog.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
