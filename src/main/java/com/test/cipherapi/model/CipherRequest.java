package com.test.cipherapi.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CipherRequest {

	@NotEmpty(message = "Please provide a message")
	private String message;

	private int rotation = 0;

}
