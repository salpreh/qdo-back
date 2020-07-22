package com.salpreh.qdo.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName="notes")
@Data
public class Note {
	
	@Id
	private String id;
	
	private String name;
	
	@Field(type=FieldType.Text)
	private String description;
	
	@Field(type=FieldType.Keyword)
	private String taskId;
}
