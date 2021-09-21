package com.todo.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findUserByName(String username);

}
