package com.example.library_management_system.controller;

import com.example.library_management_system.model.Loan;
import com.example.library_management_system.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/create")
    public ResponseEntity<Loan> loanBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        Loan loan = loanService.loanBook(memberId, bookId);
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<Loan> returnBook(@PathVariable Long id) {
        Loan loan = loanService.returnBook(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }
}
