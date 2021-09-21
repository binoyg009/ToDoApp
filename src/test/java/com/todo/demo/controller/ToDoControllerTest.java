package com.todo.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.todo.demo.entity.Todo;
import com.todo.demo.service.TodoService;

@ExtendWith(SpringExtension.class)
class ToDoControllerTest {

	private MockMvc mockMVC;
	
	@MockBean
	private TodoService todoService;
	
	private ToDoController todoController; 
	
    @BeforeEach
    public void setUp() {
    	todoController = new ToDoController(todoService);
        this.mockMVC = MockMvcBuilders.standaloneSetup(todoController).build();
    }

	
    @Test
    public void view_ReturnsViewPageWithStudentFromDatabase_WhenStudentIdExistsInDatabase() throws Exception {

        final Long id = 1L;
        LocalDateTime now = LocalDateTime.now();  
        final Todo todo = new Todo(id, id.toString(), id.toString(),now);
        

        

        given(todoService.getToDoById(id)).willReturn(Optional.of(todo));


        mockMVC.perform(
                get("/todo/view")
                    .param("id", id.toString())
            )
           .andExpect(status().isOk())
           .andExpect(model().attribute("id", is(id)))
           .andExpect(model().attribute("todo", is(notNullValue())))
           .andExpect(model().attribute("todo", hasProperty("id", is(id))))
           .andExpect(model().attribute("todo", hasProperty("todoName", is(todo.getTodoName()))))
           .andExpect(model().attribute("todo", hasProperty("todoDescription", is(todo.getTodoDescription()))))
           .andExpect(view().name("todo/view"))
        ;

        then(todoService).should().getToDoById(id);
        then(todoService).shouldHaveNoMoreInteractions();
    }


}
 