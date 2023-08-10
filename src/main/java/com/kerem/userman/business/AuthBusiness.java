package com.kerem.userman.business;

import javax.ws.rs.core.Response;

import com.kerem.userman.dto.SignInCredentailsDto;
import com.kerem.userman.model.User;

public interface AuthBusiness {
	public Response register(User user);
	public Response signIn(SignInCredentailsDto signInCredentialsDto);
}
