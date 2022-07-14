package com.test.cipherapi.service;

import com.test.cipherapi.model.CipherEntity;
import com.test.cipherapi.model.CipherRequest;
import com.test.cipherapi.model.CipherResponse;
import com.test.cipherapi.repository.CipherRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

public class CipherServiceImplTest {
  @Mock CipherRepository cipherRepository;
  @InjectMocks CipherServiceImpl cipherServiceImpl;

  private CipherRequest cipherRequest;

  @BeforeMethod
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    cipherRequest = new CipherRequest();
    cipherRequest.setMessage("2-deFGhijklmnopqrstuvwxyzabc");
    cipherRequest.setRotation(3);
  }

  @Test
  public void testGetAllCiphers() {

    Page<CipherEntity> result = cipherServiceImpl.getAllCiphers(null);
    Assert.assertEquals(result, null);
  }


  @Test
  public void testCreateCipher() {
    CipherResponse result = cipherServiceImpl.createCipher(cipherRequest);
    Assert.assertEquals(
        result, new CipherResponse("2-deFGhijklmnopqrstuvwxyzabc", "2-ghIJklmnopqrstuvwxyzabcdef"));
  }

  @Test
  public void testDecrypt2MMessageWithLowerCase() {
    String result = CipherServiceImpl.decrypt2MMessage("abcdefghijklmnopqrstuvwxyz", 3);
    Assert.assertEquals(result, "defghijklmnopqrstuvwxyzabc");
  }

  @Test
  public void testDecrypt2MMessageWithUpperCase() {
    String result = CipherServiceImpl.decrypt2MMessage("abCDefghijklmnopqrstuvwxyz", 3);
    Assert.assertEquals(result, "deFGhijklmnopqrstuvwxyzabc");
  }

  @Test
  public void testDecrypt2MMessageWithNumbers() {
    String result = CipherServiceImpl.decrypt2MMessage("2abCDefghijklmnopqrstuvwxyz", 3);
    Assert.assertEquals(result, "2deFGhijklmnopqrstuvwxyzabc");
  }

  @Test
  public void testDecrypt2MMessageWithNumbersAndSpecial() {
    String result = CipherServiceImpl.decrypt2MMessage("2-abCDefghijklmnopqrstuvwxyz", 3);
    Assert.assertEquals(result, "2-deFGhijklmnopqrstuvwxyzabc");
  }
}

