package com.example.library_management_system.controller;

import com.example.library_management_system.service.LoanService;
import com.example.library_management_system.dto.LoanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.library_management_system.dto.LoanRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    // Kitap ödünç alma
    @PostMapping("/takebook")
    public ResponseEntity<LoanResponse> takeBook(@RequestBody LoanRequest request) {
        LoanResponse response = loanService.takeBook(request.getMemberId(), request.getBookId());
        return ResponseEntity.ok(response);
    }


    // Kitap iade etme
    @PostMapping("/returnBook")
    public ResponseEntity<LoanResponse> returnBook(@RequestBody LoanRequest request) {
        LoanResponse response = loanService.returnBook(request.getMemberId(), request.getBookId());
        return ResponseEntity.ok(response);
    }

    // Tüm aktif (iade edilmemiş) ödünçleri getir
    @GetMapping("/active")
    public ResponseEntity<List<LoanResponse>> getActiveLoans() {
        return ResponseEntity.ok(loanService.getActiveLoans());
    }

    // Belirli bir üyeye ait aktif ödünçler
    @GetMapping("/activeByMember")
    public ResponseEntity<List<LoanResponse>> getActiveLoansByMember(@RequestParam Long memberId) {
        return ResponseEntity.ok(loanService.getActiveLoansByMember(memberId));
    }

    // Tüm ödünç kayıtlarını sil (uyarı: dikkatli kullan!)
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllLoans() {
        loanService.deleteAllLoans();
        return ResponseEntity.ok("Tüm ödünç kayıtları silindi.");
    }

    @PostMapping("/notifyLateLoans")
    public ResponseEntity<String> notifyLateLoans() {
        loanService.notifyLateLoans();
        return ResponseEntity.ok("Geciken kitaplar için e-mail gönderildi.");
    }
    // geç kalan kitapları listeleme
    @GetMapping("/overdue")
    public ResponseEntity<List<LoanResponse>> getOverdueLoans() {
        List<LoanResponse> overdueLoans = loanService.getOverdueLoans();
        return ResponseEntity.ok(overdueLoans);
    }







}
