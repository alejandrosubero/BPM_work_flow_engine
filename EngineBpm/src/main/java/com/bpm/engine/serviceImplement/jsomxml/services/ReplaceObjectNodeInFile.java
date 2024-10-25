package com.bpm.engine.serviceImplement.jsomxml.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReplaceObjectNodeInFile {



    public static void replaceObjectNodeInFile(String inputFilePath, String outputFilePath) {
        try (
        		BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
             ) {

            String line;
            
            while ((line = reader.readLine()) != null) {
                String modifiedLine = line.replace("<ObjectNode>", "").replace("</ObjectNode>", "");
                writer.write(modifiedLine);
                writer.newLine();
            }
            
            System.out.println("file procces an save: " + outputFilePath);
  
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	 deleteFile(inputFilePath);
		}
    }
    
    
    public static void deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("file was deleted successful: " + filePath);
            } else {
                System.out.println("Error can't delete the file: " + filePath);
            }
        } else {
            System.out.println("The file don't exist: " + filePath);
        }
    }
    
    
    
}

