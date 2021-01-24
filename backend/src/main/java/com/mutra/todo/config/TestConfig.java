package com.mutra.todo.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mutra.todo.domain.Todo;
import com.mutra.todo.domain.User;
import com.mutra.todo.repositories.TodoRepository;
import com.mutra.todo.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withZone(ZoneId.of("GMT"));
		
		User user1 = new User(null, "Mário", "mario@gmail.com", "1234", LocalDate.now());
		userRepository.saveAll(Arrays.asList(user1));
		
		Todo t1= new Todo(null, "Fazer o front-end", LocalDate.now(), LocalDate.now().plusDays(2),user1);
		Todo t2= new Todo(null, "Fazer o Deploy", LocalDate.now(), LocalDate.now().plusDays(4),user1);
		
		user1.getTodos().addAll(Arrays.asList(t1,t2));
		
		todoRepository.saveAll(Arrays.asList(t1,t2));
		
	}
}
