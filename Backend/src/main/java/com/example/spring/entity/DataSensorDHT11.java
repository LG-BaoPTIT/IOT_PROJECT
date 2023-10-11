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
public class DataSensorDHT11 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "moduleId", referencedColumnName = "moduleId")
    private Module module;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "humidity")
    private double humidity;

    @Column(name = "temperature")
    private  double temperature;
}
