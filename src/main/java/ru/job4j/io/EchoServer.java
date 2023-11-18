package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

                    if (firstLine.contains("?msg=Exit")) {
                        server.close();
                    } else if (firstLine.contains("?msg=Hello")) {
                        out.write("Hello, dear friend.".getBytes());
                    } else {
                        out.write(firstLine.split(" ")[1].getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}
