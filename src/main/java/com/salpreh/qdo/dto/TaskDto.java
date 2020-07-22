package com.salpreh.qdo.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class TaskDto {
	
	@NotEmpty
	private String name;
	
	private String description;

}
