package com.shaparak.batch.service;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@AllArgsConstructor
public class FileService implements Runnable {

    private String filePath;


    @Override
    public void run() {
        try {

            System.out.println(Thread.currentThread().getId() + ":" + filePath);

            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8));

            int fileSize = fileContent.size();
            for (int i = 1; i < fileSize; i++) {
                fileContent.set(i, String.format("%010d", i) + fileContent.get(i));
            }

            Files.write(Path.of(filePath), fileContent, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println("rowNumber task exception: " + e.getMessage());
        }
    }


}