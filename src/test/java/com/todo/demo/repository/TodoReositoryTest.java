package com.todo.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.todo.demo.entity.Todo;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class TodoReositoryTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private TodoReository todoRepository;
	
	

	@Test
	void save_todo_record_test() {

		Todo expected_result = new Todo();
		expected_result.setTodoName(UUID.randomUUID().toString());
		expected_result.setTodoDescription(UUID.randomUUID().toString());
		
		Todo saved_result = todoRepository.save(expected_result);
		
		Todo actual_result = testEntityManager.find(Todo.class, saved_result.getId());
		
		assertThat(actual_result).isEqualTo(expected_result);

	
	}

}
