package com.example.spring.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "LightLogs")
public class LightLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logId")
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "moduleId", referencedColumnName = "moduleId")
    private Module module;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "status", nullable = false)
    private String status; // Trạng thái (Bật/Tắt) -> (1/0)

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private Users user;

}