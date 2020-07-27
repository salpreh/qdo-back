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

import com.salpreh.qdo.dto.NoteDto;
import com.salpreh.qdo.entities.Note;
import com.salpreh.qdo.repositories.NoteRepository;

@RestController
@RequestMapping("note")
public class NoteController {
	
	private final NoteRepository noteRepository;
	private final Mapper dozerMapper;

	public NoteController(NoteRepository notRepository, Mapper dozerMapper) {
		this.noteRepository = notRepository;
		this.dozerMapper = dozerMapper;
	}
	
	@PostMapping
	public Note createNote(@RequestBody @Valid NoteDto note) {
		Note noteE = dozerMapper.map(note, Note.class);
		noteE = noteRepository.save(noteE);
		
		return noteE;
	}
	
	@GetMapping("{id}")
	public Note readNote(@PathVariable String id) {
		Note noteE = noteRepository.findById(id)
			.orElseThrow(() -> {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
			});
		
		return noteE;
	}
	
	@PutMapping("{id}")
	public Note updateNote(@PathVariable String id, @RequestBody @Valid NoteDto note) {
		Note noteE = dozerMapper.map(note, Note.class);
		noteE.setId(id);
		
		noteE = noteRepository.save(noteE);
		
		return noteE;
	}
	
	@DeleteMapping("{id}")
	public Note deleteNote(@PathVariable String id) {
		Note noteE = noteRepository.findById(id)
			.orElseThrow(() -> {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
			});
		
		noteRepository.delete(noteE);
		
		return noteE;
	}
}
