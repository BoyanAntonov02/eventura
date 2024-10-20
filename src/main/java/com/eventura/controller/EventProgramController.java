package com.eventura.controller;

import com.eventura.entity.EventProgram;
import com.eventura.service.EventProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-programs")
public class EventProgramController {

    @Autowired
    private EventProgramService eventProgramService;

    @GetMapping
    public List<EventProgram> getAllEventPrograms() {
        return eventProgramService.getAllPrograms();
    }

    @PostMapping
    public EventProgram createEventProgram(@RequestBody EventProgram eventProgram) {
        return eventProgramService.saveEventProgram(eventProgram);
    }
}
