package com.shaparak.batch.service;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@AllArgsConstructor
public class ZipService implements Runnable {

    private String filePath;


    @Override
    public void run() {
        try {
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