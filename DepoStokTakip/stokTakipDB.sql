create schema StokTakip;
create table user(
id int primary key AUTO_INCREMENT, 
name varchar(45) ,
email varchar(45) not null unique ,
password varchar (45),
created_at date default(curdate()),
updated_at date default(curdate())
);

create table store(
id int primary key AUTO_INCREMENT, 
user_id int ,
name varchar(45) ,
address varchar(45) ,
description varchar (45),
created_at date default(curdate()),
deletable boolean default(1),
FOREIGN KEY(user_id) references user(id)
);

create table category(

id int primary key AUTO_INCREMENT, 
user_id int ,
name varchar(45) ,
description varchar (45),
created_at date default(curdate()),
deletable boolean default(1),
FOREIGN KEY(user_id) references user(id)

);

create table item(

id int primary key AUTO_INCREMENT, 
user_id int ,
store_id int not null default(1),
category_id int not null default(1),
name varchar(45) ,
description varchar (45),
stock int,
price double,
created_at date default(curdate()),
updated_at date default(curdate()),
FOREIGN KEY(user_id) references user(id),
FOREIGN KEY(store_id) references store(id),
FOREIGN KEY(category_id) references category(id)
);




select * from user;
select * from store;
select * from category;
select * from item;

insert into User (name,email,password)
 values("destek","destek@java.com.tr","xxxxxxxx");

insert into  store(user_id,name,address,description,deletable)
 values(1,"default","default","default store",false);
 
 insert into  category(user_id,name,description,deletable)
 values(1,"default","default category",false);
 
  insert into  item(user_id,name,description,stock,price)
 values(1,"java","java item",10,20.0);

