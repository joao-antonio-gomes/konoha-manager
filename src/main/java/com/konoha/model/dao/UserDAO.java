package com.konoha.model.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class UserDAO {

	private static Map<String, User> db = new HashMap<>();
	
	public UserDAO() {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		User joao = new User("joao@email.com", pe.encode("123456")
				,Set.of("ROLE_USER", "ROLE_ADMIN"));
		db.put(joao.getEmail(), joao);
	}
	
	public void updateUser(User user) {
		db.replace(user.getEmail(), user);
	}
	
	public User getUser(String email) {
		return db.get(email);
	}
}
