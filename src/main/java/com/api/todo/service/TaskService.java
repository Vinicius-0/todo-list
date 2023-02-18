package com.api.todo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.todo.model.Task;
import com.api.todo.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {

	private TaskRepository taskRepository;

	public Task createTask(Task task) {
		return taskRepository.save(task);
	}

	public List<Task> listAllTasks() {
		return taskRepository.findAll();
	}

	public ResponseEntity<Task> getTaskById(Long id) {
		return taskRepository.findById(id).map(task -> ResponseEntity.ok().body(task))
				.orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Task> updateTaskById(Task task, Long id) {
		return taskRepository.findById(id).map(taskToUpdate -> {
			taskToUpdate.setTitle(task.getTitle());
			taskToUpdate.setDescription(task.getDescription());
			taskToUpdate.setDeadLine(task.getDeadLine());
			Task updated = taskRepository.save(taskToUpdate);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Object> deleteTaskById(Long id) {
		return taskRepository.findById(id).map(taskToDelete -> {
			taskRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
