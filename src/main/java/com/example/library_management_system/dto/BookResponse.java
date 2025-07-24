package com.example.library_management_system.dto;

import com.example.library_management_system.model.Book;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponse {
    private Book book;
    private String message;
    private int status;
}
