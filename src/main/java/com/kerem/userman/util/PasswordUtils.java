package com.kerem.userman.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;


public class PasswordUtils {
	
    private static final int SALT_LENGTH = 16; // Length of the salt in bytes

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String plainTextPassword, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            md.update(saltBytes);
            md.update(plainTextPassword.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            return Base64.getEncoder().encodeToString(passwordDigest);
        } catch (Exception e) {
            throw new RuntimeException("Exception hashing password", e);
        }
    }

    public static boolean verifyPassword(String plainTextPassword, String hashedPassword, String salt) {
        String newlyHashedPassword = hashPassword(plainTextPassword, salt);
        return hashedPassword.equals(newlyHashedPassword);
    }
}