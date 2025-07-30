package com.example.library_management_system.repository;

import com.example.library_management_system.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    // isme göre kitap arama
    List<Book> findByBookNameContainingIgnoreCase(String name);
    Optional<Book> findByBookName(String bookName);
}
