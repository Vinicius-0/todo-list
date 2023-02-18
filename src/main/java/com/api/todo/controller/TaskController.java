package com.api.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.todo.model.Task;
import com.api.todo.service.TaskService;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask (@RequestBody Task task) {
		return taskService.createTask(task);
	}
	
	@GetMapping("/tasks")
	@ResponseStatus(HttpStatus.OK)
	public List<Task> getAllTasks () {
		return taskService.listAllTasks();
	}
	
	
	@GetMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> getById (@PathVariable (value = "id") Long id) {
		return taskService.getTaskById(id);
	}
	
	@PutMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> updateById (@PathVariable (value = "id") Long id, @RequestBody Task task) {
		return taskService.updateTaskById(task, id);
	}
	
	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> deleteById (@PathVariable (value = "id") Long id) {
		return taskService.deleteTaskById(id);
	}
}
