package com.bpm.engine.serviceImplement.jsomxml.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileToString {

    public static String readFileToString(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }

        return content.toString();
    }
}

