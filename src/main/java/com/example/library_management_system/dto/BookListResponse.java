package com.example.library_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.example.library_management_system.model.Book;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookListResponse {
    private List<Book> books;
    private String message;
    private int status;
}
