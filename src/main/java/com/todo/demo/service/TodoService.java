package com.todo.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.todo.demo.entity.Todo;
import com.todo.demo.repository.TodoReository;

@Service
public class TodoService {

	
	private TodoReository todoRepository;
	
	@Autowired
	public TodoService ( TodoReository todoRepository) {
		
		this.todoRepository = todoRepository;
				
	}
	
	public Page<Todo> getTodo(int pageNumber , int size){
		
		return todoRepository.findAll(PageRequest.of(pageNumber, size));
		
	}
	
	public Todo save (Todo todo) {
		
		return todoRepository.save(todo);
	}
	

	
    public Optional<Todo> getToDoById(Long id) {
        return todoRepository.findById(id);
    }
    
    public void delete(Long id) {
    	todoRepository.deleteById(id);
    }
    
    
    
}
