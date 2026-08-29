package com.humancloud.entity;

import com.humancloud.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity 
{
	@Id
	
	@GeneratedValue(generator = "gen1",strategy = GenerationType.AUTO)
	private Integer id;
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
