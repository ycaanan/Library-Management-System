package com.example.library_management_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Process {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String processType;
   private LocalDateTime processDate;

   @ManyToOne
   @JoinColumn(name = "book_id")
   private Book book;

   @ManyToOne
   @JoinColumn(name = "member_id")
   private Member member;
}
