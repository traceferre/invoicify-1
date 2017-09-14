package com.theironyard.invoicify.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.UserRepository;

@Configuration
@Profile("development")
public class SeedData {
	
	public SeedData(UserRepository usersRepository, PasswordEncoder encoder) {
		usersRepository.save(new User("curtis", encoder.encode("password"), "TEACHER"));
		usersRepository.save(new User("admin", encoder.encode("admin"), "ADMIN"));
		usersRepository.save(new User("clerk", encoder.encode("clerk"), "CLERK"));
		usersRepository.save(new User("accountant", encoder.encode("accountant"), "ACCOUNTANT"));
	}

}


















