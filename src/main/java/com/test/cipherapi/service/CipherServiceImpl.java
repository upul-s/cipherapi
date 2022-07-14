package com.test.cipherapi.service;

import com.test.cipherapi.exception.CipherNotFoundException;
import com.test.cipherapi.model.CipherEntity;
import com.test.cipherapi.model.CipherRequest;
import com.test.cipherapi.model.CipherResponse;
import com.test.cipherapi.repository.CipherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CipherServiceImpl implements CipherService {

  @Autowired private CipherRepository cipherRepository;

  @Override
  public Page<CipherEntity> getAllCiphers(Pageable pageable) {
    return cipherRepository.findAll(pageable);
  }

  @Override
  public CipherEntity getCipherById(Long id) {

    Optional<CipherEntity> cipherEntity = cipherRepository.findById(id);

    if (cipherEntity.isPresent()) {
      return cipherEntity.get();
    }
    throw new CipherNotFoundException("Cipher not found for the Id : " + id);
  }

  @Override
  public CipherResponse createCipher(CipherRequest cipherRequest) {

    String originalMessage = cipherRequest.getMessage();
    int noRotations = cipherRequest.getRotation();
    String responseCipher = decrypt2MMessage(originalMessage, noRotations);

    CipherResponse cipherResponse =
        CipherResponse.builder()
            .requestMessage(originalMessage)
            .cipherResponseStr(responseCipher)
            .build();

    cipherRepository.save(
        CipherEntity.builder()
            .requestMessage(originalMessage)
            .cipherMessage(responseCipher)
            .submissionDate(new Date())
            .build());
    return cipherResponse;
  }

  public static String decrypt2MMessage(String message, int noRotations) {

    StringBuilder tempResult = new StringBuilder();

    for (int i = 0; i < message.length(); i++) {
      // check whether the character is alphabetic or not
      if ((Character.isAlphabetic(message.charAt(i)))) {
        if (Character.isUpperCase(message.charAt(i))) {

          int currentIndex = message.charAt(i) - 'A';

          int newIndex = (currentIndex + noRotations) % 26;

          tempResult.append(Character.toUpperCase((char) ('a' + newIndex)));

        } else {

          int currentIndex = message.charAt(i) - 'a';

          int newIndex = (currentIndex + noRotations) % 26;

          tempResult.append((char) ('a' + newIndex));
        }
      } else {
        tempResult.append(message.charAt(i));
      }
    }
    return tempResult.toString();
  }
}
