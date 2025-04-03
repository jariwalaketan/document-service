package com.example.grpc;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: document.proto")
public interface DocumentService extends MutinyService {

    /**
     * <pre>
     *  Get document details
     * </pre>
     */
    io.smallrye.mutiny.Uni<com.example.grpc.DocumentResponse> getDocument(com.example.grpc.DocumentRequest request);
}
