package com.example.library_management_system.controller;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.dto.BookResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.library_management_system.dto.BookListResponse;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            BookResponse response = new BookResponse(book, "Kitap bulundu", 200);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            BookResponse response = new BookResponse(null, "Kitap bulunamadı", 404);
            return ResponseEntity.status(404).body(response);
        }
    }
// 201 created yeni kaynak oluşturuldu
    @PostMapping("/create")
    public ResponseEntity<BookResponse> createBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        BookResponse response = new BookResponse(savedBook, "Kitap başarıyla eklendi", 201);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody Book book) {
        try {
            Book updatedBook = bookService.updateBook(id, book);
            BookResponse response = new BookResponse(updatedBook, "Kitap başarıyla güncellendi", 200);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            BookResponse response = new BookResponse(null, "Kitap bulunamadı", 404);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable Long id) {
        try {
             Book deleteBook = bookService.deleteBook(id);
            BookResponse response= new BookResponse(deleteBook,"Kitap BAşarıyla silindi",200);
            return ResponseEntity.ok(response);
        }//hata olursa çalışır
        catch (RuntimeException e){
            BookResponse response=new BookResponse( null," Kitap bulunamadı ", 404);
       return ResponseEntity.status(404).body(response);
        }

        }


    @GetMapping("/search")
    public ResponseEntity<BookListResponse> searchBooks(@RequestParam String name) {
        List<Book> books = bookService.searchBooksByName(name);
        if (!books.isEmpty()) {
            BookListResponse response = new BookListResponse(books, "Kitaplar bulundu", 200);
            return ResponseEntity.ok(response);
        } else {
            BookListResponse response = new BookListResponse(null, "Kitap bulunamadı", 404);
            return ResponseEntity.status(404).body(response);
        }
    }


    // kitap ödünç alma verme kısmını ileride ekleyebilirsin
}
