package com.kerem.userman.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtils {
	public static String generateJWTToken(String username){
		SecretKey key = KeyGeneratorUtils.generateKey();
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("UserManRest")
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plus(6, ChronoUnit.HOURS)))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
	
    private static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
