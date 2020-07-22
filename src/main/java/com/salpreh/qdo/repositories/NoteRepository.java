package com.salpreh.qdo.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.salpreh.qdo.entities.Note;

public interface NoteRepository extends ElasticsearchRepository<Note, String> {}
