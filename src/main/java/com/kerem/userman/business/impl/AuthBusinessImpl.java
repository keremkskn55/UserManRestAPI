package com.kerem.userman.business.impl;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.kerem.userman.business.AuthBusiness;
import com.kerem.userman.dao.UserDao;
import com.kerem.userman.dto.SignInCredentailsDto;
import com.kerem.userman.model.Role;
import com.kerem.userman.model.Tenant;
import com.kerem.userman.model.User;
import com.kerem.userman.util.JwtTokenUtils;
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
		Tenant tenant = new Tenant("Kamu SM");
		user.setTenant(tenant);
		user.setRole(new Role("Admin", true, true, true, tenant));
		boolean isAdded = userDao.add(user);
		
		if (!isAdded) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		try {
			String token = JwtTokenUtils.generateJWTToken(user.getEmail());
			
			return Response.ok("User registered successfully")
		            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
		            .build();
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
				String token = JwtTokenUtils.generateJWTToken(signInCredentialsDto.getEmail());
				
				return Response.ok("User registered successfully")
			            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
			            .build();
			} catch (Exception e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
}
