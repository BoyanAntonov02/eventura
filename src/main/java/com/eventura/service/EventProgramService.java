package com.eventura.service;

import com.eventura.entity.EventProgram;
import com.eventura.entity.QrCode;
import com.eventura.repository.EventProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventProgramService {

    @Autowired
    private EventProgramRepository eventProgramRepository;

    @Autowired
    private QrCodeService qrCodeService;

    public List<EventProgram> getAllPrograms() {
        return eventProgramRepository.findAll();
    }

    public EventProgram saveEventProgram(EventProgram eventProgram) {
        EventProgram savedEvent = eventProgramRepository.save(eventProgram);

        // Generate QR code for this event
        try {
            String qrData = "Event: " + savedEvent.getName() + " Date: " + savedEvent.getCreatedDate();
            String qrCodePath = qrCodeService.generateQRCode(qrData);

            // Optionally, you can save the QR code information in the event or separately
            QrCode qrCode = new QrCode();
            qrCode.setEventProgram(savedEvent);
            qrCode.setQrCodeUrl(qrCodePath);
            qrCodeService.saveQrCode(qrCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedEvent;
    }

    public EventProgram getEventProgramById(Long id) {
        Optional<EventProgram> eventProgram = eventProgramRepository.findById(id);
        return eventProgram.orElseThrow(() -> new RuntimeException("EventProgram not found with id " + id));
    }
}
