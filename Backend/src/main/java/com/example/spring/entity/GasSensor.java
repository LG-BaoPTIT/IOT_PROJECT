package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "DataSensorGas")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GasSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId", insertable = false, updatable = false)
    private Device device;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "value")
    private long value;

    @Column(name = "gasStatus")
    private int gasStatus;//0/1 => (khong phat hien khi gas/co phat hien khi gas)

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "description")
    private String description;
}
