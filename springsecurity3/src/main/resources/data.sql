/*
CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    enabled INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE authorities (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(45) NOT NULL,
    authority varchar(45) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users VALUES (1, 'happy', '12345', '1');
INSERT INTO authorities VALUES (1, 'happy', 'write'); */

CREATE TABLE CUSTOMER (
    id int not null primary key auto_increment,
    email varchar(40) not null ,
    password varchar(200) not null ,
    role varchar(45) not null
);

INSERT INTO CUSTOMER (email, password , role) VALUES ('tmdgh717@naver.com', '1234', 'admin');
