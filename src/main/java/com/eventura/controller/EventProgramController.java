package com.eventura.controller;

import com.eventura.entity.EventProgram;
import com.eventura.entity.QrCode;
import com.eventura.service.EventProgramService;
import com.eventura.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-programs")
public class EventProgramController {

    @Autowired
    private EventProgramService eventProgramService;

    @Autowired
    private QrCodeService qrCodeService;

    @GetMapping
    public List<EventProgram> getAllEventPrograms() {
        return eventProgramService.getAllPrograms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventProgram> getEventProgramById(@PathVariable Long id) {
        EventProgram eventProgram = eventProgramService.getEventProgramById(id);
        if (eventProgram != null) {
            return ResponseEntity.ok(eventProgram);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<EventProgram> createEventProgram(@RequestBody EventProgram eventProgram) {
        try {
            EventProgram savedEvent = eventProgramService.saveEventProgram(eventProgram);

            // Create QR Code data
            String qrData = "Event: " + savedEvent.getName() + " Date: " + savedEvent.getCreatedDate();

            // Generate QR Code
            String qrCodePath = qrCodeService.generateQRCode(qrData);

            // You might want to save the QR Code information in the database here
            QrCode qrCode = new QrCode();
            qrCode.setQrCodeUrl(qrCodePath);
            qrCode.setEventProgram(savedEvent);
            qrCodeService.saveQrCode(qrCode);

            // Return the saved event with QR Code information if needed
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
        } catch (Exception e) {
            // Handle any exceptions (e.g., QR Code generation failure)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
