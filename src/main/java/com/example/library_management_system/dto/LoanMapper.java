package com.example.library_management_system.dto;

import com.example.library_management_system.dto.LoanResponse;
import com.example.library_management_system.model.Loan;
import java.time.LocalDate;

public class LoanMapper {

    public static LoanResponse toResponse(Loan loan) {
        LoanResponse response = new LoanResponse();
        response.setLoanId(loan.getId());
        response.setBookName(loan.getBook().getBookName());
        response.setMemberName(loan.getMember().getMemberName());
        response.setLoanDate(loan.getLoanDate());
        response.setReturnDate(loan.getExpectedReturnDate());
        response.setReturned(loan.getReturned());
        if (!loan.isReturned() && loan.getReturnDate().isBefore(LocalDate.now())) {
            response.setInfoMessage("Kitap gecikti");
        } else {
            response.setInfoMessage(null); // veya ""
        }









        return response;
    }

}
