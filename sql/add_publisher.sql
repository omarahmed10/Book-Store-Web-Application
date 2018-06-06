DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_publisher`(
	Publisher_name varchar(45),
	Publisher_address varchar(45),
	Publisher_phone varchar(45))
BEGIN
insert into BookStore_Schema.Publisher(PName,PAddress,Phone) values (
	Publisher_name ,
	Publisher_address ,
	Publisher_phone );
END$$
DELIMITER ;

