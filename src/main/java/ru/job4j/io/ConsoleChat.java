package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private boolean botIsOn = true;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean chatIsOn = true;
        List<String> botPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        while (chatIsOn) {
            String line = scanner.nextLine();
            log.add(line);
            chatIsOn = !OUT.equalsIgnoreCase(line);
            if (botIsOnCheck(line) && chatIsOn) {
                String phrase = getRandomPhrase(botPhrases);
                System.out.println(phrase);
                log.add(phrase);
            }
        }
        saveLog(log);
    }

    private boolean botIsOnCheck(String line) {
        if (CONTINUE.equalsIgnoreCase(line)) {
            botIsOn = true;
        }
        if (STOP.equalsIgnoreCase(line)) {
            botIsOn = false;
        }
        return botIsOn;
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            while (in.ready()) {
               phrases.add(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private String getRandomPhrase(List<String> phrases) {
        return phrases.get((int) (Math.random() * phrases.size()));
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String line : log) {
                out.println(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatlog.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
