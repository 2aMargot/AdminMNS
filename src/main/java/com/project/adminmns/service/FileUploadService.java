package com.project.adminmns.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileUploadService {

    @Value("${dossier.upload}")
    private String dossierUpload;

    public ResponseEntity<byte[]> uploadToLocalFileSystem(InputStream inputStream, String originalFileName) throws IOException {
        Path storageDirectory = Paths.get(dossierUpload);

        if (!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                throw new IOException("Could not create storage directory", e);
            }
        }
        Set<String> allowedExtensions = new HashSet<>();
        allowedExtensions.add(".jpg");
        allowedExtensions.add(".jpeg");
        allowedExtensions.add(".png");
        allowedExtensions.add(".pdf");

        boolean validExtension = false;
        for (String extension : allowedExtensions) {
            if (originalFileName.endsWith(extension)) {
                validExtension = true;
                break;
            }
        }
        if (!validExtension) {
            throw new IOException("File type not allowed");
        }


        if (originalFileName.contains("..") || originalFileName.contains("/") || originalFileName.contains("\\") || originalFileName.contains(";")) {
            throw new IOException("Invalid file name");
        }

        String fileExtension = "";
        int i = originalFileName.lastIndexOf('.');
        if (i > 0) {
            fileExtension = originalFileName.substring(i);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String fileName = "Absence_" + formattedDateTime + fileExtension;

        int maxFileSize = 5 * 1024 * 1024;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        int totalBytesRead = 0;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            totalBytesRead += bytesRead;
            if (totalBytesRead > maxFileSize) {
                return new ResponseEntity<>(HttpStatus.PAYLOAD_TOO_LARGE);
            }
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }

        byte[] fileData = byteArrayOutputStream.toByteArray();

        Path destination = storageDirectory.resolve(fileName);
        try {
            Files.write(destination, fileData);
        } catch (Exception e) {
            throw new IOException("Failed to upload file", e);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public byte[] getFileFromUploadFolder(String fileName) throws IOException {
        Path fichierPath = Paths.get(dossierUpload, fileName);
        if (Files.exists(fichierPath)) {
            return Files.readAllBytes(fichierPath);
        } else {
            throw new IOException("Le fichier n'existe pas : " + fileName);
        }
    }
}

