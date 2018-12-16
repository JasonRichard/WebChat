CREATE DATABASE IF NOT EXISTS chat;

USE chat;

CREATE TABLE IF NOT EXISTS `admin`(
   `aid` INT UNSIGNED AUTO_INCREMENT,
   `account` VARCHAR(20) NOT NULL,
   `password` VARCHAR(20) NOT NULL,
   `priority` INT UNSIGNED,
   PRIMARY KEY ( `aid` )
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `private_info`(
   `uid` INT UNSIGNED AUTO_INCREMENT,
   `account` VARCHAR(45) NOT NULL,
   `password` VARCHAR(45) NOT NULL,
   `ip_add` VARCHAR(45) NOT NULL,
   PRIMARY KEY ( `uid` )
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `public_info`(
   `uid` INT UNSIGNED AUTO_INCREMENT,
   `nickname` VARCHAR(45),
   `tel` VARCHAR(45),
   `gender` VARCHAR(45),
   `avatar` VARCHAR(45),
   PRIMARY KEY ( `uid` ),
   FOREIGN KEY ( `uid` ) 
   REFERENCES `chat`.`private_info`( `uid` ) 
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `record`(
   `sender` INT UNSIGNED,
   `receiver` INT UNSIGNED,
   `content` VARCHAR(90) NOT NULL,
   `time` DATETIME,
   PRIMARY KEY ( `sender`,`receiver`,`time`),
   FOREIGN KEY (`sender`) 
   REFERENCES `chat`.`private_info`(`uid`),
   FOREIGN KEY (`receiver`) 
   REFERENCES `chat`.`private_info`(`uid`)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `friend`(
   `num` INT UNSIGNED AUTO_INCREMENT, 
   `uid1` INT UNSIGNED,
   `uid2` INT UNSIGNED,
   PRIMARY KEY ( `num` ),
   FOREIGN KEY ( `uid1` ) 
   REFERENCES `chat`.`private_info`(`uid`),
   FOREIGN KEY ( `uid2` ) 
   REFERENCES `chat`.`private_info`(`uid`)
)ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `complaint`(
   `complainer` INT UNSIGNED,
   `complainee` INT UNSIGNED,
   `content` VARCHAR(90) NOT NULL,
   `time` DATETIME,
   `check` BOOLEAN,
   PRIMARY KEY ( `complainer`,`complainee`,`time`),
   FOREIGN KEY ( `complainer` )
   REFERENCES `chat`.`private_info`(`uid`),
   FOREIGN KEY ( `complainee` ) 
   REFERENCES `chat`.`private_info`(`uid`)
)ENGINE = InnoDB;

show variables like '%time_zone%';

set global time_zone='+8:00';