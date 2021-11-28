package com.konoha.controllers.service;

import java.util.Random;

import com.konoha.controllers.service.EmailService;
import com.konoha.controllers.service.UserService;
import com.konoha.model.dao.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

	private EmailService emailService;
	private PasswordEncoder passwordEncoder;
	private UserService userService;

	public AuthService(UserService userService,
					   PasswordEncoder passwordEncoder,
					   EmailService emailService) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
	}

	public void sendNewPass(String email)  {
		User user = userService.getUser(email);
		if(user == null) {
			throw new RuntimeException("Email not found.");
		}
		String newPass = generatePassword();
		String encodePass = passwordEncoder.encode(newPass);
		user.setPassword(encodePass);
		userService.updateUser(user);
		emailService.sendNewPassword(user, newPass);
	}
	private String generatePassword() {
		return new String(generatePassword(12));
	}

	/**
	 * https://www.tutorialspoint.com/Generating-password-in-Java
	 * @param length
	 * @return
	 */
	private char[] generatePassword(int length) {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "!@#$";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[length];

		password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
		password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < length; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		return password;
	}
}
