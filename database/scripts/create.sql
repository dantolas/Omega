DELIMITER //

START TRANSACTION;
create database omega;
use omega;


create table IF NOT EXISTS User(
	id BINARY(16) primary key default (UUID_TO_BIN(UUID())) ,
	username varchar(255) not null,
	email varchar(255) not null unique,
	passHash varchar(500) not null,
	salt varchar(255) not null
);

END //

DELIMITER ;


