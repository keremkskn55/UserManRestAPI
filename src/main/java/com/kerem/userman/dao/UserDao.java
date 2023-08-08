package com.kerem.userman.dao;

import java.util.List;

import com.kerem.userman.model.User;

public interface UserDao{
	public User findById(int id);
	public boolean add(User entity);
	public List<User> findAll();
	public boolean update(User entity);
	public boolean deleteById(int id);
}
