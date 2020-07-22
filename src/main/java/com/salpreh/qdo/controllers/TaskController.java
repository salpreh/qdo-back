package com.salpreh.qdo.controllers;


import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.salpreh.qdo.dto.TaskDto;
import com.salpreh.qdo.entities.Task;
import com.salpreh.qdo.repositories.TaskRepository;

@RestController
@RequestMapping("task")
public class TaskController {
	
	private final TaskRepository taskRepository;
	private final Mapper dozerMapper;
	
	public TaskController(TaskRepository taskRepository, Mapper dozerMapper) {
		this.taskRepository = taskRepository;
		this.dozerMapper = dozerMapper;
	}
	
	@PostMapping("")
	public Task createTask(@RequestBody @Valid TaskDto task) {
		Task eTask = dozerMapper.map(task, Task.class);
		eTask = taskRepository.save(eTask);
		
		return eTask;
	}
	
	@GetMapping("{id}")
	public Task readTask(@PathVariable String id) {
		Task task = taskRepository.findById(id)
			.orElseThrow(() -> {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
			});
		
		return task;
	}
	
	@PutMapping("{id}")
	public Task updateTask(@PathVariable String id, @RequestBody @Valid TaskDto task) {
		Task eTask = dozerMapper.map(task, Task.class);
		eTask.setId(id);
		
		return taskRepository.save(eTask);
	}
	
	@DeleteMapping("{id}")
	public Task deleteTask(@PathVariable String id) {
		Task eTask = taskRepository.findById(id)
			.orElseThrow(() -> {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
			});
		taskRepository.deleteById(id);
		
		return eTask;
	}
}
