package com.example.rest;

import com.example.model.Document;
import com.example.service.DocumentService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;
import java.util.UUID;

@Path("/documents")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentResource {

    @Inject
    DocumentService documentService;

    @Inject
    JsonWebToken jwt;

    @POST
    @RolesAllowed("admin")
    public Response createDocument(Document document) {
        String tenantId = jwt.getClaim("tenant_id");
        Document created = documentService.createDocument(document, tenantId);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "viewer"})
    public Response getDocument(@PathParam("id") UUID id) {
        String tenantId = jwt.getClaim("tenant_id");
        Document document = documentService.getDocument(id, tenantId);
        if (document == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(document).build();
    }

    @GET
    @RolesAllowed({"admin", "viewer"})
    public Response getAllDocuments() {
        String tenantId = jwt.getClaim("tenant_id");
        List<Document> documents = documentService.getAllDocuments(tenantId);
        return Response.ok(documents).build();
    }
} 