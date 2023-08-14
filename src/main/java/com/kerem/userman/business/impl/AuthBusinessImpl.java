package com.kerem.userman.business.impl;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.kerem.userman.business.AuthBusiness;
import com.kerem.userman.dao.UserDao;
import com.kerem.userman.dto.SignInCredentailsDto;
import com.kerem.userman.model.User;
import com.kerem.userman.util.KeyGeneratorUtils;
import com.kerem.userman.util.PasswordUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


@Named("authBusinessImpl")
public class AuthBusinessImpl implements AuthBusiness{
	
	@Inject
	@Named("userDaoImpl")
    private UserDao userDao;

	@Override
	public Response register(User user) {
		String salt = PasswordUtils.generateSalt();
		String hashedPassword = PasswordUtils.hashPassword(user.getPassword(), salt);
		user.setPassword(hashedPassword);
		user.setSalt(salt);
		boolean isAdded = userDao.add(user);
		
		if (!isAdded) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		try {
			String token = generateJWTToken(user.getEmail());
			
	        Cookie cookie = new Cookie("jwt_token", token);
	        NewCookie newCookie = new NewCookie(cookie, "JWT Token", 3600, false);
	        
			return Response.ok("User registered successfully").cookie(newCookie).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response signIn(SignInCredentailsDto signInCredentialsDto) {
		User user = userDao.findByEmail(signInCredentialsDto.getEmail());
		
		if (user == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		if (PasswordUtils.verifyPassword(signInCredentialsDto.getPassword(), user.getPassword(), user.getSalt())) {
			try {
				String token = generateJWTToken(signInCredentialsDto.getEmail());
				
		        Cookie cookie = new Cookie("jwt_token", token);
		        NewCookie newCookie = new NewCookie(cookie, "JWT Token", 3600, false);
		        
				return Response.ok("User registered successfully").cookie(newCookie).build();
			} catch (Exception e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	private String generateJWTToken(String username){
		SecretKey key = KeyGeneratorUtils.generateKey();
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("UserManRest")
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plus(6, ChronoUnit.HOURS)))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
	
    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
