package com.konoha.model.dao;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

	private String email;

	private String password;

	private Set<String> roles = new HashSet<>();

	public User(String email) {
		this.email = email;
	}
	public User(String email, String password) {
		this(email);
		this.password = password;
	}

	public User(String email, String password, Set<String> roles) {
		this(email, password);
		this.roles = roles;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email);
	}

}
