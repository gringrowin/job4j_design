package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstLine = in.readLine();

                    Pattern pattern = Pattern.compile("msg=[^&]*");
                    Matcher matcher = pattern.matcher(firstLine);
                    String message = "";
                    while (matcher.find()) {
                        message = firstLine.substring(matcher.start(), matcher.end());
                        message = message.replaceFirst("msg=", "");
                        message = message.replaceFirst(" HTTP/1.1", "");
                    }

                    switch (message) {
                        case "Hello":
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write("Hello, dear friend.".getBytes());
                            break;
                        case "Exit":
                            server.close();
                            break;
                        default:
                            out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                            out.write(message.getBytes());
                            break;
                    }

                    for (String str = firstLine; str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}
