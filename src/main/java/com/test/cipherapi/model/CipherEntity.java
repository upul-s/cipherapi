package com.test.cipherapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cipher_req")
public class CipherEntity {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long cipherId;
		@NotBlank(message = "Request Message is mandatory")
		private String requestMessage;
		private String cipherMessage;
		private Date submissionDate;



}
