package com.example.service;

import com.example.model.Document;
import com.example.repository.DocumentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DocumentService {

    @Inject
    DocumentRepository documentRepository;

    @Transactional
    public Document createDocument(Document document, String tenantId) {
        document.setTenantId(tenantId);
        documentRepository.persist(document);
        return document;
    }

    public Document getDocument(UUID id, String tenantId) {
        return documentRepository.findByIdAndTenantId(id, tenantId);
    }

    public List<Document> getAllDocuments(String tenantId) {
        return documentRepository.findByTenantId(tenantId);
    }
} 