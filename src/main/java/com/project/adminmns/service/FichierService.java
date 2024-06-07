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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Service
public class FichierService {

    @Value("${dossier.upload}")
    private String dossierUpload;

    public ResponseEntity<byte[]> uploadToLocalFileSystem(InputStream inputStream, String fileName) throws IOException {
        Path storageDirectory = Paths.get(dossierUpload);

        if (!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                throw new IOException("Could not create storage directory", e);
            }
        }

        if (fileName.contains("..") || fileName.contains("/") || fileName.contains("\\")) {
            throw new IOException("Invalid file name");
        }

        Set<String> allowedExtensions = new HashSet<>();
        allowedExtensions.add(".jpg");
        allowedExtensions.add(".jpeg");
        allowedExtensions.add(".png");
        allowedExtensions.add(".pdf");

        boolean validExtension = false;
        for (String extension : allowedExtensions) {
            if (fileName.endsWith(extension)) {
                validExtension = true;
                break;
            }
        }
        if (!validExtension) {
            throw new IOException("File type not allowed");
        }

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

        // Save file as blob in the database
        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql = "INSERT INTO file (file_name, file_data) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            Blob blob = connection.createBlob();
            blob.setBytes(1, fileData);

            statement.setString(1, fileName);
            statement.setBlob(2, blob);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new file was inserted successfully!");
            }

            blob.free();
            statement.close();
        } catch (SQLException e) {
            throw new IOException("Failed to save file to database", e);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

