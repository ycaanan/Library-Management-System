package com.example.library_management_system.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter


public class Member {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

     private String membername;
    private  String membersurname;
    private String memberemail;



}
