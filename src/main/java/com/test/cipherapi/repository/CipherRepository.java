package com.test.cipherapi.repository;

import com.test.cipherapi.model.CipherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CipherRepository extends JpaRepository<CipherEntity, Long> {

}
