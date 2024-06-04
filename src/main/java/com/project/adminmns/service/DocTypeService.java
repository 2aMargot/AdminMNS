package com.project.adminmns.service;

import com.project.adminmns.dao.DocTypeDao;
import com.project.adminmns.model.DocType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocTypeService {

    DocTypeDao docTypeDao;

    public List<DocType> doctypeList() {
        return docTypeDao.findAll();
    }

    public ResponseEntity<DocType> getDocType(int id) {

        Optional<DocType> docTypeOptional = this.docTypeDao.findById(id);

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<DocType> addDocType(DocType newDocType) {

        if (newDocType.getId() != null) {
            Optional<DocType> docTypeOptional = this.docTypeDao.findById(newDocType.getId());


            if (docTypeOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            this.docTypeDao.save(newDocType);
            return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
        }

        docTypeDao.save(newDocType);
        return new ResponseEntity<>(newDocType, HttpStatus.CREATED);
    }

    public ResponseEntity<DocType> updateDocType(DocType docType, int id) {

        Optional<DocType> docTypeOptional = docTypeDao.findById(docType.getId());

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        docType.setId(id);
        this.docTypeDao.save(docType);
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<DocType> deleteDocType(int id) {

        Optional<DocType> docTypeOptional = this.docTypeDao.findById(id);

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        docTypeDao.deleteById(id);
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }
}
