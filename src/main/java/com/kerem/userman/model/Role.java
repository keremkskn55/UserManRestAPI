package com.kerem.userman.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "users"})
@NamedQueries({
    @NamedQuery(name = Role.FIND_BY_NAME, query = "SELECT u FROM Role u WHERE u.name = :name"),
})
public class Role {
	
	public static final String FIND_BY_NAME = "Role.findByName";

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String name;
	private boolean canUserAdd;
	private boolean canUserEdit;
	private boolean canUserDelete;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade = CascadeType.PERSIST)
    private List<User> users = new ArrayList<>();
	
	public Role() {}

	public Role(String name, boolean canUserAdd, boolean canUserEdit, boolean canUserDelete) {
		super();
		this.name = name;
		this.canUserAdd = canUserAdd;
		this.canUserEdit = canUserEdit;
		this.canUserDelete = canUserDelete;
	}

	public Role(int id, String name, boolean canUserAdd, boolean canUserEdit, boolean canUserDelete) {
		super();
		this.id = id;
		this.name = name;
		this.canUserAdd = canUserAdd;
		this.canUserEdit = canUserEdit;
		this.canUserDelete = canUserDelete;
	}
	
	public void addUser(User user) {
		users.add(user);
		user.setRole(this);
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
}
