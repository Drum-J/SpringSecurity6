CREATE TABLE CUSTOMER (
    id int not null primary key auto_increment,
    email varchar(40) not null ,
    password varchar(200) not null ,
    role varchar(45) not null
);

INSERT INTO CUSTOMER (email, password , role) VALUES ('tmdgh717@naver.com', '1234', 'admin');
