package com.example.LibrarySystem.repository;

import com.example.LibrarySystem.model.Book;
import com.example.LibrarySystem.model.BorrowingRecord;
import com.example.LibrarySystem.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

}
