# SuperMarket Tables
drop table akproducts;
create table akproducts(
	prod_id  int ,
	prod_name varchar(20),
	category_name varchar(20)    
);

drop table akvendors;


create table akvendors(
Vendor_id int,
prod_id int ,
vendor_name varchar(20),	
available int,
primary key(Vendor_id,prod_id));

insert into akproducts values(1,'Santoor','Soap');
insert into akproducts values(2,'Meera','Shampoo');
select * from akproducts;
insert into  akvendors values(1,1,'Akshaya',100);
insert into  akvendors values(2,1,'Beulah',200);
select * from akvendors;
drop table akStocks;
create table akStocks(
pid int,
    vid int,
    stockCount int,
    Foreign key(pid,vid) references akvendors(prod_id,Vendor_id)
);
create table akReports(
    pid int,
    vid int,
    S_type varchar(20),
    S_date date,
    primary key(pid,vid,S_date),
    Foreign key(pid,vid) references akvendors(prod_id,Vendor_id)
);









