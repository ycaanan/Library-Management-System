package com.example.library_management_system.controller;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Tüm kitapları listeleme endpoint'i

    @GetMapping("/GetAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/Create")
        @PostMapping
        public Book createBook (@RequestBody Book book) {
            return bookService.saveBook(book);
        }
        }
