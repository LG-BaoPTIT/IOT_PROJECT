package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Modules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moduleId")
    private Long moduleId;

    @Column(name = "moduleName", nullable = false)
    private String moduleName;

    @Column(name = "moduleType", nullable = false)
    private String moduleType;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

}
