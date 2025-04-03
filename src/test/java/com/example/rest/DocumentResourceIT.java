package com.example.rest;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
class DocumentResourceIT {

    private String adminToken;
    private String viewerToken;
    private String tenantId;

    @BeforeEach
    void setUp() {
        tenantId = "tenant-1";
        
        // Create mock tokens - these are just strings that will be validated by the test configuration
        adminToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoZF9tTXBfMXFzeTNJLVZWTkZENGtsaEtyNXYwN3lMTk1zUXNvY0tjcWU4In0.eyJleHAiOjE3NDM2ODI4ODIsImlhdCI6MTc0MzY3OTI4MiwianRpIjoiNTkwNDUzYjUtNzY2MC00ZTMyLWJmNzctNGRmNzM1MThmZmJmIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL3Rlc3QtcmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMTIyOTY1NGItMjY3NC00ZjlkLWIwMjQtNDVkZWY5OTU5MjdmIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZG9jdW1lbnQtc2VydmljZSIsInNlc3Npb25fc3RhdGUiOiI3NDE3YjE1OC1jMzIwLTRkYmMtYjk4Mi1hNjA1ZTZjZmQ2OGEiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy10ZXN0LXJlYWxtIiwib2ZmbGluZV9hY2Nlc3MiLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwic2lkIjoiNzQxN2IxNTgtYzMyMC00ZGJjLWI5ODItYTYwNWU2Y2ZkNjhhIiwidGVuYW50X2lkIjoidGVuYW50LTEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJBZG1pbiBVc2VyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYWRtaW4tdXNlciIsImdpdmVuX25hbWUiOiJBZG1pbiIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoiYWRtaW5AZXhhbXBsZS5jb20ifQ.c10Z3J294kpat9AvbAXY8PmlMwftcyuJKSkcW5AombDpif6Sp97qzuaDNz9LqWbAi3OXoYEJaFAd4L6oeNLBW2AXW_b2j_YXKukkcR2S1n4xQDQGwP6PPAHbWpA_Uum8zV8yVDlPgOvHOsHXpnKXvgmBeBzhT9zcGUlPAGmOpx_oA-F_kZFTfBjqD--vuHlw6Wexkv7CCGKbhJPlgTzfFhfqr8RZTzxKuwoBBe8TiG7KElrzFiWmMQoGmyuzcYgjoOCbQ0OEdhcvQQrXZWXl9XPQTzebnaeiyIxnSguHgn2GBwUy85OmjCyoffVl1dDmpO3j-_tHUeUoLYveEfRXEw";
        viewerToken = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoZF9tTXBfMXFzeTNJLVZWTkZENGtsaEtyNXYwN3lMTk1zUXNvY0tjcWU4In0.eyJleHAiOjE3NDM2ODI5MTgsImlhdCI6MTc0MzY3OTMxOCwianRpIjoiOGQwMjI1YjAtNDcwNS00ZGVmLWEwYzctNTdjNzYyODU4NjBkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL3Rlc3QtcmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiMDU3YThiMmItN2Y0Ni00ODA3LWFiODItN2JlZmI2Mjc1ZDgwIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiZG9jdW1lbnQtc2VydmljZSIsInNlc3Npb25fc3RhdGUiOiJiNmQyNmM3OS1iNTVjLTQyNjQtYmVkOC1hZTc3MTE4OWUxMGYiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MCJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsidmlld2VyIiwiZGVmYXVsdC1yb2xlcy10ZXN0LXJlYWxtIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6ImI2ZDI2Yzc5LWI1NWMtNDI2NC1iZWQ4LWFlNzcxMTg5ZTEwZiIsInRlbmFudF9pZCI6InRlbmFudC0xIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiVmlld2VyIFVzZXIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ2aWV3ZXItdXNlciIsImdpdmVuX25hbWUiOiJWaWV3ZXIiLCJmYW1pbHlfbmFtZSI6IlVzZXIiLCJlbWFpbCI6InZpZXdlckBleGFtcGxlLmNvbSJ9.KGy1-B7Xc_SZ85H07spj2nG6uy6DhyXUwFbKOhbCB_kIok_47N5Mk-d7IE3Ng-pVx93LJMKa7iYqiH0b0tKS45n-My1G9nmtA0NF_ix2sYd3dlVo1F7MLhQD6QdeHrCSY1htY34eAXVb4W3b1MTDFs4LPh70OBNy72DEupchgFgz4boa4LHrEcgbqp5gAZYhsDDjNqxwu2qLYZw4n7DgVJxHa6fDzIq8fBh__mtauAxOmrO5y04ydJZpNPFD_k9bEnbPqkB6bzyC9haRBr9WZZvrv8eAL4jTRqyhwlwD-A0aUfrUwPpdc6cikfbqyjMySMfJGaI3oo_2-hVbruAgig";
    }

    @Test
    void testCreateAndGetDocument() {
        // Create a document
        String documentJson = "{\"title\":\"Test Document\",\"content\":\"Test Content\"}";
        String documentId = given()
            .header("Authorization", "Bearer " + adminToken)
            .contentType(ContentType.JSON)
            .body(documentJson)
        .when()
            .post("/documents")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("title", is("Test Document"))
            .body("content", is("Test Content"))
            .body("tenantId", is(tenantId))
            .extract()
            .path("id");
            
        // Retrieve the document
        given()
            .header("Authorization", "Bearer " + viewerToken)
        .when()
            .get("/documents/" + documentId)
        .then()
            .statusCode(200)
            .body("id", is(documentId))
            .body("title", is("Test Document"))
            .body("content", is("Test Content"))
            .body("tenantId", is(tenantId));
    }
    
    @Test
    void testGetAllDocuments() {
        // Retrieve all documents
        given()
            .header("Authorization", "Bearer " + viewerToken)
        .when()
            .get("/documents")
        .then()
            .statusCode(200)
            .body("$", hasSize(1));
    }
} 