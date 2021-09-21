package com.todo.demo.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.todo.demo.entity.Todo;
import com.todo.demo.repository.TodoReository;

@ExtendWith(SpringExtension.class)
class TodoServiceTest {
	
    final Long id = 1L;

	@MockBean
	private TodoReository todoRepo;
	
	private TodoService todoServiceTest;
	
	@BeforeEach
	public void setUp() {
		
		todoServiceTest = new TodoService(todoRepo);
	}
	
	
    @Test
    public void getStudents_ReturnsStudents_WhenStudentsExists() {

        final int pageNumber = (int) (Math.random() * 100);
        final int pageSize = (int) (Math.random() * 100);

        final int totalRecords = (int) (Math.random() * 100);
        
		Todo expected_result = new Todo(id,UUID.randomUUID().toString(),UUID.randomUUID().toString(),LocalDateTime.now());
		Todo expected_result1 = new Todo(id,UUID.randomUUID().toString(),UUID.randomUUID().toString(),LocalDateTime.now());



        java.util.List<Todo> todos = Arrays.asList(expected_result, expected_result1);

        final PageRequest page = PageRequest.of(pageNumber, pageSize);

        final Page<Todo> expected = new PageImpl<>(todos, page, totalRecords);
        
        given(todoRepo.findAll(page)).willReturn(expected);

        final Page<Todo> actual = todoServiceTest.getTodo(pageNumber, pageSize);

        assertThat(actual).isEqualTo(expected);
        assertThat(actual.getContent()).hasSameElementsAs(todos);
        assertThat(actual.getPageable()).isEqualTo(page);

        then(todoRepo).should().findAll(page);
        then(todoRepo).shouldHaveNoMoreInteractions();
    }


}
