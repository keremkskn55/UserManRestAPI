package com.kerem.userman.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class KeyGeneratorUtils{
    
    private static final String SECRET_KEY_STRING = "XPR1BIf7J8TjHEECOjaH14nSqXQtm/W0j1WpZRy2KTZ9+WQX7Lz1NE2ECJhN1vEaRVli21+45d7FqDdCzAkNZw==";
    private static final byte[] SECRET_KEY_BYTES = Base64.getDecoder().decode(SECRET_KEY_STRING);

    public static SecretKey generateKey() {
        return new SecretKeySpec(SECRET_KEY_BYTES, "HmacSHA512");
    }
}