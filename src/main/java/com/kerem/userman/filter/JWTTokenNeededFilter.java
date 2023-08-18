package com.kerem.userman.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.crypto.SecretKey;
import javax.ws.rs.Priorities;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.kerem.userman.util.KeyGeneratorUtils;

import java.io.IOException;
import java.security.Key;

@JWTTokenNeeded
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {



	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();

		try {

			// Validate the token
			SecretKey key = KeyGeneratorUtils.generateKey();

			Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
			System.out.println(claims.getSubject());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}
}