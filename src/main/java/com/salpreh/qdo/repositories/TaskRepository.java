package com.salpreh.qdo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.salpreh.qdo.entities.Task;
import com.salpreh.qdo.repositories.custom.TaskRepositoryCustom;

public interface TaskRepository extends ElasticsearchRepository<Task, String>, TaskRepositoryCustom {
	public Page<Task> findByName(String name, Pageable p);
	
	public Page<Task> findByDescription(String description, Pageable p);
	
	public Page<Task> findByNameOrDescription(String name, String description, Pageable p);
}
