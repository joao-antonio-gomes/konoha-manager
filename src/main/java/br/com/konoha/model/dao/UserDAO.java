package br.com.konoha.model.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	private static Map<String, User> db = new HashMap<>();
	
	public UserDAO() {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		User gustavo = new User("rhbarauna@gmail.com", pe.encode("123456")
				,Set.of("ROLE_USER", "ROLE_ADMIN"));
		db.put(gustavo.getEmail(), gustavo);
	}
	
	public void updateUser(User user) {
		db.replace(user.getEmail(), user);
	}
	
	public User getUser(String email) {
		return db.get(email);
	}
}
