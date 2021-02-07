package com.example.jpacrud.controller;

import com.example.jpacrud.model.Note;
import com.example.jpacrud.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/note")
public class MyNoteController {

    private final NoteRepository noteRepository;

    @Autowired
    public MyNoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

//    @PostMapping("/save")
//    public ResponseEntity<Void> addNotes() {
//        noteRepository.save(new Note("My Title 1", 121314, "My First Note"));
//        noteRepository.save(new Note("My Title 2", 121314, "My Second Note"));
//        noteRepository.save(new Note("My Title 3", 121314, "My Third Note"));
//        noteRepository.save(new Note("My Title 4", 121314, "My Fourth Note"));
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveNote(@RequestBody Map<String, Object> userMap) {
        String title = (String) userMap.get("title");
        String text = (String) userMap.get("text");
        String createdAt = (String) userMap.get("createdAt");
        noteRepository.save(new Note(title, Long.parseLong(createdAt), text));
        Map<String, String> map = new HashMap<>();
        map.put("message", "Note Added Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/note/{id}")
    ResponseEntity<Note> getNoteById(@PathVariable long id) {
        return new ResponseEntity<>(noteRepository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/notes")
    ResponseEntity<List<Note>> getNotes() {
        List<Note> notes = new ArrayList<>();
        noteRepository.findAll().forEach(notes::add);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteNote(@RequestParam String id) {
        noteRepository.deleteById(Long.parseLong(id));
        Map<String, String> map = new HashMap<>();
        map.put("message", "Note Deleted Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/note")
    public ResponseEntity<List<Note>> getByTitle(@RequestParam String title) {
        return new ResponseEntity<>(noteRepository.findByTitle(title), HttpStatus.OK);

    }

}
