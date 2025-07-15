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
    private String book_author ;
    private String genre;

}
