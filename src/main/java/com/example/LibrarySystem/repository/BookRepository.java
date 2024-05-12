package com.example.LibrarySystem.repository;

import com.example.LibrarySystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
