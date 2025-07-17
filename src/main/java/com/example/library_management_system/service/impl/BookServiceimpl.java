package com.example.library_management_system.service.impl;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceimpl implements BookService {

    // Basit, geçici kitap listesi
    private final List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getBook_id().equals(id))
                .findFirst()
                .orElse(null);
    }

    }

