package com.example.grpc;

import com.example.model.Document;
import com.example.service.DocumentService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class DocumentProcessorServiceIT {

    @Inject
    DocumentService documentService;

    @GrpcService
    DocumentProcessorService documentProcessorService;

    private UUID testDocumentId;
    private Document testDocument;
    private String tenantId;

    @BeforeEach
    void setUp() {
        tenantId = "tenant-1";
        testDocumentId = UUID.randomUUID();
        
        testDocument = new Document();
        testDocument.setId(testDocumentId);
        testDocument.setTitle("Test Document");
        testDocument.setContent("Test Content");
        testDocument.setTenantId(tenantId);
    }

    @Test
    void testGetDocument_Success() {
        documentService.createDocument(testDocument,tenantId);

        DocumentRequest request = DocumentRequest.newBuilder()
                .setDocumentId(testDocumentId.toString())
                .setTenantId(tenantId)
                .build();

        var response = documentProcessorService.getDocument(request)
                .await().indefinitely();

        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Document retrieved successfully", response.getMessage());
    }

    @Test
    void testGetDocument_NotFound() {
        DocumentRequest request = DocumentRequest.newBuilder()
                .setDocumentId(testDocumentId.toString())
                .setTenantId(tenantId)
                .build();

        var response = documentProcessorService.getDocument(request)
                .await().indefinitely();

        assertNotNull(response);
        assertEquals("Error", response.getStatus());
        assertEquals("Document is not found", response.getMessage());
    }
} 