package com.example.library_management_system.config;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) { // Eğer veritabanı boşsa kitapları ekle
                bookRepository.save(new Book("Sefiller", "Victor Hugo", "Roman"));
                bookRepository.save(new Book("Suç ve Ceza", "Dostoyevski", "Roman"));
                bookRepository.save(new Book("İçimizdeki Şeytan", "Sabahattin Ali", "Roman"));
                bookRepository.save(new Book("Baba Evi", "Orhan Kemal", "Roman"));
                bookRepository.save(new Book("Romeo ve Juliet","William Shakespeare","Roman"));
                System.out.println("📚 Örnek kitaplar veritabanına eklendi.");
            } else {
                System.out.println("📚 Veritabanında zaten kitaplar var. Yeni veri eklenmedi.");
            }
        };
    }
}
