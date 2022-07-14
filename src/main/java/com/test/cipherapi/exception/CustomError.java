package com.test.cipherapi.exception;

import lombok.Data;

import java.util.Date;

@Data
public class CustomError {
	private Integer statusCode;
	private String message;
	private Date timesTamp ;
}
