package com.bobocode.notes.controller;

import com.bobocode.notes.dto.NoteDto;
import com.bobocode.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public List<NoteDto> getAll() {
        return noteService.getAllWithPersons();
    }
}
