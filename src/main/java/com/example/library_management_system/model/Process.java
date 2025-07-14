package com.example.library_management_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;


import java.time.LocalDateTime;


@Entity
@Getter
@Setter

public class Process {

   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private long id;

   private String processType ;
   private LocalDateTime processDate ;

   @ManyToOne
   @JoinColumn(name = "id")
   private Book book;
@ManyToOne
   @JoinColumn(name = "id")
   private Member member;






}
