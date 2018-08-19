package com.imcs.maven.MavenJDBC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

public class Customer {
	
	private int id;
	private String name;
	private int age;
	private String address;
	private float purchase;
	
	
}
