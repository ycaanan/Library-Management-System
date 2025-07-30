package com.example.library_management_system.controller;

import com.example.library_management_system.model.Member;
import com.example.library_management_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Üye ekle
    @PostMapping("/create")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member savedMember = memberRepository.save(member);
        return ResponseEntity.ok(savedMember);
    }

    // Tüm üyeleri listele
    @GetMapping("/all")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return ResponseEntity.ok(members);
    }

    // Üye güncelle
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody Member newMemberData) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setMemberName(newMemberData.getMemberName());
                    member.setMemberSurname(newMemberData.getMemberSurname());
                    member.setMemberEmail(newMemberData.getMemberEmail());
                    memberRepository.save(member);
                    return ResponseEntity.ok("Üye başarıyla güncellendi.");
                })
                .orElse(ResponseEntity.status(404).body("Üye bulunamadı veya silinmiş."));
    }

    // Üye sil
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    memberRepository.delete(member);
                    return ResponseEntity.ok("Üye silindi: " + id);
                })
                .orElse(ResponseEntity.status(404).body("Üye bulunamadı: " + id));
    }
}
