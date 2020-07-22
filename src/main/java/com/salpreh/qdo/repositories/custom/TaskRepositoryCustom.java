package com.salpreh.qdo.repositories.custom;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.salpreh.qdo.entities.Task;

public interface TaskRepositoryCustom {
	
	/**
	 * Search query that must match fields-values passed in a map
	 * 
	 * @param matchCriteria Fields (key) and values that search must match
	 * @param p
	 * @return Matched results
	 */
	public Page<Task> findMustMatch(Map<String, String> matchCriteria, Pageable p);

	/**
	 * Search query that should match fields-values passed in a map
	 * 
	 * @param matchCriteria Fields (key) and values that search should match
	 * @param p
	 * @return Matched results
	 */
	public Page<Task> findShouldMatch(Map<String, String> matchCriteria, Pageable p);
}
