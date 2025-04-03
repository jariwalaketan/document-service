package com.example.grpc;

import com.example.service.DocumentService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.security.Authenticated;
import io.quarkus.security.runtime.SecurityIdentityAssociation;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.UUID;

@GrpcService
@Singleton
@Authenticated
public class DocumentProcessorService implements com.example.grpc.DocumentService {

    private static final Logger LOG = Logger.getLogger(DocumentProcessorService.class);

    @Inject
    DocumentService documentService;

    @Inject
    JsonWebToken jwt;

    @Inject
    SecurityIdentityAssociation securityIdentityAssociation;

    @Override
    @Blocking
    public Uni<DocumentResponse> getDocument(DocumentRequest request) {
        try {
            // Verify authentication
            if (securityIdentityAssociation.getIdentity() == null) {
                LOG.error("Authentication failed: No security identity found");
                return Uni.createFrom().item(DocumentResponse.newBuilder()
                        .setStatus("Error")
                        .setMessage("Authentication required")
                        .build());
            }
//            // Check if user has required role
            if (!securityIdentityAssociation.getIdentity().hasRole("viewer")) {
                LOG.errorf("Access denied: User role required. Available roles: %s",
                    securityIdentityAssociation.getIdentity().getRoles());
                return Uni.createFrom().item(DocumentResponse.newBuilder()
                        .setStatus("Error")
                        .setMessage("Access denied: User role required")
                        .build());
            }
            
            // Get tenant ID from JWT token
            String tokenTenantId = jwt.getClaim("tenant_id");
            if (tokenTenantId == null) {
                LOG.errorf("Tenant ID not found in token. Available claims: %s", jwt.getClaimNames());
                return Uni.createFrom().item(DocumentResponse.newBuilder()
                        .setStatus("Error")
                        .setMessage("Tenant ID not found in token")
                        .build());
            }

            UUID documentId = UUID.fromString(request.getDocumentId());

            try {
                var document = documentService.getDocument(documentId, tokenTenantId);

                if (document == null) {
                    throw new Exception("Document is not found");
                }
                return Uni.createFrom().item(DocumentResponse.newBuilder()
                        .setStatus("Success")
                        .setMessage("Document retrieved successfully")
                        .build());
            } catch (Exception e) {
                LOG.errorf("Error retrieving document: %s", e.getMessage());
                return Uni.createFrom().item(DocumentResponse.newBuilder()
                        .setStatus("Error")
                        .setMessage(e.getMessage())
                        .build());
            }
        } catch (IllegalArgumentException e) {
            LOG.errorf("Invalid document ID format: %s", e.getMessage());
            return Uni.createFrom().item(DocumentResponse.newBuilder()
                    .setStatus("Error")
                    .setMessage("Invalid document ID format")
                    .build());
        } catch (Exception e) {
            LOG.errorf("Authentication error: %s", e.getMessage(), e);
            return Uni.createFrom().item(DocumentResponse.newBuilder()
                    .setStatus("Error")
                    .setMessage("Authentication error: " + e.getMessage())
                    .build());
        }
    }
} 
