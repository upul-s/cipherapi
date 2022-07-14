package com.test.cipherapi.controller;

import com.test.cipherapi.model.CipherEntity;
import com.test.cipherapi.model.CipherRequest;
import com.test.cipherapi.model.CipherResponse;
import com.test.cipherapi.service.CipherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class CipherController {

  @Autowired private CipherService cipherService;

  @GetMapping("/ciphers")
  public Page<CipherEntity> getAllMessages(Pageable pageable) {
    return cipherService.getAllCiphers(pageable);
  }

  @PostMapping("/ciphers")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CipherResponse> createCipher(@Valid @RequestBody CipherRequest request) {
    return ResponseEntity.ok(cipherService.createCipher(request)) ;
  }

  @GetMapping("/ciphers/{id}")
  public CipherEntity getMessageById(@PathVariable Long id) {
    return cipherService.getCipherById(id);
  }
}
