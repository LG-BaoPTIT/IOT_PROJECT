package com.example.spring.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Users")
@Data
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "UserName", unique = true, nullable = false)
	private String userName;
	
	@Column(name = "PassWord", nullable = false)
	@JsonIgnore
	private String passWord;
	
	@Column(name = "Email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "Phone")
	private String phone;
	
	@Column(name = "UserStatus")
	private boolean userStatus;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "UserId"), inverseJoinColumns = @JoinColumn(name = "RoleId"))
	private Set<Roles> listRole = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "deviceId", referencedColumnName = "deviceId", insertable = false, updatable = false)
	private Device device;

	@Column(name = "deviceId")
	private String deviceId; // Trường này ánh xạ đến deviceId
}
