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

/**
 * Service class for handling file uploads and retrieval.
 * <p>
 * This service provides methods for uploading files to the local file system and retrieving them.
 * The allowed file types are .jpg, .jpeg, .png, and .pdf. Files are saved with a timestamp-based filename.
 * </p>
 */
@Service
public class FileUploadService {

    @Value("${dossier.upload}")
    private String dossierUpload;

    /**
            * Uploads a file to the local file system.
            *
            * @param inputStream The {@link InputStream} of the file to upload.
     * @param originalFileName The original name of the file.
     * @return A {@link ResponseEntity} with {@link HttpStatus#CREATED} if the upload was successful,
     *         or {@link HttpStatus#PAYLOAD_TOO_LARGE} if the file size exceeds the limit.
            * @throws IOException If an I/O error occurs during file upload or directory creation.
     */
    public ResponseEntity<byte[]> uploadToLocalFileSystem(InputStream inputStream, String originalFileName) throws IOException {
        Path storageDirectory = Paths.get(dossierUpload);

        // Ensure storage directory exists
        if (!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                throw new IOException("Could not create storage directory", e);
            }
        }

        // Check file extension
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

        // Validate file name
        if (originalFileName.contains("..") || originalFileName.contains("/") || originalFileName.contains("\\") || originalFileName.contains(";")) {
            throw new IOException("Invalid file name");
        }

        // Generate new file name with timestamp
        String fileExtension = "";
        int i = originalFileName.lastIndexOf('.');
        if (i > 0) {
            fileExtension = originalFileName.substring(i);
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String fileName = "Absence_" + formattedDateTime + fileExtension;

        // Check file size and write file
        int maxFileSize = 5 * 1024 * 1024; // 5 MB
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

    /**
     * Retrieves a file from the local file system.
     *
     * @param fileName The name of the file to retrieve.
     * @return A byte array containing the file data.
     * @throws IOException If the file does not exist or an I/O error occurs.
     */
    public byte[] getFileFromUploadFolder(String fileName) throws IOException {
        Path fichierPath = Paths.get(dossierUpload, fileName);
        if (Files.exists(fichierPath)) {
            return Files.readAllBytes(fichierPath);
        } else {
            throw new IOException("Le fichier n'existe pas : " + fileName);
        }
    }
}

