package com.example.LibrarySystem.Controller;

import com.example.LibrarySystem.model.Book;
import com.example.LibrarySystem.model.BorrowingRecord;
import com.example.LibrarySystem.model.Patron;
import com.example.LibrarySystem.service.BookService;
import com.example.LibrarySystem.service.BorrowingService;
import com.example.LibrarySystem.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingRecordService;

    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Book book = bookService.getBookById(bookId).orElse(null);
        Patron patron = patronService.getPatronById(patronId).orElse(null);

        if (book == null || patron == null) {
            return new ResponseEntity<>("Book or patron not found", HttpStatus.NOT_FOUND);
        }

        BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(book, patron);
        if (borrowingRecord != null) {
            return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to borrow book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Book book = bookService.getBookById(bookId).orElse(null);
        Patron patron = patronService.getPatronById(patronId).orElse(null);

        if (book == null || patron == null) {
            return new ResponseEntity<>("Book or patron not found", HttpStatus.NOT_FOUND);
        }

        BorrowingRecord borrowingRecord = borrowingRecordService.returnBook(book, patron);
        if (borrowingRecord != null) {
            return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to return book", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
