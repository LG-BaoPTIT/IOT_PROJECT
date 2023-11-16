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
    @Column(name = "LogId")
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "lightId", referencedColumnName = "lightId", insertable = false, updatable = false)
    private Light light;

    @Column(name = "lightId")
    private String lightId;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "status", nullable = false)
    private String status;

}