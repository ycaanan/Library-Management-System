package com.example.library_management_system.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;
    private String bookAuthor;
    private String genre;

    private Boolean available = true; // Varsayılan true olarak ayarlandı

    // ID'siz constructor
    public Book(String bookName, String bookAuthor, String genre) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.genre = genre;
        this.available = true; // Constructor'da da ayarla
    }
}
