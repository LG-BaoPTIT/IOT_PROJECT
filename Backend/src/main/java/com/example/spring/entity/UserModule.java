package com.example.spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserModules")
public class UserModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "moduleId", referencedColumnName = "moduleId")
    private Module module;
}
