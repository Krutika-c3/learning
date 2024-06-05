package com.application.example.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

	private Integer id;

	private String street;

	private String city;

	private Student student;
}