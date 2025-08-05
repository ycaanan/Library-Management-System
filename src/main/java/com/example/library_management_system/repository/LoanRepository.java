package com.example.library_management_system.repository;

import com.example.library_management_system.model.Loan;
import com.example.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Aktif (henüz iade edilmemiş) ödünç kayıtlarını getirir
    List<Loan> findByActualReturnDateIsNull();

    // Belirli üyeye ait aktif ödünç kayıtları
    List<Loan> findByMember_MemberIdAndActualReturnDateIsNull(Long memberId);

    // Belirli üyeye ve kitaba ait aktif ödünç kaydı (varsa)
    Optional<Loan> findByMember_MemberIdAndBook_BookIdAndActualReturnDateIsNull(Long memberId, Long bookId);

    // Silme işlemi öncesi, kitap halen aktif ödünçte mi diye kontrol için
    boolean existsByBookAndActualReturnDateIsNull(Book book);

    List<Loan> findByExpectedReturnDateBeforeAndActualReturnDateIsNull(LocalDate date);

}
