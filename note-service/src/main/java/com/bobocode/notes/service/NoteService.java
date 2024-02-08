package com.bobocode.notes.service;

import com.bobocode.notes.client.PersonServiceClient;
import com.bobocode.notes.client.PersonServiceFeignClient;
import com.bobocode.notes.domain.Note;
import com.bobocode.notes.dto.NoteDto;
import com.bobocode.notes.dto.PersonDto;
import com.bobocode.notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    //private final PersonServiceClient personServiceClient;
    private final PersonServiceFeignClient personServiceFeignClient;

    public List<NoteDto> getAllWithPersons() {
        return noteRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private NoteDto convertToDto(Note n) {
        PersonDto personDto = personServiceFeignClient.getById(n.getPersonId());
        return new NoteDto(n.getId(), n.getBody(), personDto);
    }

}
