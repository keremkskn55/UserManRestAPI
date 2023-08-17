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

import com.kerem.userman.business.RoleBusiness;
import com.kerem.userman.filter.JWTTokenNeeded;
import com.kerem.userman.model.Role;

@Path("/roles")
@JWTTokenNeeded
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleEndpoint {
	@Inject
	@Named("roleBusinessImpl")
	RoleBusiness roleBusinessUnit;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/test")
	public String test() {
		return "Test-roleEndpoint";
	}
	
    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") int id) {
        return roleBusinessUnit.findRoleById(id);
    }
    
    @POST
    public Response createUser(Role role) {
        return roleBusinessUnit.createRole(role);
    }
    
    @GET
    public Response getAllUser() {
    	return roleBusinessUnit.getAllRoles();
    }
    
    @POST
    @Path("/updateRole")
    public Response updateUser (Role role) {
    	return roleBusinessUnit.updateRole(role);
    }
    
    @GET
    @Path("deleteRole/{id}")
    public Response deleteUserById (@PathParam("id") int id) {
    	return roleBusinessUnit.deleteRoleById(id);
    }
}
