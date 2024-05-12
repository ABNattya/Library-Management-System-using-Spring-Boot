package com.example.LibrarySystem.service;

import com.example.LibrarySystem.model.Book;
import com.example.LibrarySystem.model.BorrowingRecord;
import com.example.LibrarySystem.model.Patron;
import com.example.LibrarySystem.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service

public class BorrowingService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    public BorrowingRecord borrowBook(Book book, Patron patron) {
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());
        return borrowingRecordRepository.save(borrowingRecord);
    }

    public BorrowingRecord returnBook(Book book, Patron patron) {
        Optional<BorrowingRecord> borrowingRecordOptional = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
        if (borrowingRecordOptional.isPresent()) {
            BorrowingRecord borrowingRecord = borrowingRecordOptional.get();
            borrowingRecord.setReturnDate(LocalDate.now());
            return borrowingRecordRepository.save(borrowingRecord);
        } else {
            // Throw exception or handle appropriately
            return null;
        }
    }
}
