package net.dreamlu.test;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
	private Integer id;
	private String name;
	private Integer age;
	private LocalDateTime birthday;
}
