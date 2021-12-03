package br.com.konoha.model.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.konoha.model.transport.UserDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	private static Map<String, UserDTO> db = new HashMap<>();
	
	public UserDAO() {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		UserDTO joao = new UserDTO("joao@email.com", pe.encode("123456")
				,Set.of("ROLE_USER", "ROLE_ADMIN"));
		db.put(joao.getEmail(), joao);
	}
	
	public void updateUser(UserDTO user) {
		db.replace(user.getEmail(), user);
	}
	
	public UserDTO getUser(String email) {
		return db.get(email);
	}
}
