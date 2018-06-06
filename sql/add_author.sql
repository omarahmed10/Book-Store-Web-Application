DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_Author`(ISBN varchar(20), Authors Varchar(255))
BEGIN
DECLARE Remainder TEXT; 
DECLARE Delimiter CHAR(1); 
DECLARE Pos INT DEFAULT 1 ; 
DECLARE Str VARCHAR(1000); 

SET Delimiter = ','; 
SET Remainder = Authors; 

WHILE CHAR_LENGTH(Remainder) > 0 AND Pos > 0 DO 
SET Pos = INSTR(Remainder, `Delimiter`); 
IF Pos = 0 THEN 
SET Str = Remainder; 
ELSE 
SET Str = LEFT(Remainder, Pos - 1); 
END IF; 
IF TRIM(Str) != '' THEN 
insert ignore into Authors values(str , ISBN);
END IF; 

SET Remainder = SUBSTRING(Remainder, Pos + 1); 
END WHILE;
END$$
DELIMITER ;

