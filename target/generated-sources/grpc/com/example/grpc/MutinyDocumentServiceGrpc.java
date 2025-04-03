package com.example.grpc;

import static com.example.grpc.DocumentServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: document.proto")
public final class MutinyDocumentServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyDocumentServiceGrpc() {
    }

    public static MutinyDocumentServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyDocumentServiceStub(channel);
    }

    /**
     * <pre>
     *  Document service for processing and managing documents
     * </pre>
     */
    public static class MutinyDocumentServiceStub extends io.grpc.stub.AbstractStub<MutinyDocumentServiceStub> implements io.quarkus.grpc.MutinyStub {

        private DocumentServiceGrpc.DocumentServiceStub delegateStub;

        private MutinyDocumentServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = DocumentServiceGrpc.newStub(channel);
        }

        private MutinyDocumentServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = DocumentServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyDocumentServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyDocumentServiceStub(channel, callOptions);
        }

        /**
         * <pre>
         *  Get document details
         * </pre>
         */
        public io.smallrye.mutiny.Uni<com.example.grpc.DocumentResponse> getDocument(com.example.grpc.DocumentRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getDocument);
        }
    }

    /**
     * <pre>
     *  Document service for processing and managing documents
     * </pre>
     */
    public static abstract class DocumentServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public DocumentServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        /**
         * <pre>
         *  Get document details
         * </pre>
         */
        public io.smallrye.mutiny.Uni<com.example.grpc.DocumentResponse> getDocument(com.example.grpc.DocumentRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(com.example.grpc.DocumentServiceGrpc.getGetDocumentMethod(), asyncUnaryCall(new MethodHandlers<com.example.grpc.DocumentRequest, com.example.grpc.DocumentResponse>(this, METHODID_GET_DOCUMENT, compression))).build();
        }
    }

    private static final int METHODID_GET_DOCUMENT = 0;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final DocumentServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(DocumentServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_GET_DOCUMENT:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.example.grpc.DocumentRequest) request, (io.grpc.stub.StreamObserver<com.example.grpc.DocumentResponse>) responseObserver, compression, serviceImpl::getDocument);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }
}
