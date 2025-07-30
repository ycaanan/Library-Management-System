package com.example.library_management_system.service;

import com.example.library_management_system.dto.LoanResponse;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {

    LoanResponse takeBook(Long memberId, Long bookId );
    LoanResponse returnBook(Long memberId, Long bookId);
    List<LoanResponse> getOverdueLoans();
    List<LoanResponse> getActiveLoans();
    void deleteAllLoans();
    List<LoanResponse> getActiveLoansByMember(Long memberId);
    void notifyLateLoans();
}
