package com.project.adminmns.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FichierService {

    @Value("${dossier.upload}")
    private String dossierUpload;

    public void uploadToLocalFileSystem(MultipartFile multipartFile, String fileName) throws IOException {
        uploadToLocalFileSystem(multipartFile.getInputStream(), fileName);
    }

    public void uploadToLocalFileSystem(InputStream inputStream, String fileName) throws IOException {
        Path storageDirectory = Paths.get(dossierUpload);
        if(!Files.exists(storageDirectory)){
            try {
                Files.createDirectories(storageDirectory);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Path destination = Paths.get(storageDirectory.toString() + "/" + fileName);
        Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    // Méthode pour valider le nom du fichier
    public boolean nomFichierValide(String nomDeFichier) {

        Path fichierPath = Paths.get(dossierUpload, nomDeFichier);

        return Files.exists(fichierPath);
    }

    // Méthode pour obtenir un fichier depuis le dossier d'upload
    public byte[] getFileFromUploadFolder(String nomDeFichier) throws IOException {
        Path fichierPath = Paths.get(dossierUpload, nomDeFichier);
        if (Files.exists(fichierPath)) {
            return Files.readAllBytes(fichierPath);
        } else {
            throw new IOException("Le fichier n'existe pas : " + nomDeFichier);
        }
    }
}

