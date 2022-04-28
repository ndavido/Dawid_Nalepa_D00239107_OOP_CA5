# Restaurant Order Menu 
```
Object Oriented Programming
CA4
	
Name		: Dawid Nalepa
Student ID	: D00239107
Class Group	: SD2b
```
```sql
DROP DATABASE IF EXISTS `oop_ca4`;
CREATE DATABASE `oop_ca4`;
USE `oop_ca4`;
DROP TABLE IF EXISTS `menu`;
//Created table for menu orders
CREATE TABLE `menu` (
                        `MENU_ID` int(11) NOT NULL,
                        `NAME` varchar(50) NOT NULL,
                        `DISH_SIZE` varchar(50) NOT NULL,
                        `QUANTITY` INT(20) NOT NULL,
                        `PRICE` DOUBLE (20,2) NOT NULL
                        )ENGINE=InnoDB DEFAULT CHARSET=utf8;
                        
//Insterted values for menu orders table
INSERT INTO menu VALUES (1, "Mushroom Soup", "Medium",1, 15.99),
                        (2, "Spaghetti","Small",2,18.50),
                        (3, "Mashed Potatoes","Large",1,20.99),
                        (4, "Cranberry Sorbet","Medium",1,9.99),
                        (5, "Warm Ice-Cream","Large",3,14.99),
                        (6, "Lasagna","Small",1,17.99),
                        (7, "Caesar Salad","Small",1,8.99),
                        (8, "Chicken Tika Masala","Medium",3,15.99),
                        (9, "Smoked Salmon","Small",1,25.99),
                        (10,"Steak","Large",1,59.99);

ALTER TABLE `menu` ADD PRIMARY KEY (`MENU_ID`);
ALTER TABLE `menu` MODIFY `MENU_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
```
![alt text](https://github.com/ndavido/Dawid_Nalepa_D00239107_OOP_CA5/raw/main/READMEimages/Feature_18_OOP_CA4.PNG "Idea")
