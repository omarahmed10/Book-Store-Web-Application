use  BookStore;
create user 'omar'@'localhost' identified by 'omarico';
GRANT SELECT ON BookStore.Book TO 'omar'@'localhost' IDENTIFIED BY 'omarico';
GRANT update ON BookStore.Users TO 'omar'@'localhost' IDENTIFIED BY 'omarico';
flush privileges;
delete from Book;

#('Science', 'Art', 'Religion', 'History', 'Geography')
insert into Category_T values ('Geography');
insert into Category_T values ('History');
insert into Category_T values ('Religion');
insert into Category_T values ('Art');
insert into Category_T values ('Science');

call add_publisher('hamda' , 'asldfasd' , '36446');
call add_publisher('hamda2' , 'asldfasd2' , '364462');
call add_publisher('hamda3' , 'asldfasd' , '36446');

call add_new_book(
	'123456',
    'Title1',
	'2009-05-09',
    50,
	3,
	5,
	'hamda',
	'omar,mohamed', 
    'Geography');
    
call add_new_book(
	'123458',
    'Title1',
	'2009-07-09',
    60,
	4,
	10,
	'hamda2',
	'omar,mohamed,Aboelhamd', 
    'Geography');

    
call add_new_book(
	'123459',
    'Title2',
	'2009-03-09',
    60,
	4,
	10,
	'hamda',
	'omar,mohamed,Aboelhamd', 
    'Art');
    
call add_new_book(
	'123460',
    'Title3',
	'2009-04-09',
    60,
	4,
	10,
	'hamda2',
	'omar2,mohamed2,Aboelhamd2', 
    'Art');
    
call add_new_book(
	'123470',
    'Title7',
	'2009-04-09',
    60,
	4,
	10,
	'hamda3',
	'omar2,mohamed2,Aboelhamd2', 
    'Art22');
    
call add_new_book(
	'123470',
    'Title7',
	'2009-04-09',
    60,
	4,
	10,
	'hamda3',
	'omar23,mohamed23,Aboelhamd23', 
    'Art22');
    
call modify_book(
	'123460',
    'Title3',
    8);
call modify_book(
	'123460',
    'Title3',
    4);
call modify_book(
	'123460',
    'Title3',
    2);
call modify_book(
	'123460',
    'Title3',
    -8);

call confirm_Order(
	'123460',
    'Title3');

call Default_Book_Search('123460', 'Title3');

call Category_Book_Search ('Art');

call Author_Book_Search('Aboelhamd');

call Publisher_Book_Search ('hamda');