CREATE DEFINER=`root`@`localhost` PROCEDURE `add_new_book`(
	ISBN varchar(20),
    Title varchar(100),
	Pub_year Date,
    Price int,
	Threshold int,
	Copies_nums int,
	Publisher_name varchar(45),
	Authors varchar(255), #How to use the list.
						  #SET @Authors = '\'auth1\',\'auth2\'....';
						  #SET @sql = CONCAT('SELECT * FROM Author WHERE Name IN (', Authors, ')');
						  #PREPARE stmt FROM @sql;
						  #EXECUTE stmt;
						  #DEALLOCATE PREPARE stmt;
    category  ENUM('Science', 'Art', 'Religion', 'History', 'Geography')
 )
BEGIN
insert ignore into Category values (
	Title,
    category);
CALL add_Author(ISBN, Authors);
insert into Book values (
	ISBN,
    Pub_year,
    Price,
    Copies_nums,
    Threshold,
    (select ID from Publisher where Publisher_name = PName),
    Title);

END
