package com.api.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.todo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
}
