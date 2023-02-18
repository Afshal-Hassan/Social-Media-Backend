package com.example.social.entities.ml_domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user")
    private String user;

    @Column(name = "interest")
    private String interest;

    @Column(name = "category")
    private String category;
}
