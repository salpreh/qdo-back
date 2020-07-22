package com.salpreh.qdo.repositories.custom;

import java.util.Map;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import com.salpreh.qdo.entities.Task;

public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {
	private final ElasticsearchOperations elasticsearchOperations;

	@Autowired
	public TaskRepositoryCustomImpl(ElasticsearchOperations elasticsearchOperations) {
		this.elasticsearchOperations = elasticsearchOperations;
	}
	
	@Override
	public Page<Task> findMustMatch(Map<String, String> matchCriteria, Pageable p) {
		
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		for (Map.Entry<String, String> entry : matchCriteria.entrySet()) {
			boolQueryBuilder.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.AND));
		}
		
		return searchQueryPageable(boolQueryBuilder, p);
	}

	@Override
	public Page<Task> findShouldMatch(Map<String, String> matchCriteria, Pageable p) {
		
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		for (Map.Entry<String, String> entry : matchCriteria.entrySet()) {
			boolQueryBuilder.should(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()).operator(Operator.OR));
		}
		
		return searchQueryPageable(boolQueryBuilder, p);
	}
	
	private Page<Task> searchQueryPageable(QueryBuilder queryBuilder, Pageable p) {
		NativeSearchQuery query = new NativeSearchQueryBuilder()
			.withQuery(queryBuilder)
			.withPageable(p)
			.build();
		
		SearchHits<Task> hits = elasticsearchOperations.search(query, Task.class);
		long totalHits = hits.getTotalHits();
		
		return new PageImpl<Task>(
			hits.get()
				.map(sh -> sh.getContent())
				.collect(Collectors.toList()),
			p,
			totalHits
		);
	}

}
