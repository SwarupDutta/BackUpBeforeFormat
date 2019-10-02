package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Document;
import com.example.demo.repositories.DocumentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by JavaDeveloperZone.
 * 
 * https://javadeveloperzone.com/spring-boot/spring-boot-elastic-search-example/
 */
@RestController
public class ESDocumentController {
    @Autowired
    private DocumentRepository documentRepository;
    @RequestMapping("/")
    public String SpringBootESExample() {
        return "Welcome to Spring Boot Elastic Search Example";
    }
    @DeleteMapping("/delete")
    public String deleteAllDocuments() {
        try {   //delete all documents from solr core
            documentRepository.deleteAll();
            return "documents deleted succesfully!";
        }catch (Exception e){
        return "Failed to delete documents";
        }
    }
    @GetMapping("/save")
    public String saveAllDocuments() {
        //Store Documents
    	
    	//documentRepository.save(new Document("1", "msg", "subject:reinvetion"));
       documentRepository.saveAll(Arrays.asList(
                new Document("2", "msg", "subject:reinvetion"),
                new Document("3", "pdf", "Spring boot sessions"),
                new Document("4", "docx", "meeting agenda"),
                new Document("5", "docx", "Spring boot + Elastic Search")));
        return "4 document saved!!!";
    }
    @GetMapping("/getAll")
    public List<Document> getAllDocs() {
        List<Document> documents = new ArrayList<>();
        // iterate all documents and add it to list
        for (Document doc : this.documentRepository.findAll()) {
            documents.add(doc);
        }
        return documents;
    }
}