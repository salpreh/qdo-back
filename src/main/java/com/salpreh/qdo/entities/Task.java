package com.salpreh.qdo.entities;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Document(indexName="tasks")
@Data
public class Task {
	@Id
	private String id;
	
	private String name;
	
	@Field(type=FieldType.Text)
	private String description;
	
	@CreatedDate
	@Field(type=FieldType.Date, format=DateFormat.basic_date_time)
	@JsonFormat(shape=Shape.STRING, pattern="yyy-MM-dd'T'HH:mm:ss")
	private Date createdDate;
	
	@Field(type=FieldType.Nested)
	private List<Note> notesId;
}
