package com.test.cipherapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CipherResponse {

	private String requestMessage;
	private String cipherResponseStr;
}
