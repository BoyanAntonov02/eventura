package com.eventura.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class EventProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // Event name, like "Conference Program 2024"

    @ElementCollection
    private List<String> days;  // Stores the agenda for each day (simplified for now)

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
}
