package com.example.grpc;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: document.proto")
public class DocumentServiceBean extends MutinyDocumentServiceGrpc.DocumentServiceImplBase implements BindableService, MutinyBean {

    private final DocumentService delegate;

    DocumentServiceBean(@GrpcService DocumentService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<com.example.grpc.DocumentResponse> getDocument(com.example.grpc.DocumentRequest request) {
        try {
            return delegate.getDocument(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
