package com.kerem.userman.dao;

import java.util.List;

import com.kerem.userman.model.Role;

public interface RoleDao {
	boolean add(Role entity);
	public Role findByName(String roleName);
	public Role findById(int id);
	public List<Role> findAll();
	public boolean update(Role entity);
	public boolean deleteById(int id);
}
