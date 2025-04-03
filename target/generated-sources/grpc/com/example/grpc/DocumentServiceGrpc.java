package com.example.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Document service for processing and managing documents
 * </pre>
 */
@io.quarkus.Generated(value = "by gRPC proto compiler (version 1.59.0)", comments = "Source: document.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DocumentServiceGrpc {

    private DocumentServiceGrpc() {
    }

    public static final java.lang.String SERVICE_NAME = "document.DocumentService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<com.example.grpc.DocumentRequest, com.example.grpc.DocumentResponse> getGetDocumentMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetDocument", requestType = com.example.grpc.DocumentRequest.class, responseType = com.example.grpc.DocumentResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.example.grpc.DocumentRequest, com.example.grpc.DocumentResponse> getGetDocumentMethod() {
        io.grpc.MethodDescriptor<com.example.grpc.DocumentRequest, com.example.grpc.DocumentResponse> getGetDocumentMethod;
        if ((getGetDocumentMethod = DocumentServiceGrpc.getGetDocumentMethod) == null) {
            synchronized (DocumentServiceGrpc.class) {
                if ((getGetDocumentMethod = DocumentServiceGrpc.getGetDocumentMethod) == null) {
                    DocumentServiceGrpc.getGetDocumentMethod = getGetDocumentMethod = io.grpc.MethodDescriptor.<com.example.grpc.DocumentRequest, com.example.grpc.DocumentResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDocument")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.grpc.DocumentRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.example.grpc.DocumentResponse.getDefaultInstance())).setSchemaDescriptor(new DocumentServiceMethodDescriptorSupplier("GetDocument")).build();
                }
            }
        }
        return getGetDocumentMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static DocumentServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<DocumentServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<DocumentServiceStub>() {

            @java.lang.Override
            public DocumentServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new DocumentServiceStub(channel, callOptions);
            }
        };
        return DocumentServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static DocumentServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<DocumentServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<DocumentServiceBlockingStub>() {

            @java.lang.Override
            public DocumentServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new DocumentServiceBlockingStub(channel, callOptions);
            }
        };
        return DocumentServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static DocumentServiceFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<DocumentServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<DocumentServiceFutureStub>() {

            @java.lang.Override
            public DocumentServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new DocumentServiceFutureStub(channel, callOptions);
            }
        };
        return DocumentServiceFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * Document service for processing and managing documents
     * </pre>
     */
    public interface AsyncService {

        /**
         * <pre>
         * Get document details
         * </pre>
         */
        default void getDocument(com.example.grpc.DocumentRequest request, io.grpc.stub.StreamObserver<com.example.grpc.DocumentResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDocumentMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service DocumentService.
     * <pre>
     * Document service for processing and managing documents
     * </pre>
     */
    public static abstract class DocumentServiceImplBase implements io.grpc.BindableService, AsyncService {

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return DocumentServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service DocumentService.
     * <pre>
     * Document service for processing and managing documents
     * </pre>
     */
    public static class DocumentServiceStub extends io.grpc.stub.AbstractAsyncStub<DocumentServiceStub> {

        private DocumentServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected DocumentServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new DocumentServiceStub(channel, callOptions);
        }

        /**
         * <pre>
         * Get document details
         * </pre>
         */
        public void getDocument(com.example.grpc.DocumentRequest request, io.grpc.stub.StreamObserver<com.example.grpc.DocumentResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetDocumentMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service DocumentService.
     * <pre>
     * Document service for processing and managing documents
     * </pre>
     */
    public static class DocumentServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DocumentServiceBlockingStub> {

        private DocumentServiceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected DocumentServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new DocumentServiceBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * Get document details
         * </pre>
         */
        public com.example.grpc.DocumentResponse getDocument(com.example.grpc.DocumentRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetDocumentMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service DocumentService.
     * <pre>
     * Document service for processing and managing documents
     * </pre>
     */
    public static class DocumentServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DocumentServiceFutureStub> {

        private DocumentServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected DocumentServiceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new DocumentServiceFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * Get document details
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.DocumentResponse> getDocument(com.example.grpc.DocumentRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetDocumentMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_GET_DOCUMENT = 0;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final AsyncService serviceImpl;

        private final int methodId;

        MethodHandlers(AsyncService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_GET_DOCUMENT:
                    serviceImpl.getDocument((com.example.grpc.DocumentRequest) request, (io.grpc.stub.StreamObserver<com.example.grpc.DocumentResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    public static io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getGetDocumentMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<com.example.grpc.DocumentRequest, com.example.grpc.DocumentResponse>(service, METHODID_GET_DOCUMENT))).build();
    }

    private static abstract class DocumentServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

        DocumentServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return com.example.grpc.DocumentProto.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("DocumentService");
        }
    }

    private static final class DocumentServiceFileDescriptorSupplier extends DocumentServiceBaseDescriptorSupplier {

        DocumentServiceFileDescriptorSupplier() {
        }
    }

    private static final class DocumentServiceMethodDescriptorSupplier extends DocumentServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

        private final java.lang.String methodName;

        DocumentServiceMethodDescriptorSupplier(java.lang.String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (DocumentServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new DocumentServiceFileDescriptorSupplier()).addMethod(getGetDocumentMethod()).build();
                }
            }
        }
        return result;
    }
}
