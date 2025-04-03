package com.example.model;

import java.util.UUID;

public class DocumentDTO {
    private UUID id;
    private String title;
    private String content;
    private String tenantId;

    // Default constructor
    public DocumentDTO() {}

    // Constructor from Document entity
    public DocumentDTO(Document document) {
        this.id = document.getId();
        this.title = document.getTitle();
        this.content = document.getContent();
        this.tenantId = document.getTenantId();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
} 