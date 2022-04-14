package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void createNote(Note note) {
        this.noteMapper.addNote(note);
    }

    public void updateNote(Note note) {
        noteMapper.updateNote(note);
    }

    public List<Note> displayNotes(Integer userId) {
        return noteMapper.displayNotes(userId);
    }

    public void deleteNote(Integer noteId, Integer userId) {
        this.noteMapper.deleteNote(noteId, userId);
    }}
