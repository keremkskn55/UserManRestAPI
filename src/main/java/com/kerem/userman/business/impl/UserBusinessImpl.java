package com.kerem.userman.business.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import com.kerem.userman.business.UserBusiness;
import com.kerem.userman.dao.RoleDao;
import com.kerem.userman.dao.UserDao;
import com.kerem.userman.dto.EmailPayload;
import com.kerem.userman.model.Role;
import com.kerem.userman.model.User;
import com.kerem.userman.util.PasswordUtils;
import com.kerem.userman.util.SendingEmailUtils;



@Named("userBusinessImpl")
public class UserBusinessImpl implements UserBusiness {
	
	@Inject
	@Named("userDaoImpl")
    private UserDao userDao;
	
	@Inject
	@Named("roleDaoImpl")
    private RoleDao roleDao;
    
	
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
		String staticPassword = "aA123456!";
		
//		EmailPayload emailPayload = new EmailPayload();
//		emailPayload.setToEmail(user.getEmail());
//		emailPayload.setTitle("UserMan Password");
//		emailPayload.setContext("You're added to website. Your password: " + staticPassword);
//		
//		boolean isSentEmail = SendingEmailUtils.sendingEmail(emailPayload);
//
//		if (!isSentEmail) {
//			return Response.status(Response.Status.BAD_REQUEST).build();
//		}
		
		String salt = PasswordUtils.generateSalt();
		String hashedPassword = PasswordUtils.hashPassword(staticPassword, salt);
		user.setPassword(hashedPassword);
		user.setSalt(salt);
		
		Role role = roleDao.findByName("Admin");
		
		// Role role = new Role("Admin", true, true, true);
		role.addUser(user);
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
		User selectedUser = userDao.findById(user.getId());
		user.setPassword(selectedUser.getPassword());
		user.setSalt(selectedUser.getSalt());
		
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
