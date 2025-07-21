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
    private Long book_id;

    private String book_name;
    private String book_author;
    private String genre;

    // ID'siz constructor
    public Book(String book_name, String book_author, String genre) {
        this.book_name = book_name;
        this.book_author = book_author;
        this.genre = genre;
    }
}


