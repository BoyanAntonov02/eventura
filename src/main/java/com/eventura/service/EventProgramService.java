package com.eventura.service;

import com.eventura.entity.EventProgram;
import com.eventura.repository.EventProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventProgramService {

    @Autowired
    private EventProgramRepository eventProgramRepository;

    public List<EventProgram> getAllPrograms() {
        return eventProgramRepository.findAll();
    }

    public EventProgram saveEventProgram(EventProgram eventProgram) {
        return eventProgramRepository.save(eventProgram);
    }
}
