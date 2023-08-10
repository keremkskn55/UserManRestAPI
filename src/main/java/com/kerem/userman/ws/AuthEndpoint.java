package com.kerem.userman.ws;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kerem.userman.business.AuthBusiness;
import com.kerem.userman.dto.SignInCredentailsDto;
import com.kerem.userman.model.User;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthEndpoint {
	
	@Inject
	@Named("authBusinessImpl")
	AuthBusiness authBusinessUnit;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/test")
	public String test() {
		return "Test-authEndpoint";
	}
	
    @POST
    @Path("/signIn")
    public Response signIn(SignInCredentailsDto signInCredentailsDto) {
        return authBusinessUnit.signIn(signInCredentailsDto);
    }
    
    @POST
    @Path("/register")
    public Response register(User user) {
        return authBusinessUnit.register(user);
    }
}
