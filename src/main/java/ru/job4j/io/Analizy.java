package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            int countStepByLogLine = 0;
            String line = in.readLine();
            while (line != null) {
                boolean serverNotAvailable = line.startsWith("400") || line.startsWith("500");
                if (serverNotAvailable && countStepByLogLine == 0) {
                    out.print(line.split(" ", 2)[1]);
                    countStepByLogLine++;
                }
                if (!serverNotAvailable && countStepByLogLine > 0) {
                    out.println(";" + line.split(" ", 2)[1]);
                    countStepByLogLine = 0;
                }
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
