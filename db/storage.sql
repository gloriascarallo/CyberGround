DROP DATABASE IF EXISTS storage;
CREATE DATABASE storage;
USE storage;




CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);



CREATE TABLE `registereduser` (
  `idUser` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telephoneNumber` char(10) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE(`username`),
  KEY `idUser_idx` (`idUser`),
  CONSTRAINT `idUser_RegistratedUser` FOREIGN KEY (`idUser`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);



CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE(`username`)
 
);



CREATE TABLE `cart` (
  `idCart` int NOT NULL,
  PRIMARY KEY (`idCart`),
  CONSTRAINT `idCart_Cart` FOREIGN KEY (`idCart`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);


CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT 0,
   `discountPercentage` DECIMAL(5, 2) DEFAULT NULL,
   `dateExpirationDiscount` date DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,  
  `dateUpload` date DEFAULT CURRENT_TIMESTAMP,
  `supplier` varchar(45) DEFAULT NULL,
  `categoryName` varchar(45) DEFAULT NULL,
  `imagePath` varchar(100) DEFAULT NULL,
  `quantityAvailable` int DEFAULT 1,
  PRIMARY KEY (`id`)

) ;

CREATE TABLE `product_situatedin_cart` (
  `id_SituatedIn` int AUTO_INCREMENT PRIMARY KEY,
  `idCart` int NOT NULL,
  `idProduct` int NOT NULL,
  `dateAdded` date DEFAULT CURRENT_TIMESTAMP,
  `quantity` int NOT NULL DEFAULT 1,
  UNIQUE (`idProduct`,`idCart`),
  KEY `idCart_idx` (`idCart`),
  KEY `idProduct_idx` (`idProduct`),
  CONSTRAINT `idCart_SituatedIn` FOREIGN KEY (`idCart`) REFERENCES `cart` (`idCart`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `idProduct_SituatedIn` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);






CREATE TABLE `orders` (
  `idOrder` int AUTO_INCREMENT PRIMARY KEY,
  `datePurchase` date DEFAULT CURRENT_TIMESTAMP,
  `dateDelivery` date DEFAULT NULL,
  `dateShipping` date DEFAULT NULL,
  `idCart` int NOT NULL,
  KEY `idCart_idx` (`idCart`),
  CONSTRAINT `idCart_Order` FOREIGN KEY (`idCart`) REFERENCES `cart` (`idCart`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `product_in_order` (
  `id_product_in_order` int AUTO_INCREMENT PRIMARY KEY,
  `idOrder` int NOT NULL,
  `idProduct` int NOT NULL,
  `quantity` int DEFAULT 1,
  `price` double DEFAULT 0,
  KEY `idOrder_idx` (`idOrder`),
  KEY `idProduct_idx` (`idProduct`),
  CONSTRAINT `idOrder_in_order` FOREIGN KEY (`idOrder`) REFERENCES `orders` (`idOrder`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `idProduct_in_order` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);




CREATE TABLE `method_payment` (
  `pan` char(19) NOT NULL,
  `expirationDate` char(5) NOT NULL,
  `cvc` varchar(4) NOT NULL,
  PRIMARY KEY (`pan`)
  
);




CREATE TABLE `registereduser_has_method_payment` (

  `id_has_method_payment` int AUTO_INCREMENT PRIMARY KEY,
  `idRegisteredUser` int NOT NULL,
  `panMethodPayment` char(19) NOT NULL,
  `expirationDateMethodPayment` char(5) NOT NULL,
  `cvcMethodPayment` varchar(4) NOT NULL,
  UNIQUE (`idRegisteredUser`,`panMethodPayment`),
  KEY `idRegisteredUser_HasMethodPayment_idx` (`idRegisteredUser`),
  KEY `pan_HasMethodPayment_idx` (`panMethodPayment`),
 CONSTRAINT `idRegisteredUser_HasMethodPayment` FOREIGN KEY (`idRegisteredUser`) REFERENCES `registereduser` (`idUser`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `pan_HasMethodPayment` FOREIGN KEY (`panMethodPayment`) REFERENCES `method_payment` (`pan`) ON UPDATE CASCADE
);




CREATE TABLE `address` (
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`name`)
);



CREATE TABLE `registereduser_has_address` (
  `id_has_address` int AUTO_INCREMENT PRIMARY KEY,
  `idRegisteredUser` int NOT NULL,
  `nameAddress` varchar(45) NOT NULL,
  UNIQUE (`idRegisteredUser`,`nameAddress`),
  KEY `idRegisteredUser_HasAddress_idx` (`idRegisteredUser`),
  KEY `nameAddress_HasAddress_idx` (`nameAddress`),
  CONSTRAINT `idRegisteredUser_HasAddress` FOREIGN KEY (`idRegisteredUser`) REFERENCES `registereduser` (`idUser`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `nameAddress_HasAddress` FOREIGN KEY (`nameAddress`) REFERENCES `address` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
);



