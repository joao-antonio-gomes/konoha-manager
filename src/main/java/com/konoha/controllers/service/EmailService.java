package com.konoha.controllers.service;

import com.konoha.model.dao.User;
import org.springframework.mail.SimpleMailMessage;


public interface EmailService {

	void sendEmail(SimpleMailMessage message);

	void sendNewPassword(User user, String newPassword);

}
