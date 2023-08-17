package com.kerem.userman.business.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import com.kerem.userman.business.RoleBusiness;
import com.kerem.userman.dao.RoleDao;
import com.kerem.userman.model.Role;
import com.kerem.userman.model.User;

@Named("roleBusinessImpl")
public class RoleBusinessImpl implements RoleBusiness {
	
	@Inject
	@Named("roleDaoImpl")
    private RoleDao roleDao;

	@Override
	public Response findRoleById(int id) {
		Role role = roleDao.findById(id);
		if (role != null) {
			return Response.ok(role).build();
		}
		else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@Override
	public Response createRole(Role role) {
		boolean isAdded = roleDao.add(role);
		if (isAdded) {
			return Response.status(Response.Status.CREATED).build();
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Override
	public Response getAllRoles() {
		List<Role> roles = roleDao.findAll();
        if (roles != null) {
            return Response.ok(roles).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
	}

	@Override
	public Response updateRole(Role role) {

    	boolean isSuccesed = roleDao.update(role);
    	if (isSuccesed) {
    		return Response.ok().build();
    	}
    	return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Override
	public Response deleteRoleById(int id) {
    	boolean isSuccesed = roleDao.deleteById(id);
    	if (isSuccesed) {
    		return Response.ok().build();
    	}
    	return Response.status(Response.Status.BAD_REQUEST).build();
	}

}
