package com.example.library_management_system.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponse {

    private Long loanId;

    private Long memberId;
    private String memberName;
    private String memberSurname;
    private String infoMessage;
    private Long bookId;
    private String bookName;
    private boolean returned;
    private LocalDate loanDate;
    private LocalDate returnDate; //iade tarihi
    private boolean delayed;
    private String delayMessage;

}
