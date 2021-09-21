package com.todo.demo.controller;

 import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.demo.entity.Todo;
import com.todo.demo.service.TodoService;

@Controller
public class ToDoController {
	
	
	/*
	 * public String index() {
	 * 
	 * return "redirect:todolist"; }
	 */
    static final int DEFAULT_PAGE_SIZE = 2;

	private TodoService todoService;
	
	@Autowired
	public  ToDoController(TodoService todoService) {
			this.todoService = todoService;
	}
	
	/*
	 * @GetMapping("/todo") public String index(ModelMap model) {
	 * model.addAttribute("sssss", "sample");
	 * 
	 * 
	 * return new ModelAndView("todo/todolist", model); }
	 */
	
	@GetMapping("/todo/todolist")
	public String getTodo( Model model, @RequestParam(value = "page", defaultValue = "0") final int pageNumber,
            @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE + "") final int pageSize) {
		
        Page<Todo> page = todoService.getTodo(pageNumber, pageSize) ;

        final int currentPageNumber = page.getNumber();
        final int previousPageNumber = page.hasPrevious() ? currentPageNumber - 1 : -1;
        final int nextPageNumber = page.hasNext() ? currentPageNumber + 1 : -1;
        
        System.out.println(page.getContent().toString());

        model.addAttribute("todos", page.getContent());
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
        model.addAttribute("currentPageNumber", currentPageNumber);
        model.addAttribute("pageSize", pageSize);
        
		return "todo/todolist";
	}
	
	@GetMapping("/todo/add")
    public String add(ModelMap model) {
        model.addAttribute("todo", new Todo());
		return "todo/add";
    }
	
	@GetMapping("/todo/view")
    public String view( Model model, @RequestParam long id) {
		
        final Optional<Todo> record = todoService.getToDoById(id);

        model.addAttribute("todo", record.isPresent() ? record.get() : new Todo());
        model.addAttribute("id", id);
        
        
		return "todo/view";
    }
	@GetMapping("/todo/edit")
    public String  edit( Model model, @RequestParam long id) {
        final Optional<Todo> record = todoService.getToDoById(id);

        model.addAttribute("todo", record.isPresent() ? record.get() : new Todo());
        model.addAttribute("id", id);
        
        
		return "todo/edit";
    }

	@GetMapping("/todo/delete")
    public String  delete( Model model, @RequestParam long id) {
        final Optional<Todo> record = todoService.getToDoById(id);

        model.addAttribute("todo", record.isPresent() ? record.get() : new Todo());
        model.addAttribute("id", id);
        
		return "todo/delete";
    }

	
	@PostMapping("/todo/save")
	 public String save( Model model, @ModelAttribute  Todo todo,  BindingResult errors) {

		
		System.out.println(todo.getTodoDescription());
		System.out.println(todo.getTodoName());

		System.out.println(todo.toString());
		
		LocalDateTime now = LocalDateTime.now();

		todo.setTodoDate(now);
		
		todoService.save(todo);

        return "redirect:todolist";
    }
	
    @PostMapping("/todo/delete")
    public String deleteById( Model model,  @ModelAttribute  Todo todo,  BindingResult errors) {

    	todoService.delete(todo.getId());

        return "redirect:todolist";
    }

	


}
