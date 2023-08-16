package com.kerem.userman.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tenants")
public class Tenant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String name;
	
	public Tenant() {}

	public Tenant(String name) {
		super();
		this.name = name;
	}

	public Tenant(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
