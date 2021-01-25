package com.mutra.todo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mutra.todo.domain.Todo;
import com.mutra.todo.domain.User;
import com.mutra.todo.dto.NewUserDTO;
import com.mutra.todo.dto.UserDTO;
import com.mutra.todo.repositories.UserRepository;
import com.mutra.todo.services.exceptions.DatabaseException;
import com.mutra.todo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly=true)
	public List<UserDTO> findAll() {
		List<User> list = repository.findAll();
		 return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	@Transactional(readOnly=true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);

		
		if(obj.isPresent()) {
			return new UserDTO(obj.get());
			
		}
		else {
			throw new ResourceNotFoundException(id);
		}
	}
	public UserDTO insert(NewUserDTO dto) {
		dto.setRegistrationDate(LocalDate.now());
		User user = new User(null,dto.getName() ,dto.getEmail(),dto.getUsername(),dto.getPassword(),dto.getRegistrationDate());
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	public UserDTO update(Long id,UserDTO obj) {
		try {
			System.out.println("velho do ceu o numero eh "+id);
			User entity = repository.getOne(id);
			updateData(entity,obj);
			entity= repository.save(entity);
			return toDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, UserDTO obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setUsername(obj.getUsername());
	}
	
	public List<Todo> findTodos(Long id){
		Optional<User> obj = repository.findById(id);
		if(obj.isPresent()) {
			return obj.get().getTodos();
		}
		else {
			throw new ResourceNotFoundException(id);
		}
	}
	public User fromDTO(NewUserDTO obj) {
		User user = new User(obj.getId(),obj.getName(), obj.getEmail(),obj.getUsername(),obj.getPassword(), obj.getRegistrationDate());
		return user;
	}
	public UserDTO toDTO(User obj) {
		UserDTO userDto = new UserDTO(obj);
		return userDto;
	}
}
	