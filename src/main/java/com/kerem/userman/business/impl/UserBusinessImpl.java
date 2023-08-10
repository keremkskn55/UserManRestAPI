package com.kerem.userman.business.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import com.kerem.userman.business.UserBusiness;
import com.kerem.userman.dao.UserDao;
import com.kerem.userman.model.User;



@Named("userBusinessImpl")
public class UserBusinessImpl implements UserBusiness {
	
	@Inject
	@Named("userDaoImpl")
    private UserDao userDao;
    
	
	public Response findUserById(int id) {
		User user = userDao.findById(id);
		if (user != null) {
			return Response.ok(user).build();
		}
		else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	public Response createUser(User user) {
		boolean isSuccesed = userDao.add(user);
		if (isSuccesed) {
			return Response.status(Response.Status.CREATED).build();
		}
		else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	public Response getAllUser() {
    	List<User> users = userDao.findAll();
        if (users != null) {
            return Response.ok(users).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
	
	public Response updateUser (User user) {
    	boolean isSuccesed = userDao.update(user);
    	if (isSuccesed) {
    		return Response.ok().build();
    	}
    	return Response.status(Response.Status.BAD_REQUEST).build();
    }
	
    public Response deleteUserById (int id) {
    	boolean isSuccesed = userDao.deleteById(id);
    	if (isSuccesed) {
    		return Response.ok().build();
    	}
    	return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
