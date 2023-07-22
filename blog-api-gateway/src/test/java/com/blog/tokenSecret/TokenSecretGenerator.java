package com.blog.tokenSecret;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenSecretGenerator {

    public static void main(String[] args) {
        String tokenSecret = generateTokenSecret();
        System.out.println("Generated Token Secret: " + tokenSecret);
    }

    public static String generateTokenSecret() {
        byte[] randomBytes = new byte[24];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}
