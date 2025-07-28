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

    @PostMapping("/create")
    public Member addMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @GetMapping("/all")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(member -> {
                    memberRepository.delete(member);
                    return ResponseEntity.ok("Üye silindi: " + id);
                })
                .orElse(ResponseEntity.status(404).body("Üye bulunamadı: " + id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member newMemberData) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setMembername(newMemberData.getMembername());
                    member.setMembersurname(newMemberData.getMembersurname());
                    member.setMemberemail(newMemberData.getMemberemail());
                    memberRepository.save(member);
                    return ResponseEntity.ok(member);
                })
                .orElse(ResponseEntity.status(404).body(null));
    }




}
