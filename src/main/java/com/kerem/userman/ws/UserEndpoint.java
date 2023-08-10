package com.kerem.userman.ws;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kerem.userman.business.UserBusiness;
import com.kerem.userman.model.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEndpoint {
	
	@Inject
	@Named("userBusinessImpl")
	UserBusiness userBusinessUnit;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/test")
	public String test() {
		return "Test-userEndpoint";
	}
	
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
        return userBusinessUnit.findUserById(id);
    }
    
    @POST
    public Response createUser(User user) {
        return userBusinessUnit.createUser(user);
    }
    
    @GET
    public Response getAllUser() {
    	return userBusinessUnit.getAllUser();
    }
    
    @POST
    @Path("/updateUser")
    public Response updateUser (User user) {
    	return userBusinessUnit.updateUser(user);
    }
    
    @GET
    @Path("deleteUser/{id}")
    public Response deleteUserById (@PathParam("id") int id) {
    	return userBusinessUnit.deleteUserById(id);
    }
}
