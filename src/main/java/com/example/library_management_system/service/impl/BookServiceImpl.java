package com.example.library_management_system.service.impl;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.LoanRepository;
import com.example.library_management_system.service.BookService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public BookServiceImpl(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı: " + id));
    }

    @Override
    public Book updateBook(Long id, @NotNull Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı: " + id));

        if (book.getBookName() != null) {
            existingBook.setBookName(book.getBookName());
        }
        if (book.getBookAuthor() != null) {
            existingBook.setBookAuthor(book.getBookAuthor());
        }
        if (book.getGenre() != null) {
            existingBook.setGenre(book.getGenre());
        }

        return bookRepository.save(existingBook);
    }


    @Override
    public Book deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı: " + id));

        boolean loanExists = loanRepository.existsByBookAndActualReturnDateIsNull(book);
        if (loanExists) {
            throw new RuntimeException("Kitap aktif ödünçte olduğu için silinemez.");
        }

        bookRepository.delete(book);
        return book;
    }

    @Override
    public List<Book> searchBooksByName(String name) {
        return bookRepository.findByBookNameContainingIgnoreCase(name);
    }
}
