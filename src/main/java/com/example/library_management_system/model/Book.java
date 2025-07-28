package com.example.library_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


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

    // ID'siz constructor
    public Book(String bookName, String bookAuthor, String genre) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.genre = genre;
    }
}
