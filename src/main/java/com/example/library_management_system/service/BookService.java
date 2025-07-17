package com.example.library_management_system.service;

import com.example.library_management_system.model.Book;

import java.util.List;

public interface BookService {


    // Yeni kitap ekleme
    Book saveBook(Book book);

    // Tüm kitapları listeleme
    List<Book> getAllBooks();

    // id'ye göre kitap getirme
    Book getBookById(Long id);

    // Var olan kitabı güncelleme
    Book updateBook(Long id, Book book);
    // Var olan kitabı silme
    void deleteBook(Long id);


}