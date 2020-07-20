package com.genx.dto;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.genx.model.RoleName;

import lombok.Data;

@Data
public class SignUpForm {

	private String name;

	private String username;

	private String email;

	private Set<String> role;

	private String password;

	private String salary;

	private String age;
	
	private String mobile;

}