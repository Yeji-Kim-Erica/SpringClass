package org.doit.ik.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

	private String firstName;
    private String lastName;
    private int age;
	
}
