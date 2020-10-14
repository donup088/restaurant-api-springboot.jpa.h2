package com.dong.restaurant.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtUtilTest {

    @Test
    public void createToken() {
        String secret="12345678901234567890123456789012";
        JwtUtil jwtUtil = new JwtUtil(secret);

        String token = jwtUtil.createToken(1L, "dong");

        assertEquals(token,"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJkb25nIn0.nFJeevg5yaVow0gINVZY-CJ7Zw6PHNhUqXHMzWsFX6c");
    }

}