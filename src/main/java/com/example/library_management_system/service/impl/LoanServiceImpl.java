package com.example.library_management_system.service.impl;

import com.example.library_management_system.dto.LoanResponse;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Loan;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.LoanRepository;
import com.example.library_management_system.repository.MemberRepository;
import com.example.library_management_system.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import com.example.library_management_system.service.EmailService;

@Service
public  class LoanServiceImpl implements LoanService {
    // yeni ekledim

    @Autowired
    private EmailService emailService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;


    // silmeyi unutma !!!!!!!
    @Override
    public void deleteAllLoans() {
        loanRepository.deleteAll();
    }
    @Override
    public List<LoanResponse> getOverdueLoans() {
        LocalDate today = LocalDate.now();
        List<Loan> overdueLoans = loanRepository.findByExpectedReturnDateBeforeAndActualReturnDateIsNull(today);

        return overdueLoans.stream()
                .map(loan -> LoanResponse.builder()
                        .loanId(loan.getId())
                        .bookName(loan.getBook().getBookName())
                        .memberName(loan.getMember().getMemberName())
                        .memberSurname(loan.getMember().getMemberSurname())
                        .loanDate(loan.getLoanDate())
                        .returnDate(loan.getExpectedReturnDate())
                        .returned(loan.getReturned() != null ? loan.getReturned() : false)
                        // Eğer gecikme durumu hesaplanacaksa aşağıdaki gibi hesaplayabilirsiniz
                        .delayed(today.isAfter(loan.getExpectedReturnDate()))
                        .delayMessage(today.isAfter(loan.getExpectedReturnDate()) ?
                                "Kitap gecikti!" : "")
                        .build())
                .toList();
    }


    @Override
    public void notifyLateLoans() {
        List<Loan> activeLoans = loanRepository.findByActualReturnDateIsNull();

        for (Loan loan : activeLoans) {
            LocalDate expectedReturn = loan.getExpectedReturnDate();
            if (expectedReturn != null && LocalDate.now().isAfter(expectedReturn)) {
                Member member = loan.getMember();

                String email = member.getMemberEmail();
                String subject = "Kitap İade Gecikmesi Hakkında";
                String message = "Merhaba " + member.getMemberName() + ",\n\n"
                        + "Ödünç aldığınız \"" + loan.getBook().getBookName() + "\" kitabının iade süresi "
                        + expectedReturn + " tarihinde dolmuştur.\n"
                        + "Lütfen kitabı en kısa sürede iade ediniz.\n\n"
                        + "Teşekkürler.";

                emailService.sendSimpleMessage(email, subject, message);
            }
        }
    }


    @Override
    public LoanResponse takeBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Üye bulunamadı"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        if (book.getAvailable() == null || !book.getAvailable()) {
            throw new RuntimeException("Kitap şu anda ödünç verilemez.");
        }

        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        // 15 gün sonrası iade süresi olarak belirleniyor
        LocalDate expectedReturnDate = LocalDate.now().plusDays(15);
        loan.setExpectedReturnDate(expectedReturnDate);

        loan.setReturned(false);
        loan.setActualReturnDate(null);

        book.setAvailable(false);
        bookRepository.save(book);
        loan = loanRepository.save(loan);

        //  mesaj
        String infoMessage = "Kitap başarıyla ödünç verildi. Lütfen kitabı " + expectedReturnDate + " tarihine kadar iade edin.";

        LoanResponse response = toLoanResponse(loan);
        response.setInfoMessage(infoMessage);

        return response;
    }



    @Override
    public LoanResponse returnBook(Long memberId, Long bookId) {
        Loan loan = loanRepository.findByMember_MemberIdAndBook_BookIdAndActualReturnDateIsNull(memberId, bookId)
                .orElseThrow(() -> new RuntimeException("Aktif ödünç kayıt bulunamadı."));

        if (loan.getActualReturnDate() != null) {
            throw new RuntimeException("Kitap zaten iade edilmiş.");
        }

        loan.setActualReturnDate(LocalDate.now());
        loan.setReturned(true);
        loanRepository.save(loan);

        // Kitabı tekrar müsait yap
        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return toLoanResponse(loan);
    }

    @Override
    public List<LoanResponse> getActiveLoans() {
        List<Loan> activeLoans = loanRepository.findByActualReturnDateIsNull();
        return activeLoans.stream().map(this::toLoanResponse).collect(Collectors.toList());
    }

    @Override
    public List<LoanResponse> getActiveLoansByMember(Long memberId) {
        List<Loan> activeLoans = loanRepository.findByMember_MemberIdAndActualReturnDateIsNull(memberId);
        return activeLoans.stream().map(this::toLoanResponse).collect(Collectors.toList());
    }

    private LoanResponse toLoanResponse(Loan loan) {
        boolean delayed = false;
        String delayMessage;

        LocalDate expectedReturnDate = loan.getExpectedReturnDate();
        LocalDate actualReturnDate = loan.getActualReturnDate();

        if (actualReturnDate != null) {
            System.out.println("Beklenen iade tarihi: " + expectedReturnDate + ", Bugün: " + LocalDate.now());
            delayMessage = "Kitap teslim edildi.";

        }
        else {
            if (expectedReturnDate != null && LocalDate.now().isAfter(expectedReturnDate)) {
                delayed = true;
                long daysLate = ChronoUnit.DAYS.between(expectedReturnDate, LocalDate.now());
                delayMessage = "Kitap " + daysLate + " gün geç kaldı!";
            } else {
                delayMessage = "Kitap henüz iade edilmedi, süresi devam ediyor.";
            }
        }

        return LoanResponse.builder()
                .loanId(loan.getId())
                .memberId(loan.getMember().getMemberId())
                .memberName(loan.getMember().getMemberName())
                .memberSurname(loan.getMember().getMemberSurname())
                .bookId(loan.getBook().getBookId())
                .bookName(loan.getBook().getBookName())
                .loanDate(loan.getLoanDate())
                .returnDate(expectedReturnDate)
                .returned(loan.getReturned())
                .delayed(delayed)
                .delayMessage(delayMessage)
                .build();
    }


}
