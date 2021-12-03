package br.com.konoha.controllers.service;

import br.com.konoha.model.transport.UserDTO;
import br.com.konoha.model.dao.UserSpringSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.konoha.model.dao.UserDAO;

@Service
public class UserService implements UserDetailsService {

	private UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void updateUser(UserDTO user) {
		userDAO.updateUser(user);
	}

	public UserDTO getUser(String email) {
		return userDAO.getUser(email);
	}

	public static UserSpringSecurity authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return new UserSpringSecurity((String) authentication.getPrincipal(), null,
					authentication.getAuthorities());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = getUser(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSpringSecurity(user.getEmail(), user.getPassword(), user.getRoles());
	}
}
