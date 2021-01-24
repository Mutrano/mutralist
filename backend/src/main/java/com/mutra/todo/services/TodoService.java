package com.mutra.todo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mutra.todo.domain.Todo;
import com.mutra.todo.repositories.TodoRepository;
import com.mutra.todo.services.exceptions.DatabaseException;
import com.mutra.todo.services.exceptions.ResourceNotFoundException;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	

	public List<Todo> findAll() {
		return repository.findAll();
	}

	public Todo findById(Long id) {
		Optional<Todo> obj = repository.findById(id);
		return obj.orElseThrow( () -> new ResourceNotFoundException(id)  );
	}

	public Todo insert(Todo obj) {
		return repository.save(obj);
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
	public Todo update(Long id,Todo obj) {
		try {
			Todo entity = repository.getOne(id);
			updateData(entity,obj);
			return repository.save(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Todo entity, Todo obj) {
		entity.setText(obj.getText());
		entity.setDueDate(obj.getDueDate());
	}
}
	