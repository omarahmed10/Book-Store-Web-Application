show processlist;
show status where `variable_name` = 'Threads_connected';
SELECT user,host,authentication_string FROM mysql.user;

update mysql.user set authentication_string='123' where user='ha';

delete from Users where User_Name = 'omar';
select * from Users ;
update Users set User_LastName = 'koko', User_FirstName = 'kaka' , User_email = 'ah@ah.ah' , User_address = 'ah' where User_Name = 'ah'; 
desc mysql.user;

delete from Book ;
select * from Book ;

delete from Publisher;

call add_new_book(
	'1',
    '1',
	'2009-04-09',
    60,
	4,
	10,
	'A',
	'A,B,C', 
    'Art');
    call add_new_book(
	'2',
    '2',
	'2009-04-09',
    60,
	4,
	10,
	'A',
	'A,B,C', 
    'Art');
    call add_new_book(
	'3',
    '3',
	'2009-04-09',
    60,
	4,
	10,
	'A',
	'A,B,C', 
    'Art');
    call add_new_book(
	'4',
    '4',
	'2009-04-09',
    60,
	4,
	10,
	'A',
	'A,B,C', 
    'Art');

call add_new_book(
	'8',
    '8',
	'2009-04-09',
    60,
	4,
	10,
	'D',
	'D,E,F',  
    'Art');
    call add_new_book(
	'7',
    '7',
	'2009-04-09',
    0,
	0,
	0,
	'D',
	'D,E,F', 
    'Art');
    call add_new_book(
	'9',
    '9',
	'2009-04-09',
    0,
	0,
	0,
	'D',
	'D,E,F',  
    'Art');
    call add_new_book(
	'10',
    '10',
	'2009-04-09',
    60,
	4,
	10,
	'D',
	'D,E,F', 
    'Religion');
        call add_new_book(
	'131',
    '123',
	'2009-04-09',
    0,
	0,
	0,
	'G',
	'G,U,I', 
    'Religion');
    
call Default_Book_Search ('10','10');
call Publisher_Book_Search('D');
call Category_Book_Search('Religion');
call Author_Book_Search('A');
call List_books();
call modify_book(
	'afsd',	
    'asfsadg',
    '2009-04-09',
    12,
	3,
    2,
    'Q'	,
	'TT,YY,uU',
    'Science');
    
REVOKE ALL ON *.* FROM 'omar'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'omar'@'localhost'
        WITH GRANT OPTION;
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'ah'@'localhost';

REVOKE ALL ON *.* FROM 'ah'@'localhost';


call get_User('omar');

call List_Users();

select count(*) As cnt from Orders;


select * from Cart;