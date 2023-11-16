package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Light")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Light {
    @Id
    @Column(name = "lightId")
    private String lightId;

    @ManyToOne
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId", insertable = false, updatable = false)
    private Device device;

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "description")
    private String description;
}
