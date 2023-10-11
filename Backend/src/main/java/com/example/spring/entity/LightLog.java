package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "LightLogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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




}