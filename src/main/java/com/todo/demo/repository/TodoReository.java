package com.todo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.demo.entity.Todo;


@Repository
public interface TodoReository extends JpaRepository<Todo,Long>{

}
