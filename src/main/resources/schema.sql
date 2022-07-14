DROP TABLE IF EXISTS CIPHER_REQ;


create table CIPHER_REQ(
   cipher_id INT NOT NULL AUTO_INCREMENT,
   request_message VARCHAR(100) NOT NULL,
   cipher_message VARCHAR(40) NOT NULL,
   submission_date Varchar(40),
PRIMARY KEY ( cipher_id )
);