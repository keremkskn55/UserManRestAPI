package com.kerem.userman.business;

import javax.ws.rs.core.Response;

import com.kerem.userman.model.Role;

public interface RoleBusiness {
	public Response findRoleById(int id);
	public Response createRole(Role role);
	public Response getAllRoles();
	public Response updateRole (Role role);
    public Response deleteRoleById (int id);
}
