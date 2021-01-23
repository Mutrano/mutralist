package com.mutra.todo.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mutra.todo.domain.User;
import com.mutra.todo.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User(null, "Mário", "mario@gmail.com", "1234", LocalDate.now());
		userRepository.saveAll(Arrays.asList(user1));
	}
}
