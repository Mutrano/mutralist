package com.mutra.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutra.todo.domain.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long>{

}
