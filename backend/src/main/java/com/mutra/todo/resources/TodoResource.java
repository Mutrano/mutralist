package com.mutra.todo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import com.mutra.todo.dto.TodoDTO;
import com.mutra.todo.services.TodoService;

@RestController
@RequestMapping(value="/todos")
public class TodoResource {
	@Autowired
	private TodoService service;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping()
	public ResponseEntity<List<TodoDTO>> findAll(){
		List<Todo> list = service.findAll();
		List<TodoDTO> listDto = list.stream().map(x -> service.toDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value ="/{id}")
	public ResponseEntity<TodoDTO> findById(@PathVariable Long id){
		Todo obj = service.findById(id);
		return ResponseEntity.ok().body(service.toDTO(obj));
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping
	public ResponseEntity<TodoDTO> insert(@RequestBody TodoDTO dto){
			System.out.println("Sysout intensifies ");
			Todo todo = service.fromDTO(dto);
			todo = service.insert(todo);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todo.getId()).toUri();
			return ResponseEntity.created(uri).body(service.toDTO(todo));	
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PutMapping(value = "/{id}")
	public ResponseEntity<TodoDTO> update(@PathVariable Long id, @RequestBody TodoDTO obj){
		Todo todo = new Todo(obj.getId(),obj.getText(),obj.getStartDate(), obj.getDueDate(), obj.getUser());
		todo = service.update(id, todo);
		return ResponseEntity.ok(service.toDTO(todo));
	}
}
