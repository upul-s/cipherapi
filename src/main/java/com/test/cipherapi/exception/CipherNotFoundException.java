package com.test.cipherapi.exception;

public class CipherNotFoundException extends RuntimeException {


	private static final Long serialVersionUID =1L;

	public CipherNotFoundException(String message){
		super(message);

	}
}
