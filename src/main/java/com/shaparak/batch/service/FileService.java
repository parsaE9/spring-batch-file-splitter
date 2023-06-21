package com.shaparak.batch.service;

import lombok.AllArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@AllArgsConstructor
public class FileService implements Runnable {

    private String filePath;


    @Override
    public void run() {
        try {
//            System.out.println(Thread.currentThread().getId() + " :setting rowNumber for " + filePath);
            int i = 1;
            String destinationFileName = filePath + "_tmppp";
            FileWriter fw = new FileWriter(destinationFileName, StandardCharsets.UTF_8, true);
            try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
                String header = br.readLine();
                fw.write(header + "\n");
                String line;
                while ((line = br.readLine()) != null) {
                    fw.write(String.format("%09d%s\n", i++, line));
                }
            }
            fw.close();
            new File(filePath).delete();
            new File(destinationFileName).renameTo(new File(filePath.replace("_tmppp", "")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}