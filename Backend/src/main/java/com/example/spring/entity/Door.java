package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Door")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Door {
    @Id
    @Column(name = "DoorId")
    private String doorId;

    @ManyToOne
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId", insertable = false, updatable = false)
    private Device device;

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "description")
    private String description;
}
