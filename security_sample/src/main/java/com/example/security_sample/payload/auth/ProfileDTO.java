package com.example.security_sample.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfileDTO {

	private long id;
	private String email;
	private String authorities;

}
