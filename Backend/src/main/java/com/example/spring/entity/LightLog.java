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
    @Column(name = "id")
    private Long id;

    @Column(name = "lightId")
    private String lightId;

    @ManyToOne
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId", insertable = false, updatable = false)
    private Device device;

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "status", nullable = false)
    private String status; // Trạng thái (Bật/Tắt) -> (1/0)

}