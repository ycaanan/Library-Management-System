package com.example.library_management_system.config;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Loan;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.LoanRepository;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Optional;
import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository,
                                   MemberRepository memberRepository,
                                   LoanRepository loanRepository) {
        return args -> {
            // Kitap ekle (var mı kontrol et)
            if (bookRepository.count() == 0) {
                bookRepository.save(new Book("Sefiller", "Victor Hugo", "Roman"));
                bookRepository.save(new Book("Suç ve Ceza", "Dostoyevski", "Roman"));
                bookRepository.save(new Book("Kukla", "Mustafa", "Roman")); // Kukla kitabı eklendi
                System.out.println("📚 Örnek kitaplar veritabanına eklendi.");
            } else {
                System.out.println("📚 Veritabanında zaten kitaplar var. Yeni veri eklenmedi.");
            }

            // Üye ekle
            if (loanRepository.count() == 0) {
                Book sefiller = new Book("Sefiller", "Victor Hugo", "Roman");
                sefiller.setAvailable(false);
                bookRepository.save(sefiller);

                Member ahmet = new Member();
                ahmet.setMemberName("Ahmet");
                ahmet.setMemberSurname("Yılmaz");
                ahmet.setMemberEmail("ahmet@example.com");
                memberRepository.save(ahmet);

                Loan loan = Loan.builder()
                        .member(ahmet)
                        .book(sefiller)
                        .loanDate(LocalDate.now().minusDays(30))
                        .expectedReturnDate(LocalDate.now().minusDays(15))
                        .returned(false)
                        .build();

                loanRepository.save(loan);
                System.out.println("📦 Geç kalan kitap eklendi.");
            } else {
                System.out.println("📦 Loan zaten var, tekrar eklenmedi.");
            }
        };
    }
}
