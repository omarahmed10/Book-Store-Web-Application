#drop schema BookStore;

create schema if not exists BookStore;
USE BookStore;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS=0;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET SQL_SAFE_UPDATES = 0;

-- -----------------------------------------------------
-- Table Publisher
-- -----------------------------------------------------
DROP TABLE IF EXISTS Publisher ;

CREATE TABLE IF NOT EXISTS Publisher (
    PID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    PName VARCHAR(45) NOT NULL UNIQUE,
    PAddress VARCHAR(45) NULL,
    Phone VARCHAR(45) NULL
);

-- -----------------------------------------------------
-- Table Book
-- -----------------------------------------------------
# ('Science', 'Art', 'Religion', 'History', 'Geography')
DROP TABLE IF EXISTS Book;
CREATE TABLE IF NOT EXISTS Book (
    ISBN VARCHAR(20) NOT NULL,
    Title VARCHAR(100) NOT NULL,
    Publish_year DATE,
    Price INT,
    Copies_number INT,
    Threshold INT NOT NULL,
    PID INT,
    Category Varchar(10),
    CONSTRAINT Book_PK PRIMARY KEY (ISBN , Title),
    INDEX fk_Book_Publisher1_idx (PID ASC),
    CONSTRAINT fk_Book_Publisher1 FOREIGN KEY (PID)
        REFERENCES Publisher (PID)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table Category
-- -----------------------------------------------------
#DROP TABLE IF EXISTS Category;

#CREATE TABLE IF NOT EXISTS Category (
#  CTitle VARCHAR(100) primary key,
#  Category ENUM('Science', 'Art', 'Religion', 'History', 'Geography') NOT NULL);

#Alter Table Book ADD CONSTRAINT fk_Book_Category FOREIGN KEY (Title) REFERENCES Category (CTitle)
#    ON DELETE set null ON UPDATE CASCADE;
create table if not exists Category_T (
	Category Varchar (10)
);
-- -----------------------------------------------------
-- Table Authors
-- -----------------------------------------------------
DROP TABLE IF EXISTS Authors ;

CREATE TABLE IF NOT EXISTS Authors (
    Author VARCHAR(45) NOT NULL,
    Book_ISBN VARCHAR(20) NOT NULL,
    Book_Title VARCHAR(100) NOT NULL,
    PRIMARY KEY (Author , Book_ISBN , Book_Title),
    INDEX fk_Authors_Book1_idx (Book_ISBN ASC , Book_Title ASC),
    CONSTRAINT fk_Authors_Book1 FOREIGN KEY (Book_ISBN , Book_Title)
        REFERENCES Book (ISBN , Title)
        ON DELETE CASCADE ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table Order
-- -----------------------------------------------------
DROP TABLE IF EXISTS Orders ;

CREATE TABLE IF NOT EXISTS Orders (
    Quantity INT NULL,
    Book_ISBN VARCHAR(20) NOT NULL,
    Book_Title VARCHAR(100) NOT NULL,
    PRIMARY KEY (Book_ISBN , Book_Title),
    CONSTRAINT fk_Order_Book1 FOREIGN KEY (Book_ISBN , Book_Title)
        REFERENCES Book (ISBN , Title)
        ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Users;

CREATE TABLE IF NOT EXISTS Users (
	User_email varchar(100),
	User_Name varchar(100) primary key,
	User_LastName varchar(100),
	User_FirstName varchar(100),
	User_address varchar(100),
	User_phoneNumber varchar(100));
