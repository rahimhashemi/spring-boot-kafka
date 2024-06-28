package com.example.kafka.log;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LogService {

    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public LogService() {
        try {
            this.fileWriter = new FileWriter("file-log", true);
            this.printWriter = new PrintWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logUserAction(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        printWriter.println(timestamp + " - " + message);
        printWriter.flush();
    }

    public void close() {
        try {
            printWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
