package com.kerem.userman.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = User.FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email"),
})
public class User {

	public static final String FIND_BY_EMAIL = "User.findByEmail";
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private int age;
    private String password;
    private String salt;
    
    public User () {}
	
	public User(String email, String name, String surname, int age, String password, String salt) {
		super();
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.password = password;
		this.salt = salt;
	}

	public User(int id, String email, String name, String surname, int age, String password, String salt) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.password = password;
		this.salt = salt;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
    
	
}
