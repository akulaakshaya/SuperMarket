# SuperMarket Tables
drop table akReports;
drop table akStocks;
drop table akvendors;
drop table akproducts;


create table akproducts(
	prod_id  serial primary key ,
	prod_name varchar(20),
	category_name varchar(20) ,
    gst numeric(5,2),
    unit_price numeric(5,2)
);




create table akvendors(
Vendor_id int,
prod_id int references akproducts(prod_id),
vendor_name varchar(20),	
available int,
v_gst numeric(5,2),
    v_unit_price numeric(5,2),
primary key(Vendor_id,prod_id));


insert into akproducts(prod_name,category_name,gst,unit_price) values('Santoor','Soap',1.2,23);
insert into akproducts(prod_name,category_name,gst,unit_price) values('Meera','Shampoo',0.8,5);
insert into akproducts(prod_name,category_name,gst,unit_price) values('DairyMilk','Chocolate',2.3,75);
insert into akproducts(prod_name,category_name,gst,unit_price) values('Enchanteur','Perfume',4.3,200);

insert into  akvendors values(1,1,'Akshaya',100,0.9,21);
insert into  akvendors values(2,1,'Beulah',200,0.8,21);
insert into  akvendors values(2,3,'Beulah',75,1.9,74);
insert into  akvendors values(3,2,'Lakshmi',120,0.8,4.5);
insert into  akvendors values(4,4,'Sowjanya',90,4.4,195);


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
    S_date TimeStamp,
    total_price Numeric(7,3),
    quantity int,
    primary key(pid,vid,S_date),
    Foreign key(pid,vid) references akvendors(prod_id,Vendor_id)
);
select * from akproducts;
select * from akvendors;
select * from akstocks;
select * from akReports;
select * from akReports where   extract(day from S_date)=26 and extract(month from S_date)=4 and  extract(year from S_date)=2023
and extract(year from S_date)<=2023  and extract(day from S_date)<=26 and extract(month from S_date)<=4;

select sum(r.quantity*v.v_gst) from akvendors v,akReports r where r.S_type='Purchase' and v.prod_id=r.pid 
and v.Vendor_id=r.vid group by r.S_type='Purchase';
select sum(r.quantity*v.gst) from akproducts v,akReports r where r.S_type='Sales'
and v.prod_id=r.pid  group by r.S_type='Sales';



