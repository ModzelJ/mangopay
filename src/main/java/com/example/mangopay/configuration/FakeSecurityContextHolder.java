package com.example.mangopay.configuration;

import org.springframework.stereotype.Component;

import java.util.UUID;


/***
 * This class is used to simulate the security context.
 * In real application, it should be replaced with Spring Security.
 * ID of the user should be taken from token provided in request.
 */
@Component
public class FakeSecurityContextHolder {

    private UUID userId = UUID.fromString("00000000-0000-0000-0000-000000000000");

    public UUID getUserId() {
        return userId;
    }
}
