package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.Document;
public interface DocumentRepository extends ElasticsearchRepository<Document, String> {
  List<Document> findByDocTitleEndsWith(String name);
  List<Document> findByDocTitleStartsWith(String name);
  List<Document> findByDocTypeEndsWith(String name);
  List<Document> findByDocTypeStartsWith(String name);
}