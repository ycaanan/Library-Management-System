package com.example.library_management_system.service;

import com.example.library_management_system.model.Loan;  // Loan modelini import et
import java.util.List;

public interface LoanService {
    Loan loanBook(Long memberId, Long bookId);
    Loan returnBook(Long loanId);
    List<Loan> getAllLoans();
}
