use vehicle;

create table info(token_no int   auto_increment ,v_no varchar(20),
name varchar(255),phno varchar(20),date varchar(20),v_type enum('car','bike'),address varchar(255),
occupancy enum('in','out'),
primary key(token_no)
);

ALTER TABLE info AUTO_INCREMENT=1;

create table in_info(token_no int auto_increment,intoken_no int,
primary key(token_no),
foreign key (intoken_no) references vehicle.info(token_no)
);

ALTER TABLE out_info AUTO_INCREMENT=1;

create table out_info(token_no int auto_increment,intoken_no int,tot_hours int,
primary key(token_no),
foreign key (intoken_no) references vehicle.info(token_no)
);

insert into info(v_no,name,phno,date,v_type,address,occupancy) values('tn784567','hari','98745ew123','789','car','whdhqhwdg','in');

drop table in_info;
drop table out_info;

select * from info;

select *  from in_info;

select * from out_info;

truncate table info;

select token_no from info where v_no="tn459874";

select v_no from  info where occupancy='in';

select intoken_no from in_info;

select date from info where token_no=3;

update info set occupancy='out' where token_no=3;

delete from in_info where intoken_no=3;

insert into out_info(intoken_no,tot_hours) values(3,24);