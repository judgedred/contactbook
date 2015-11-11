CREATE TABLE Person (
  person_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  person_name VARCHAR(20) NOT NULL,
  person_surname VARCHAR(20) NOT NULL,
  person_patronymic VARCHAR(20) NOT NULL,
  birthday DATE NOT NULL,
  PRIMARY KEY (person_id));
CREATE TABLE Address (
  address_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  address_value VARCHAR(100) NOT NULL,
  person_id INT NOT NULL,
  PRIMARY KEY (address_id),
  FOREIGN KEY (person_id) REFERENCES Person(person_id));
CREATE TABLE Phone (
  phone_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  phone_type VARCHAR(15) NOT NULL,
  phone_number BIGINT NOT NULL,
  default_flag BOOLEAN NOT NULL,
  person_id INT NOT NULL,
  PRIMARY KEY (phone_id),
  FOREIGN KEY (person_id) REFERENCES Person(person_id));
CREATE TABLE Email (
  email_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  email_type VARCHAR(15) NOT NULL,
  email_value VARCHAR(20) NOT NULL,
  default_flag BOOLEAN NOT NULL,
  person_id INT NOT NULL,
  PRIMARY KEY (email_id),
  FOREIGN KEY (person_id) REFERENCES Person(person_id));
