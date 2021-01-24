package com.mutra.todo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.mutra.todo.services.TodoService;

@RestController
@RequestMapping(value="/todos")
public class TodoResource {
	@Autowired
	private TodoService service;
	
	@GetMapping()
	public ResponseEntity<List<Todo>> findAll(){
		List<Todo> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Todo> findById(@PathVariable Long id){
		Todo obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Todo> insert(@RequestBody Todo obj){
			System.out.println("Sysout intensifies ");
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).body(obj);	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok(obj);
	}
}
