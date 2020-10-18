package com.dong.restaurant.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtUtilTest {
    private static final String SECRET = "12345678901234567890123456789012";
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    public void createToken() {
        String token = jwtUtil.createToken(1L, "dong", null);

        assertEquals(token, "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJkb25nIn0.nFJeevg5yaVow0gINVZY-CJ7Zw6PHNhUqXHMzWsFX6c");
    }

    @Test
    public void getClaims() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJkb25nIn0.nFJeevg5yaVow0gINVZY-CJ7Zw6PHNhUqXHMzWsFX6c";
        Claims claims = jwtUtil.getClaims(token);

        assertEquals(claims.get("name"),"dong");
    }
}