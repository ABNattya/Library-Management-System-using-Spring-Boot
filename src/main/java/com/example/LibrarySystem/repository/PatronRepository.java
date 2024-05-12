package com.example.LibrarySystem.repository;

import com.example.LibrarySystem.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
