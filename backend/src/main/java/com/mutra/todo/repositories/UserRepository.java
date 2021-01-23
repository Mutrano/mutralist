package com.mutra.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutra.todo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
