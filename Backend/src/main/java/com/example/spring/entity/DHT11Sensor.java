package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DataSensorDHT11")
public class DHT11Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId", insertable = false, updatable = false)
    private Device device;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "humidity")
    private double humidity;

    @Column(name = "temperature")
    private  double temperature;

    @Column(name = "deviceId")
    private String deviceId; // Trường này ánh xạ đến deviceId

    @Column(name = "description")
    private String description;

}
