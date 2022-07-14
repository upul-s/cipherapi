package com.test.cipherapi.service;

import com.test.cipherapi.model.CipherEntity;
import com.test.cipherapi.model.CipherRequest;
import com.test.cipherapi.model.CipherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface CipherService {

	Page<CipherEntity> getAllCiphers(Pageable pageable);
	CipherEntity getCipherById(Long id);

	CipherResponse createCipher(CipherRequest cipherRequest);

}
