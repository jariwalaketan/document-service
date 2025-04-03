package com.example.grpc;

import com.example.model.Document;
import com.example.service.DocumentService;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.SecurityIdentityAssociation;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.Duration;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DocumentProcessorServiceTest {

    @Mock
    private DocumentService documentService;

    @Mock
    private JsonWebToken jwt;

    @Mock
    private SecurityIdentityAssociation securityIdentityAssociation;

    @Mock
    private SecurityIdentity securityIdentity;

    @InjectMocks
    private DocumentProcessorService documentProcessorService;

    private UUID testDocumentId;
    private Document testDocument;

    @BeforeEach
    void setUp() {
        testDocumentId = UUID.randomUUID();
        testDocument = new Document();
        testDocument.setId(testDocumentId);
        testDocument.setTitle("Test Document");
        testDocument.setContent("Test Content");
        testDocument.setTenantId("tenant-1");
        
        // Setup security identity mocks
        when(securityIdentityAssociation.getIdentity()).thenReturn(securityIdentity);
        when(securityIdentity.hasRole("user")).thenReturn(true);
        when(securityIdentity.getRoles()).thenReturn(Collections.singleton("user"));
        
        // Setup JWT token mocks
        when(jwt.getClaim("tenant_id")).thenReturn("tenant-1");
    }

    @Test
    void getDocument_Success() {
        DocumentRequest request = DocumentRequest.newBuilder()
                .setDocumentId(testDocumentId.toString())
                .setTenantId("tenant-1")
                .build();

        when(documentService.getDocument(testDocumentId, "tenant-1"))
                .thenReturn(testDocument);

        var response = documentProcessorService.getDocument(request)
                .await().indefinitely();

        assertNotNull(response);
        assertEquals("Success", response.getStatus());
        assertEquals("Document retrieved successfully", response.getMessage());
        verify(documentService).getDocument(testDocumentId, "tenant-1");
    }

    @Test
    void getDocument_NotFound() {
        DocumentRequest request = DocumentRequest.newBuilder()
                .setDocumentId(testDocumentId.toString())
                .setTenantId("tenant-1")
                .build();

        when(documentService.getDocument(testDocumentId, "tenant-1"))
                .thenReturn(null);

        var response = documentProcessorService.getDocument(request)
                .await().indefinitely();

        assertNotNull(response);
        assertEquals("Error", response.getStatus());
        assertEquals("Document is not found", response.getMessage());
        verify(documentService).getDocument(testDocumentId, "tenant-1");
    }
} 