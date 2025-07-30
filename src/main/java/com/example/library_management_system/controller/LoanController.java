package com.example.library_management_system.controller;

import com.example.library_management_system.service.LoanService;
import com.example.library_management_system.dto.LoanResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LoanResponse> takeBook(@RequestBody Map<String, String> request) {
        Long memberId = Long.valueOf(request.get("memberId"));
        Long bookId = Long.valueOf(request.get("bookId"));

        LoanResponse response = loanService.takeBook(memberId, bookId);
        return ResponseEntity.ok(response);
    }


    // Kitap iade etme
    @PostMapping("/returnBook")
    public ResponseEntity<LoanResponse> returnBook(@RequestBody Map<String, String> request) {
        Long memberId = Long.valueOf(request.get("memberId"));
        Long bookId = Long.valueOf(request.get("bookId"));

        LoanResponse response = loanService.returnBook(memberId, bookId);
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
}
