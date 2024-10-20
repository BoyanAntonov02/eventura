package com.eventura.repository;

import com.eventura.entity.EventProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventProgramRepository extends JpaRepository<EventProgram, Long> {
}
