package com.example.repository;

import com.example.model.Document;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class DocumentRepository implements PanacheRepository<Document> {
    
    public List<Document> findByTenantId(String tenantId) {
        return find("tenantId", tenantId).list();
    }
    
    public Document findByIdAndTenantId(UUID id, String tenantId) {
        return find("id = ?1 and tenantId = ?2", id, tenantId).firstResult();
    }
} 