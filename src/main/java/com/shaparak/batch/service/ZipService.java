package com.shaparak.batch.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@AllArgsConstructor
public class ZipService implements Runnable {

    private String filePath;


    @Override
    public void run() {
        try {
            Stream<String> fileStream = Files.lines(Paths.get(filePath));

            FileOutputStream fos = new FileOutputStream(filePath.replace("txt", "zip"));
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(filePath);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[10240];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }

            zipOut.close();
            fis.close();
            fos.close();
            fileToZip.delete();

        } catch (Exception e) {
            System.out.println("zip exception: " + e.getMessage());
        }
    }


}