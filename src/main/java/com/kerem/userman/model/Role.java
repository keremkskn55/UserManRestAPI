package com.kerem.userman.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String name;
	private boolean canUserAdd;
	private boolean canUserEdit;
	private boolean canUserDelete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
	
	public Role() {}

	public Role(String name, boolean canUserAdd, boolean canUserEdit, boolean canUserDelete, Tenant tenant) {
		super();
		this.name = name;
		this.canUserAdd = canUserAdd;
		this.canUserEdit = canUserEdit;
		this.canUserDelete = canUserDelete;
		this.tenant = tenant;
	}

	public Role(int id, String name, boolean canUserAdd, boolean canUserEdit, boolean canUserDelete, Tenant tenant) {
		super();
		this.id = id;
		this.name = name;
		this.canUserAdd = canUserAdd;
		this.canUserEdit = canUserEdit;
		this.canUserDelete = canUserDelete;
		this.tenant = tenant;
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

	public boolean isCanUserAdd() {
		return canUserAdd;
	}

	public void setCanUserAdd(boolean canUserAdd) {
		this.canUserAdd = canUserAdd;
	}

	public boolean isCanUserEdit() {
		return canUserEdit;
	}

	public void setCanUserEdit(boolean canUserEdit) {
		this.canUserEdit = canUserEdit;
	}

	public boolean isCanUserDelete() {
		return canUserDelete;
	}

	public void setCanUserDelete(boolean canUserDelete) {
		this.canUserDelete = canUserDelete;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	
	
	
}
