package com.api.todo.controller;

import java.util.List;

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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

	TaskService taskService;
	
	@ApiOperation(value = "Criando uma nova task")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Task criada com sucesso"),
			@ApiResponse(code = 500, message = "Houve um erro ao criar a task, verifique os dados"),
	})
	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask (@RequestBody Task task) {
		log.info("Criando nova task com os seguintes dados [{}]", task);
		return taskService.createTask(task);
	}
	
	@ApiOperation(value = "Listando todas as tasks")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Task listadas com sucesso"),
			@ApiResponse(code = 500, message = "Houve um erro ao listar as tasks"),
	})
	@GetMapping("/tasks")
	@ResponseStatus(HttpStatus.OK)
	public List<Task> getAllTasks () {
		log.info("Listando todas as tasks cadastradas.");
		return taskService.listAllTasks();
	}
	
	@ApiOperation(value = "Buscando a task pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Task recuperada com sucesso"),
			@ApiResponse(code = 404, message = "Não foi encontrada uma tarefa com esse id"),
	})
	@GetMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> getById (@PathVariable (value = "id") Long id) {
		log.info("Buscando a task com o id [{}]", id);
		return taskService.getTaskById(id);
	}
	
	@ApiOperation(value = "Atualizando uma task")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Task atualizada com sucesso"),
			@ApiResponse(code = 404, message = "Não foi possível atualizar a task - task não encontrada"),
	})
	@PutMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> updateById (@PathVariable (value = "id") Long id, @RequestBody Task task) {
		log.info("Atualizando a task com o id [{}]. Os novos dados são: [{}]", id, task);
		return taskService.updateTaskById(task, id);
	}
	
	@ApiOperation(value = "Excluindo uma task")
    @ApiResponses( value ={
            @ApiResponse(code = 204, message = "Task excluída com sucesso"),
            @ApiResponse(code = 404, message = "Não foi possivel excluir a task - task não encontrada")

    })
	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> deleteById (@PathVariable (value = "id") Long id) {
		log.info("Excluindo a task com o id [{}]", id);
		return taskService.deleteTaskById(id);
	}
}
