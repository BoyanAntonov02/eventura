package com.eventura.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QrCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String qrCodeUrl;  // The URL to access the event program

    @OneToOne
    @JoinColumn(name = "event_program_id")
    private EventProgram eventProgram;  // Linking the QR code to the program
}
