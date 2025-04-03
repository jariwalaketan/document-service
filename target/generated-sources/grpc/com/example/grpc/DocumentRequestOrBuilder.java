// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: document.proto
package com.example.grpc;

public interface DocumentRequestOrBuilder extends // @@protoc_insertion_point(interface_extends:document.DocumentRequest)
com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * The unique identifier of the document
     * </pre>
     *
     * <code>string document_id = 1;</code>
     * @return The documentId.
     */
    java.lang.String getDocumentId();

    /**
     * <pre>
     * The unique identifier of the document
     * </pre>
     *
     * <code>string document_id = 1;</code>
     * @return The bytes for documentId.
     */
    com.google.protobuf.ByteString getDocumentIdBytes();

    /**
     * <pre>
     * The tenant identifier
     * </pre>
     *
     * <code>string tenant_id = 2;</code>
     * @return The tenantId.
     */
    java.lang.String getTenantId();

    /**
     * <pre>
     * The tenant identifier
     * </pre>
     *
     * <code>string tenant_id = 2;</code>
     * @return The bytes for tenantId.
     */
    com.google.protobuf.ByteString getTenantIdBytes();
}
