package com.example.LibrarySystem.service;

import com.example.LibrarySystem.model.Patron;
import com.example.LibrarySystem.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron updatedPatron) {
        updatedPatron.setId(id);
        return patronRepository.save(updatedPatron);
    }

    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
