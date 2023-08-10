package com.kerem.userman.business.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.kerem.userman.business.AuthBusiness;
import com.kerem.userman.dao.UserDao;
import com.kerem.userman.dto.SignInCredentailsDto;
import com.kerem.userman.model.User;
import com.kerem.userman.util.KeyGenerator;
import com.kerem.userman.util.PasswordUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Named("authBusinessImpl")
public class AuthBusinessImpl implements AuthBusiness{
	
	@Inject
	@Named("userDaoImpl")
    private UserDao userDao;
	
    @Inject
    private KeyGenerator keyGenerator;
	
    @Context
    private UriInfo uriInfo;
    

	@Override
	public Response register(User user) {
		boolean isAdded = userDao.add(user);
		
		if (!isAdded) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		String token = generateJWTToken(user.getEmail());
		return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
	}

	@Override
	public Response signIn(SignInCredentailsDto signInCredentialsDto) {
		
		if (PasswordUtils.digestPassword())
		
		String token = generateJWTToken(signInCredentialsDto.getEmail());
		return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
	}
	
	private String generateJWTToken(String username) {
        Key key = keyGenerator.generateKey();
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
	
    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
