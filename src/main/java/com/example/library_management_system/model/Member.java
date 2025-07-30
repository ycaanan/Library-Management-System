package com.example.library_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long memberId;

     private String memberName;
     private String memberSurname;
     private String memberEmail;
}
