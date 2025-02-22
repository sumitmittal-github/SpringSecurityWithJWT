package com.sumit.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {

    // This is Database user class

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String username;

    private String password;

    private List<String> roles = new ArrayList<>();

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;




}