package com.mutra.todo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mutra.todo.domain.Todo;
import com.mutra.todo.dto.NewUserDTO;
import com.mutra.todo.dto.UserDTO;
import com.mutra.todo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	@Autowired
	private UserService service;
	
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping()
	public ResponseEntity<List<UserDTO>> findAll(){
		List<UserDTO> listDto = service.findAll();
		return ResponseEntity.ok().body(listDto);
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping(value ="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id){
		UserDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody NewUserDTO obj){
			UserDTO dto = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
			return ResponseEntity.created(uri).body(dto);	
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO obj){
		obj = service.update(id, obj);
		
		return ResponseEntity.ok(obj);
	}
	
	@CrossOrigin(origins = "http://localhost:8081")
	@GetMapping(value="/{id}/todos")
	public ResponseEntity<List<Todo>> findTodos(@PathVariable Long id){
		List<Todo> todos= service.findTodos(id);
		
		return ResponseEntity.ok(todos);
	}
}