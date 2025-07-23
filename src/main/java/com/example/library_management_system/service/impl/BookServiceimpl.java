package com.example.library_management_system.service.impl;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final  BookRepository bookRepository;

    @Autowired
    //constructer
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Create işlemi
    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Get işlemi
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // İD'ye göre get işlemi
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Var olan kitabı güncelleme
    @Override
    public Book updateBook(Long id, Book book){
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        existingBook.setBook_name(book.getBook_name());
        existingBook.setBook_author(book.getBook_author());

        return bookRepository.save(existingBook);
    }

    // Kitabı veritabanından siliyoruz
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}