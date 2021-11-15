CREATE USER 'admin'@'localhost' identified by 'password';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE jaspreetFlourMill;

USE jaspreetFlourMill; 
DROP TABLE IF EXISTS employees;
CREATE TABLE employees(
employeeId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
contactNumber VARCHAR(10),
address VARCHAR(100),
jobDesignation VARCHAR(50),
dob VARCHAR(25)
);
