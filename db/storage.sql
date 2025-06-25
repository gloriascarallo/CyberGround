DROP DATABASE IF EXISTS storage;
CREATE DATABASE storage;
USE storage;


DROP TABLE IF EXISTS user;

CREATE TABLE `user` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`)
)

DROP TABLE IF EXISTS registereduser;


CREATE TABLE `registereduser` (
  `username` varchar(45) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telephoneNumber` char(10) DEFAULT NULL,
  `idUser` int NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE(`username`, `idUser`),
  KEY `idUser_idx` (`idUser`),
  CONSTRAINT `idUser_RegistratedUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
)

DROP TABLE IF EXISTS admin;

CREATE TABLE `admin` (
  `id` int NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE(`username`, `id`),
  KEY `id_Admin_idx` (`id`),
  CONSTRAINT `idUser_Admin` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `username_Admin` FOREIGN KEY (`username`) REFERENCES `registereduser` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE
)


DROP TABLE IF EXISTS cart;

CREATE TABLE `cart` (
  `idCart` int NOT NULL,
  PRIMARY KEY (`idCart`),
  CONSTRAINT `idCart_Cart` FOREIGN KEY (`idCart`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
)


DROP TABLE IF EXISTS product_situatedin_cart;

CREATE TABLE `product_situatedin_cart` (
  `id_SituatedIn` int AUTO_INCREMENT PRIMARY KEY,
  `idCart` int NOT NULL,
  `idProduct` int NOT NULL,
  `dateAdded` date NOT NULL,
  `quantity` int NOT NULL,
  UNIQUE (`idProduct`,`idCart`),
  KEY `idCart_idx` (`idCart`),
  KEY `idProduct_idx` (`idProduct`),
  CONSTRAINT `idCart_SituatedIn` FOREIGN KEY (`idCart`) REFERENCES `cart` (`idCart`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `idProduct_SituatedIn` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
)

DROP TABLE IF EXISTS category;

CREATE TABLE `category` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
)

DROP TABLE IF EXISTS order;

CREATE TABLE `order` (
  `id_Order` int AUTO_INCREMENT PRIMARY KEY,
  `id` int NOT NULL,
  `datePurchase` date DEFAULT NULL,
  `dateDelivery` date DEFAULT NULL,
  `dateShipping` date DEFAULT NULL,
  `idCart` int NOT NULL,
  UNIQUE (`id`,`idCart`),
  KEY `idCart_idx` (`idCart`),
  CONSTRAINT `idCart_Order` FOREIGN KEY (`idCart`) REFERENCES `cart` (`idCart`) ON DELETE RESTRICT ON UPDATE CASCADE
)

DROP TABLE IF EXISTS product;

CREATE TABLE `product` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `dateUpload` date DEFAULT NULL,
  `supplier` varchar(45) DEFAULT NULL,
  `categoryName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoryName_idx` (`categoryName`),
  CONSTRAINT `categoryName` FOREIGN KEY (`categoryName`) REFERENCES `category` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
) 


DROP TABLE IF EXISTS method_payment;

CREATE TABLE `method_payment` (
  `pan` char(19) NOT NULL,
  `expirationDate` char(5) NOT NULL,
  `cvc` varchar(4) NOT NULL,
  PRIMARY KEY (`pan`),
  KEY `SECONDARY` (`expirationDate`),
  KEY `TERTIARY` (`cvc`)
) 


DROP TABLE IF EXISTS registereduser_has_method_payment;

CREATE TABLE `registereduser_has_method_payment` (

  `id_has_method_payment` int AUTO_INCREMENT PRIMARY KEY,
  `usernameRegisteredUser` varchar(45) NOT NULL,
  `panMethodPayment` char(19) NOT NULL,
  `expirationDateMethodPayment` char(5) NOT NULL,
  `cvcMethodPayment` varchar(4) NOT NULL,
  UNIQUE (`usernameRegisteredUser`,`panMethodPayment`,`expirationDateMethodPayment`,`cvcMethodPayment`),
  KEY `expirationDate_HasMethodPayment_idx` (`expirationDateMethodPayment`),
  KEY `pan_HasMethodPayment_idx` (`panMethodPayment`),
  KEY `cvc_methodPayment_idx` (`cvcMethodPayment`),
  CONSTRAINT `cvc_methodPayment` FOREIGN KEY (`cvcMethodPayment`) REFERENCES `method_payment` (`cvc`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `expirationDate_HasMethodPayment` FOREIGN KEY (`expirationDateMethodPayment`) REFERENCES `method_payment` (`expirationDate`) ON UPDATE CASCADE,
  CONSTRAINT `usernameRegisteredUser_HasMethodPayment` FOREIGN KEY (`usernameRegisteredUser`) REFERENCES `registereduser` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `pan_HasMethodPayment` FOREIGN KEY (`panMethodPayment`) REFERENCES `method_payment` (`pan`) ON UPDATE CASCADE
)


DROP TABLE IF EXISTS address;

CREATE TABLE `address` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
)

DROP TABLE IF EXISTS registereduser_has_address;

CREATE TABLE `registereduser_has_address` (
  `id_has_address` int AUTO_INCREMENT PRIMARY KEY,
  `usernameRegisteredUser` varchar(45) NOT NULL,
  `nameAddress` varchar(45) NOT NULL,
  UNIQUE (`usernameRegisteredUser`,`nameAddress`),
  KEY `usernameRegisteredUser_HasAddress_idx` (`usernameRegisteredUser`),
  KEY `nameAddress_HasAddress_idx` (`nameAddress`),
  CONSTRAINT `usernameRegisteredUser_HasAddress` FOREIGN KEY (`usernameRegisteredUser`) REFERENCES `registereduser` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `nameAddress_HasAddress` FOREIGN KEY (`nameAddress`) REFERENCES `address` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
) 






