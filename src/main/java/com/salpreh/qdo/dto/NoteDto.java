package com.salpreh.qdo.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class NoteDto {
	
	private String name;
	
	@NotEmpty
	private String description;

}
