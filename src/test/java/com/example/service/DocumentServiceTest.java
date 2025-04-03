package com.example.service;

import com.example.model.Document;
import com.example.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentService documentService;

    private UUID testDocumentId;
    private Document testDocument;
    private String testTenantId;

    @BeforeEach
    void setUp() {
        testDocumentId = UUID.randomUUID();
        testTenantId = "tenant-1";
        
        testDocument = new Document();
        testDocument.setId(testDocumentId);
        testDocument.setTitle("Test Document");
        testDocument.setContent("Test Content");
        testDocument.setTenantId(testTenantId);
    }

    @Test
    void createDocument_Success() {
        Document newDocument = new Document();
        newDocument.setTitle("New Document");
        newDocument.setContent("New Content");
        
        doAnswer(invocation -> {
            Document doc = invocation.getArgument(0);
            doc.setId(UUID.randomUUID());
            return null;
        }).when(documentRepository).persist(any(Document.class));

        Document result = documentService.createDocument(newDocument, testTenantId);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("New Document", result.getTitle());
        assertEquals("New Content", result.getContent());
        assertEquals(testTenantId, result.getTenantId());
        verify(documentRepository).persist(any(Document.class));
    }

    @Test
    void getDocument_Success() {
        when(documentRepository.findByIdAndTenantId(testDocumentId, testTenantId))
                .thenReturn(testDocument);

        Document result = documentService.getDocument(testDocumentId, testTenantId);

        assertNotNull(result);
        assertEquals(testDocumentId, result.getId());
        assertEquals("Test Document", result.getTitle());
        assertEquals("Test Content", result.getContent());
        assertEquals(testTenantId, result.getTenantId());
        verify(documentRepository).findByIdAndTenantId(testDocumentId, testTenantId);
    }

    @Test
    void getDocument_NotFound() {
        when(documentRepository.findByIdAndTenantId(testDocumentId, testTenantId))
                .thenReturn(null);

        Document result = documentService.getDocument(testDocumentId, testTenantId);

        assertNull(result);
        verify(documentRepository).findByIdAndTenantId(testDocumentId, testTenantId);
    }

    @Test
    void getAllDocuments_Success() {
        Document document1 = new Document();
        document1.setId(UUID.randomUUID());
        document1.setTitle("Document 1");
        document1.setContent("Content 1");
        document1.setTenantId(testTenantId);
        
        Document document2 = new Document();
        document2.setId(UUID.randomUUID());
        document2.setTitle("Document 2");
        document2.setContent("Content 2");
        document2.setTenantId(testTenantId);
        
        List<Document> expectedDocuments = Arrays.asList(document1, document2);
        
        when(documentRepository.findByTenantId(testTenantId))
                .thenReturn(expectedDocuments);

        List<Document> result = documentService.getAllDocuments(testTenantId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Document 1", result.get(0).getTitle());
        assertEquals("Document 2", result.get(1).getTitle());
        verify(documentRepository).findByTenantId(testTenantId);
    }

    @Test
    void getAllDocuments_Empty() {
        when(documentRepository.findByTenantId(testTenantId))
                .thenReturn(Arrays.asList());

        List<Document> result = documentService.getAllDocuments(testTenantId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(documentRepository).findByTenantId(testTenantId);
    }
} 