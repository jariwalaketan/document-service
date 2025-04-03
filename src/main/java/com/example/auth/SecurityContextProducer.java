package com.example.auth;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.security.Principal;

@ApplicationScoped
public class SecurityContextProducer {

    @Inject
    JsonWebToken jwt;

    @Produces
    @RequestScoped
    public SecurityContext produceSecurityContext() {
        return new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return jwt;
            }

            @Override
            public boolean isUserInRole(String role) {
                return jwt.getGroups().contains(role);
            }

            @Override
            public boolean isSecure() {
                return true;
            }

            @Override
            public String getAuthenticationScheme() {
                return "Bearer";
            }
        };
    }
} 