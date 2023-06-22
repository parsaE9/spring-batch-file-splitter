package com.shaparak.batch.service;

import com.shaparak.batch.BatchApplication;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UnzipService {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    @Value("${log.directory.path}")
    private String logDirectoryPath;

    @Value("${input.zip.file.directory.path}")
    private String inputZipFileDirectoryPath;

    @Value("${unzipped.input.file.destination.path}")
    private String unzippedInputFileDestination;

    @Value("${unzip.input.file}")
    private boolean unzipInputFile;

    public static String fileDate;


    public void unzip() throws Exception {
        System.out.println("started unzipping input zip file");

        long begin = System.currentTimeMillis();
        File dir = new File(inputZipFileDirectoryPath + "/");
//        FileFilter fileFilter = new WildcardFileFilter("Batch_Ach_Cycle_*_*.zip");
        FileFilter fileFilter = new WildcardFileFilter("*.zip");
        File[] files = dir.listFiles(fileFilter);
        if (files == null || files.length == 0)
            throw new Exception("no input zip file found!");
        else if (files.length > 1)
            throw new Exception("more than 1 zip file found!");


        String fileZip = files[0].getAbsolutePath();
        fileDate = files[0].getName().substring(19, 27);
        BatchApplication.jobDetailsMap.put("batchFileDate", fileDate);
        BatchApplication.jobDetailsMap.put("batchFileCycle", files[0].getName().substring(16, 18));
        BatchApplication.jobDetailsMap.put("inputZipFile", files[0].getName());
        File destDir = new File(unzippedInputFileDestination);

        byte[] buffer = new byte[10240];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();


        long end = System.currentTimeMillis();
        long time = TimeUnit.MILLISECONDS.toSeconds(end - begin);
        System.out.println("unzipping task completion time: " + TimeService.calculateDuration(end - begin));
        System.out.println("finished unzipping input zip file\n\n\n");
    }


    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }


    public void clearFolders() throws IOException {
        System.out.println("\ncleared folders\n\n\n");
        FileUtils.deleteDirectory(new File(outputDirectoryPath));

        if (unzipInputFile)
            FileUtils.deleteDirectory(new File(unzippedInputFileDestination));
    }


}
