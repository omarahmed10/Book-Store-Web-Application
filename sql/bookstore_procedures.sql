    
-- -----------------------------------------------------
-- Procedure add_new_book
-- -----------------------------------------------------
drop procedure if exists add_new_book;
DELIMITER $$
CREATE PROCEDURE add_new_book(
	ISBN varchar(20),
    Title varchar(100),
	Pub_year Date,
    Price int,
	Threshold int,
	Copies_nums int,
	Publisher_name varchar(45),
	Authors varchar(255),
    category  varchar(10)
 )
BEGIN

if exists (select * from Book as B where B.ISBN = ISBN and B.Title = Title) then
SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'This Book already exists.';
end if;
#####	Trying to insert new Publisher if not exist with null data.
insert ignore into Publisher(PName) value (Publisher_name);
insert into Book values (
	ISBN,
    Title,
    Pub_year,
    Price,
    Copies_nums,
    Threshold,
    (select PID from Publisher where PName = Publisher_name ),
    category);
#####	inserting Autthors 
CALL add_Author(ISBN, Title, Authors);
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure add_publisher
-- -----------------------------------------------------
drop procedure if exists add_publisher;
DELIMITER $$
CREATE PROCEDURE add_publisher(
	Publisher_name varchar(45),
	Publisher_address varchar(45),
	Publisher_phone varchar(45))
BEGIN
insert into Publisher(PName,PAddress,Phone) values (
	Publisher_name ,
	Publisher_address ,
	Publisher_phone );
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure add_Author
-- -----------------------------------------------------
drop procedure if exists add_Author;
DELIMITER $$
CREATE PROCEDURE add_Author(
ISBN varchar(20),
Title Varchar(100),
Authors Varchar(255))
BEGIN
DECLARE Remainder TEXT; 
DECLARE Delimiter CHAR(1); 
DECLARE Pos INT DEFAULT 1 ; 
DECLARE Str VARCHAR(1000); 

SET Delimiter = ','; 
SET Remainder = Authors; 

WHILE CHAR_LENGTH(Remainder) > 0 AND Pos > 0 DO 
SET Pos = INSTR(Remainder, Delimiter); 
IF Pos = 0 THEN 
SET Str = Remainder; 
ELSE 
SET Str = LEFT(Remainder, Pos - 1); 
END IF; 

IF TRIM(Str) != '' THEN
insert ignore into Authors values(str, ISBN, Title);
END IF;

SET Remainder = SUBSTRING(Remainder, Pos + 1); 
END WHILE;
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure Check Book quantity
-- -----------------------------------------------------
drop procedure if exists Check_Book_Quantity;
DELIMITER $$
CREATE Procedure Check_Book_Quantity (Book_ISBN varchar(20),
									Book_Title varchar(100),
                                    Book_Quantity int)
BEGIN
select Copies_number from Book where Book_ISBN = ISBN and Book_Title = Title into @copies;
select (@copies >= Book_Quantity) as valid;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure modify existing books
-- -----------------------------------------------------
drop procedure if exists modify_book;
DELIMITER $$
CREATE PROCEDURE modify_book(
	Book_ISBN varchar(20),
	Book_Title varchar(100),
    new_Pub_year Date,
    new_Price int,
	new_Threshold int,
    new_quantity int, 
	new_Publisher_name varchar(45),
	new_Authors varchar(255),
    new_category  varchar(10))
BEGIN
### 	deleting old data to remove Authors
delete from Book where ISBN = Book_ISBN and Title = Book_Title;
CALL add_new_book(
	Book_ISBN,
    Book_Title,
	new_Pub_year,
    new_Price,
	new_Threshold,
	new_quantity,
	new_Publisher_name,
	new_Authors,
    new_category);
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure confirm order
-- -----------------------------------------------------
drop procedure if exists confirm_Order;
DELIMITER $$
CREATE PROCEDURE confirm_Order(
ISBN varchar(20),Title varchar(100))
BEGIN
delete from Orders where Book_ISBN = ISBN and Book_Title = Title;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure List Orders
-- -----------------------------------------------------
drop procedure if exists List_Orders;
DELIMITER $$
CREATE Procedure List_Orders ()
BEGIN
select * from Orders;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure get Order
-- -----------------------------------------------------
drop procedure if exists get_Order;
DELIMITER $$
CREATE Procedure get_Order (ISBN varchar(20),Title varchar(100))
BEGIN
select *
from Orders 
where Book_ISBN = ISBN and Book_Title = Title;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure List Books
-- -----------------------------------------------------
drop procedure if exists List_Books;
DELIMITER $$
CREATE Procedure List_Books ()
BEGIN
Select ISBN,Title,PName,Author,Publish_year,Price,Copies_number,Threshold,Category
from (Book As B join Authors As A on B.ISBN = A.Book_ISBN and B.Title = A.Book_Title)
	natural join Publisher;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure List Users
-- -----------------------------------------------------
drop procedure if exists List_Users;
DELIMITER $$
CREATE Procedure List_Users ()
BEGIN
Select User_email,User_Name,User_FirstName,User_LastName,User_address,User_phoneNumber,User_Role
from Users natural join Users_Role;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure get User
-- -----------------------------------------------------
drop procedure if exists get_User;
DELIMITER $$
CREATE Procedure get_User (userN varchar(100))
BEGIN
Select User_email,User_Name,User_Password,User_FirstName,User_LastName,User_address,User_phoneNumber,User_Role
from Users natural join Users_Role 
where User_Name = userN;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure Default Book Seacrh
-- -----------------------------------------------------
drop procedure if exists Default_Book_Search;
DELIMITER $$
CREATE Procedure Default_Book_Search (Book_ISBN varchar(20),Book_Title varchar(100))
BEGIN
Select ISBN,Title,PName,Author,Publish_year,Price,Copies_number,Threshold,Category
from (Book As B join Authors As A on B.ISBN = A.Book_ISBN and B.Title = A.Book_Title)
	natural join Publisher
where ISBN = Book_ISBN and Title = Book_Title;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure Title Book Seacrh
-- -----------------------------------------------------
drop procedure if exists Title_Book_Search;
DELIMITER $$
CREATE Procedure Title_Book_Search (Book_Title varchar(100))
BEGIN
Select ISBN,Title,PName,Author,Publish_year,Price,Copies_number,Threshold,Category
from (Book As B join Authors As A on B.ISBN = A.Book_ISBN and B.Title = A.Book_Title)
	natural join Publisher
where Title = Book_Title;
END $$
DELIMITER ;


-- -----------------------------------------------------
-- Procedure Book Seacrh by Publisher
-- -----------------------------------------------------
drop procedure if exists Publisher_Book_Search;
DELIMITER $$
CREATE Procedure Publisher_Book_Search (Publisher_name varchar(45))
BEGIN
Select ISBN,Title,PName,Author,Publish_year,Price,Copies_number,Threshold,Category
from (Book As B join Authors As A on B.ISBN = A.Book_ISBN and B.Title = A.Book_Title)
	natural join Publisher
where PID in (select PID from Publisher where PName = Publisher_name);
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure Book Seacrh by Category
-- -----------------------------------------------------
drop procedure if exists Category_Book_Search;
DELIMITER $$
CREATE Procedure Category_Book_Search (in_category varchar(10))
BEGIN
Select ISBN,Title,PName,Author,Publish_year,Price,Copies_number,Threshold,Category
from (Book As B join Authors As A on B.ISBN = A.Book_ISBN and B.Title = A.Book_Title)
	natural join Publisher
where Category = in_category;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure Book Seacrh by Author
-- -----------------------------------------------------
drop procedure if exists Author_Book_Search;
DELIMITER $$
CREATE Procedure Author_Book_Search (Author_name varchar(45))
BEGIN
Select ISBN,Title,PName,Author,Publish_year,Price,Copies_number,Threshold,Category
from (Book As B join Authors As A on B.ISBN = A.Book_ISBN and B.Title = A.Book_Title)
	natural join Publisher
where Author = Author_name;
END $$
DELIMITER ;


-- -----------------------------------------------------
-- Procedure Add to Cart
-- -----------------------------------------------------
drop procedure if exists Add_To_Cart;
DELIMITER $$
CREATE Procedure Add_To_Cart (Book_ISBN VARCHAR(20),
    Book_Title VARCHAR(100) ,
    User_Name VARCHAR(100) ,
    Book_Count INT)
BEGIN
	insert into Cart values (Book_ISBN,
    Book_Title ,
    User_Name ,
    Book_Count );
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure Add to Cart
-- -----------------------------------------------------
drop procedure if exists Get_User_Cart;
DELIMITER $$
CREATE Procedure Get_User_Cart (in_User_Name VARCHAR(100))
BEGIN
	select Book_ISBN,Book_Title,Book_Count  
    from Cart
    where User_Name = in_User_Name;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure delete element from Cart
-- -----------------------------------------------------
drop procedure if exists Delete_Element_Cart;
DELIMITER $$
CREATE Procedure Delete_Element_Cart (in_Book_ISBN VARCHAR(20),
    in_Book_Title VARCHAR(100) ,in_User_Name VARCHAR(100))
BEGIN
	delete from Cart
    where  User_Name = in_User_Name and Book_ISBN = in_Book_ISBN and Book_Title = in_Book_Title;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure delete element from Cart
-- -----------------------------------------------------
drop procedure if exists Delete_User_Cart;
DELIMITER $$
CREATE Procedure Delete_User_Cart (in_User_Name VARCHAR(100))
BEGIN
	delete from Cart
    where  User_Name = in_User_Name;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure Get Book quantity
-- -----------------------------------------------------
drop procedure if exists Get_Book_Quantity;
DELIMITER $$
CREATE Procedure Get_Book_Quantity (Book_ISBN varchar(20),
									Book_Title varchar(100))
BEGIN
select Copies_number from Book where Book_ISBN = ISBN and Book_Title = Title;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure modify existing books
-- -----------------------------------------------------
drop procedure if exists buy_Book;
DELIMITER $$
CREATE PROCEDURE buy_Book(
	Book_ISBN varchar(20),
	Book_Title varchar(100),
    User_Name varchar(100),
    new_quantity int)
BEGIN
update Book set Copies_number = Copies_number - new_quantity where ISBN = Book_ISBN and Title = Book_Title;
insert into Sales values(Book_ISBN,Book_Title,CURRENT_DATE(),User_Name,new_quantity);
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure total sales for books in the previous month
-- -----------------------------------------------------
drop procedure if exists Total_Sales_previous_month;
DELIMITER $$
CREATE Procedure Total_Sales_previous_month ()
BEGIN
	SELECT * FROM Sales WHERE Sale_Date BETWEEN (CURRENT_DATE()- INTERVAL 2 MONTH) AND CURRENT_DATE();
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure The top 5 customers in the last three months
-- -----------------------------------------------------
drop procedure if exists Top_5_Customers;
DELIMITER $$
CREATE Procedure Top_5_Customers ()
BEGIN
	select tab.User_Name, SUM(tab.Paid) as Total_Paid
	FROM (
		select Sales.*,(Book_count * Price) as Paid
		from Sales, Book
		where  Sales.Book_ISBN = Book.ISBN AND Sales.Book_Title = Book.Title 
		) as tab
	WHERE tab.Sale_Date BETWEEN (CURRENT_DATE()- INTERVAL 3 MONTH) AND CURRENT_DATE()
	group by tab.User_name
	order by Total_Paid DESC
	limit 5;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Procedure The top 10 selling books for the last three months
-- -----------------------------------------------------
drop procedure if exists Top_10_Books;
DELIMITER $$
CREATE Procedure Top_10_Books ()
BEGIN
	SELECT Book_ISBN, Book_Title , count(*) as Total_Count 
	from Sales 
	WHERE Sale_Date BETWEEN (CURRENT_DATE()- INTERVAL 3 MONTH) AND CURRENT_DATE()
	group by Book_ISBN, Book_Title
	order by Total_Count DESC
	LIMIT 10;
END $$
DELIMITER ;


-- -----------------------------------------------------
-- Trigger before update book
-- -----------------------------------------------------
drop trigger if exists Book_BEFORE_UPDATE;
DELIMITER $$
CREATE TRIGGER Book_BEFORE_UPDATE BEFORE UPDATE ON Book FOR EACH ROW
BEGIN
if new.Copies_number < 0 then
SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'number of Copies must be > 0.';
end if;
END $$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger after update book
-- -----------------------------------------------------
drop trigger if exists Book_AFTER_UPDATE;
DELIMITER $$
CREATE TRIGGER Book_AFTER_UPDATE AFTER UPDATE ON Book FOR EACH ROW
BEGIN
if new.Copies_number < new.Threshold then
insert into Orders values ((new.Threshold - new.Copies_number), new.ISBN, new.Title) 
	ON DUPLICATE KEY UPDATE Quantity = (new.Threshold - new.Copies_number);
end if;
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger after insert book
-- -----------------------------------------------------
drop trigger if exists Book_AFTER_INSERT;
DELIMITER $$
CREATE TRIGGER Book_AFTER_INSERT AFTER INSERT ON Book FOR EACH ROW
BEGIN
if new.Copies_number < new.Threshold then
insert into Orders values ((new.Threshold - new.Copies_number), new.ISBN, new.Title) 
	ON DUPLICATE KEY UPDATE Quantity = (new.Threshold - new.Copies_number);
end if;
END$$
DELIMITER ;
-- -----------------------------------------------------
-- Trigger before Delete Orders
-- -----------------------------------------------------
drop trigger if exists Orders_Before_Delete;
DELIMITER $$
CREATE TRIGGER Orders_Before_Delete before DELETE ON Orders FOR EACH ROW
BEGIN
update Book set Copies_number = old.Quantity + Copies_number 
	where ISBN = old.Book_ISBN and Title = old.Book_Title;
END$$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger Before Insert Book
-- -----------------------------------------------------
drop trigger if exists Book_BEFORE_INSERT;
DELIMITER $$
CREATE TRIGGER Book_BEFORE_INSERT before Insert ON Book FOR EACH ROW
BEGIN
if not exists (select * from Category_T where new.Category = Category) then
SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Category not found';
end if;
if new.Copies_number <= 0 then
SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'number of Copies must be > 0.';
end if;
END$$
DELIMITER ;