package com.example.spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserDevice")
public class UserDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId")
    private Device device;
}
