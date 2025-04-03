// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: document.proto
package com.example.grpc;

public interface DocumentResponseOrBuilder extends // @@protoc_insertion_point(interface_extends:document.DocumentResponse)
com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * The status of the operation
     * Possible values:
     * - "Success": Operation completed successfully
     * - "Error": Operation failed
     * </pre>
     *
     * <code>string status = 1;</code>
     * @return The status.
     */
    java.lang.String getStatus();

    /**
     * <pre>
     * The status of the operation
     * Possible values:
     * - "Success": Operation completed successfully
     * - "Error": Operation failed
     * </pre>
     *
     * <code>string status = 1;</code>
     * @return The bytes for status.
     */
    com.google.protobuf.ByteString getStatusBytes();

    /**
     * <pre>
     * A descriptive message about the operation result
     * </pre>
     *
     * <code>string message = 2;</code>
     * @return The message.
     */
    java.lang.String getMessage();

    /**
     * <pre>
     * A descriptive message about the operation result
     * </pre>
     *
     * <code>string message = 2;</code>
     * @return The bytes for message.
     */
    com.google.protobuf.ByteString getMessageBytes();
}
