package com.example.library_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoanRequest {

    private Long memberId;
    private Long bookId;
}
