package com.example.grpc;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: document.proto")
public class DocumentServiceClient implements DocumentService, MutinyClient<MutinyDocumentServiceGrpc.MutinyDocumentServiceStub> {

    private final MutinyDocumentServiceGrpc.MutinyDocumentServiceStub stub;

    public DocumentServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyDocumentServiceGrpc.MutinyDocumentServiceStub, MutinyDocumentServiceGrpc.MutinyDocumentServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyDocumentServiceGrpc.newMutinyStub(channel));
    }

    private DocumentServiceClient(MutinyDocumentServiceGrpc.MutinyDocumentServiceStub stub) {
        this.stub = stub;
    }

    public DocumentServiceClient newInstanceWithStub(MutinyDocumentServiceGrpc.MutinyDocumentServiceStub stub) {
        return new DocumentServiceClient(stub);
    }

    @Override
    public MutinyDocumentServiceGrpc.MutinyDocumentServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<com.example.grpc.DocumentResponse> getDocument(com.example.grpc.DocumentRequest request) {
        return stub.getDocument(request);
    }
}
