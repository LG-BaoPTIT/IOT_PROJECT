package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Devices")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Device {

    @Id
    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "moduleType", nullable = false)
    private String moduleType;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

}
