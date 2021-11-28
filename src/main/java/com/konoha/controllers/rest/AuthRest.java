package com.konoha.controllers.rest;
import com.google.gson.Gson;
import com.konoha.controllers.service.AuthService;
import com.konoha.controllers.service.UserService;
import com.konoha.model.dao.UserSpringSecurity;
import com.konoha.model.transport.JwtDTO;
import com.konoha.model.transport.MailDTO;
import com.konoha.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthRest {
	
	private JWTUtil jwtUtil;
	
	private AuthService authService;
	
	public AuthRest(JWTUtil jwtUtil, AuthService authService) {
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}

	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) throws IOException{
		UserSpringSecurity userSpringSecurity = UserService.authenticated();
		JwtDTO generateToken = jwtUtil.generateToken(userSpringSecurity.getUsername());
		response.addHeader("Authorization", generateToken.getFullToken());
		response.getWriter().append(new Gson().toJson(generateToken));
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@RequestBody MailDTO mail){
		authService.sendNewPass(mail.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	

}
