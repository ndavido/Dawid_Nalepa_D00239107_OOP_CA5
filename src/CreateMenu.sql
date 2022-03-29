DROP DATABASE IF EXISTS `oop_ca4`;
CREATE DATABASE `oop_ca4`;
USE `oop_ca4`;
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                        `MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
                        `NAME` varchar(50) NOT NULL,
                        `DISH_SIZE` varchar(50) NOT NULL,
                        `QUANTITY` INT(20) NOT NULL,
                        `PRICE` DOUBLE (20,2) NOT NULL,
                        PRIMARY KEY  (`MENU_ID`)
);

INSERT INTO menu VALUES (null, "Mushroom Soup", "Medium",1, 15.99),
                        (null, "Spaghetti","Small",2,18.50),
                        (null, "Mashed Potatoes","Large",1,20.99),
                        (null, "Cranberry Sorbet","Medium",1,9.99),
                        (null, "Warm Ice-Cream","Large",3,14.99),
                        (null, "Lasagna","Small",1,17.99),
                        (null, "Caesar Salad","Small",1,8.99),
                        (null, "Chicken Tika Masala","Medium",1,15.99),
                        (null, "Smoked Salmon","Small",1,25.99),
                        (null,"Steak","Large",1,59.99);