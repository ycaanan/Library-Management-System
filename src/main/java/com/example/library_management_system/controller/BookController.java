package com.example.library_management_system.controller;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

//  sınıf üzerinde kullanılırsa "..." bu sınıfın tüm  URL yolunu temsil eder.
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    //contructer
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Tüm kitapları listeleme endpoint'i

    //sadece GET tipi HTTP isteleri bu metoda gelir
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/create")

    public Book createBook (@RequestBody Book book) {
        return bookService.saveBook(book);
    }


    @GetMapping("/test")
    public String test() {
        return "Controller çalışıyor!";
    }







}
