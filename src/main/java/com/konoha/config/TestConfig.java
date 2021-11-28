package com.konoha.config;

import com.konoha.controllers.service.EmailService;
import com.konoha.controllers.service.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

	@Bean
	public EmailService emailService() {
		return new MockEmailService("Test Sender");
	}
}
