package com.example.library_management_system.service.impl;

import com.example.library_management_system.model.Loan;
import com.example.library_management_system.repository.LoanRepository;
import com.example.library_management_system.service.LoanService;  // Bunu ekle

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan loanBook(Long memberId, Long bookId) {
        // İş mantığını buraya yaz
        return null; // Örnek
    }

    @Override
    public Loan returnBook(Long loanId) {
        // İş mantığını buraya yaz
        return null;
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
