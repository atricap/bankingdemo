CREATE DATABASE IF NOT EXISTS `bankingdemo`
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_0900_as_cs;
USE `bankingdemo`;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `enabled` boolean NOT NULL DEFAULT true,
  `full_name` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `business_customer` boolean NOT NULL DEFAULT false,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `user_name` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`user_name`, `authority`),
  FOREIGN KEY (`user_name`) REFERENCES `customer`(`user_name`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `accnumber` int NOT NULL AUTO_INCREMENT,
  `balance` numeric(17,2) NOT NULL DEFAULT 0,
  `customer_id` int NULL,
  PRIMARY KEY (`accnumber`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`)
    ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1001;

DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone` (
  `phonenumber` bigint NOT NULL,
  `purpose` varchar(15) NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`phonenumber`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer`(`id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;
