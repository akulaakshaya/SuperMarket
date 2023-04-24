# SuperMarket Tables
create table products(
	prod_id serial primary key,
	prod_name varchar(20),
	category_name varchar(20)	
);
create table vendors(
Vendor_id int,
vendor_name varchar(20),
	prod_id int references products(prod_id),
available int,
primary key(Vendor_id,prod_id));
insert into products values(1,'Santoor','Soap');
insert into products values(2,'Meera','Shampoo');
select * from products;
insert into  vendors values(1,'Akshaya',1,100);
insert into  vendors values(2,'Beulah',1,200);
select * from vendors;
































































create table Master_Entry(products varchar(20) , prod_category varchar(20),open_stock int, vendor varchar(20),primary key(products,vendor));
insert into Master_Entry values('Apples','Fruits',2000,'Alex')
insert into Master_Entry values('Apples','Fruits',3000,'Robin')
insert into Master_Entry values('Grapes','Fruits',2000,'Alex')
insert into Master_Entry values('Santoor','Soap',5000,'Robin')
insert into Master_Entry values('Margo','Soap',3000,'Tim')
insert into Master_Entry values('Colgate','Toothpaste',2000,'Tim')
insert into Master_Entry values('Sensodyne','Toothpaste',4000,'Sam')
insert into Master_Entry values('Meera','Shampoo',6000,'Sam')
insert into Master_Entry values('Tresseme','Shampoo',5000,'Alex')
insert into Master_Entry values('Loreal','Shampoo',1000,'Robin')

drop table Stocks
create table Stocks(products varchar(20) , prod_category varchar(20),stock int, vendor varchar(20),purchased_date date,primary key(products,vendor))

insert into Stocks values('Apples','Fruits',1000,'Alex','12-01-2022')
insert into Stocks values('Grapes','Fruits',500,'Robin','10-02-2022')
insert into Stocks values('Santoor','Soap',100,'Robin','10-24-2022')
insert into Stocks values('Margo','Soap',700,'Tim','03-07-2023')
insert into Stocks values('Colgate','Toothpaste',200,'Tim','03-31-2023')
insert into Stocks values('Sensodyne','Toothpaste',400,'Sam','02-22-2023')
insert into Stocks values('Meera','Shampoo',600,'Sam','04-17-2023')
insert into Stocks values('Tresseme','Shampoo',500,'Alex','11-19-2022')

create table Sales(products varchar(20) , prod_category varchar(20),sales int, vendor varchar(20),sold_date date,primary key(products,vendor))

insert into Sales values('Apples','Fruits',100,'Alex','12-25-2022')
insert into Sales values('Grapes','Fruits',50,'Robin','11-16-2022')
insert into Sales values('Santoor','Soap',10,'Robin','01-08-2023')

insert into Sales values('Colgate','Toothpaste',20,'Tim','04-23-2023')








insert into Sales values('Meera','Shampoo',60,'Sam','04-20-2023')











































