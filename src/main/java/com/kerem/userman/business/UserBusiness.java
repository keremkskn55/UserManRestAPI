package com.kerem.userman.business;

import javax.ws.rs.core.Response;

import com.kerem.userman.model.User;


public interface UserBusiness {

	public Response findUserById(int id);
	public Response createUser(User user);
	public Response getAllUser();
	public Response updateUser (User user);
    public Response deleteUserById (int id);

}
