use storehouse;

SET FOREIGN_KEY_CHECKS = 0;

drop table if exists `user`;
create table user
(
userId int not null auto_increment primary key,
username varchar(20) not null unique,
userpwd varchar(20) not null,
usertype int not null
);
insert into user values(null,'admin','admin',1);
insert into user values(null,'1','1',2);
insert into user values(null,'2','2',3);

drop table if exists `custom`;
CREATE TABLE custom
(
  Cno char(4) PRIMARY KEY NOT NULL UNIQUE ,
  Cname VARCHAR(10) NOT NULL ,
  Csex ENUM('男','女') DEFAULT '男',
  Caddr VARCHAR(20),
  Ctel CHAR(11)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

# ALTER TABLE custom DROP Csex;
# ALTER TABLE custom ADD Csex ENUM('男','女') DEFAULT ('男');
# ALTER TABLE custom MODIFY Csex char(2) CHECK (Csex in ('男','女'));

drop table if exists `supplier`;
CREATE TABLE supplier
(
  Sno char(4) PRIMARY KEY NOT NULL UNIQUE ,
  Sname VARCHAR(10) not NULL ,
  Saddr VARCHAR(20),
  Stel char(11)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

drop table if exists `warehouse`;
CREATE TABLE warehouse
(
  Wno char(2) PRIMARY KEY UNIQUE NOT NULL ,
  Wname VARCHAR(10) NOT NULL ,
  Wsize int not NULL ,
  Wu_size int not null default 0,
  Wa_size int not null ,
  Waddr VARCHAR(20)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

drop table if exists `type`;
CREATE TABLE type
(
  Tno char(3) PRIMARY KEY NOT NULL UNIQUE ,
  Tname VARCHAR(10) NOT NULL ,
  Tdes VARCHAR(50)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

drop table if exists `goods`;
CREATE TABLE goods
(
  Gno CHAR(8) PRIMARY KEY NOT NULL UNIQUE ,
  Gname VARCHAR(10) NOT NULL ,
  Tno char(3),
  Tname varchar(10),
  Sno char(6),
  Dura_date int not null,
  FOREIGN KEY (Tno) REFERENCES type(Tno),
  FOREIGN KEY (Sno) REFERENCES supplier(Sno)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

drop table if exists `store`;
CREATE TABLE store
(
  SID int not null auto_increment unique primary key,
  Gno char(8),
  Gname varchar(20),
  Tno char(3),
  Tname varchar(10),
  Wno char(2),
  Wname varchar(10),
  Gnum INT,
  Prod_date date,
  Out_date date,  
  foreign key (Tno) references type(Tno),
  FOREIGN KEY (Gno) REFERENCES goods(Gno),
  FOREIGN KEY (Wno) REFERENCES warehouse(Wno)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

drop table if exists `Input`;
create TABLE Input
(
  Gno char(8),
  Gname VARCHAR(10),
  Tno CHAR(3),
  Tname varchar(10),
  Sno CHAR(4),
  Wno char(2),
  Wname varchar(10),
  Inum int,
  Idate date,
  FOREIGN KEY (Gno) REFERENCES goods(Gno),
  FOREIGN KEY (Sno) REFERENCES supplier(Sno),
  FOREIGN KEY (Tno) REFERENCES type(Tno),
  FOREIGN KEY (Wno) REFERENCES warehouse(Wno)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

drop table if exists `Output`;
CREATE TABLE Output
(
  SID int,
  Gno char(8),
  Gname VARCHAR(10),
  Tno CHAR(3),
  Tname varchar(10),
  Wno char(2),
  Cno char(4),
  Cname varchar(10),
  Onum int,
  Odate date,
  foreign key (SID) references store (SID),
  FOREIGN KEY (Gno) REFERENCES goods(Gno),
  FOREIGN KEY (Cno) REFERENCES custom(Cno),
  FOREIGN KEY (Tno) REFERENCES type(Tno),
  FOREIGN KEY (Wno) REFERENCES warehouse(Wno)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

drop table if exists `OutOfDate`;
create table OutOfDate
(
  SID int not null,
  Gno char(8),
  Gname varchar(20),
  Tno char(3),
  Tname varchar(10),
  Wno char(2),
  Wname varchar(10),
  Gnum INT,
  Prod_date date,
  Out_date date
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

drop trigger if exists U_Space ;
delimiter //
create trigger U_Space after insert on store for each row
 begin
	update warehouse set Wu_size = Wu_size + new.Gnum
		where Wno= new.Wno;
end //

drop trigger if exists A_Space ;
delimiter //
create trigger A_Space after insert on store for each row
 begin
	update warehouse set Wa_size = Wa_size - new.Gnum
		where Wno= new.Wno;
end //


drop trigger if exists O_D_Goods ;
delimiter //
create trigger O_D_Goods before insert on store for each row
 begin
	set new.Out_date = date_add(new.Prod_date,interval (select Dura_date from goods where Gno = new.Gno) day);
 end //

drop trigger if exists Input_tri ;
delimiter //
create trigger Input_tri after insert on Input for each row
 begin
	insert into store values(null,new.Gno,new.Gname,new.Tno,new.Tname,new.Wno,new.Wname,new.Inum,new.Idate,null);
end //

drop trigger if exists Output_tri ;
delimiter //
create trigger Output_tri after insert on Output for each row
 begin
	 update store set Gnum = Gnum - new.Onum where Gno= new.Gno and Wno = new.Wno and SID = new.SID;
	if(select Gnum from store where Gno= new.Gno and Wno = new.Wno and SID = new.SID )<=0 then
	 delete from store where Gno= new.Gno and Wno = new.Wno and SID = new.SID;
	end if;
end //

drop trigger if exists Update_house ;
delimiter //
create trigger Update_house after update on warehouse for each row
 begin
	 update store set Wname = new.Wname where Wno= new.Wno;
     update Input set Wname = new.Wname where Wno= new.Wno;
     update OutOfDate set Wname = new.Wname where Wno= new.Wno;
end //

drop trigger if exists Update_custom ;
delimiter //
create trigger Update_custom after update on custom for each row
 begin
	 update Output set Cname = new.Cname where Cno= new.Cno;
end //

-- insert into Output values('24', 'G0000001', '酸奶', '001', '奶制品', '01', '0000', '1234', '1', curdate());
-- insert into Input values('




INSERT INTO custom VALUES ('0000','1234','男','上海','12345678901');
INSERT into custom values ('0001','张三','男','安徽宣城','13333333333');
INSERT into custom values ('0002','李四','男','河南郑州','13112345678');
INSERT into custom values ('0003','王五','男','北京','13287654321');
INSERT into custom values ('0004','孙二娘','女','南京','18898765123');
INSERT into custom values ('0005','扈三娘','女','四川阿坝','13400001111');
INSERT into custom values ('0006','宋江','男','四川成都','13901928111');
INSERT into custom values ('0007','彼得','男','美国休斯顿','13000001234');
INSERT into custom values ('0008','丽丽','女','英国伦敦','18776543210');

INSERT into supplier VALUES ('S001','旺旺','北京','40062211001');
INSERT into supplier VALUES ('S002','康师傅','台湾台北','40062212345');
INSERT into supplier VALUES ('S003','蒙牛','内蒙古锡林郭勒','40062254321');
INSERT into supplier VALUES ('S004','伊利','内蒙古呼和浩特','40060001010');
INSERT into supplier VALUES ('S005','可口可乐','美国休斯顿','23323300111');
INSERT into supplier VALUES ('S006','三只松鼠','安徽芜湖','40069902313');
INSERT into supplier VALUES ('S007','良品铺子','南京','40223265432');
INSERT into supplier VALUES ('S008','卡旺卡','安徽合肥','40112301234');
INSERT into supplier VALUES ('S009','双汇','北京','60060012345');
INSERT into supplier VALUES ('S010','特仑苏','天津','10010098765');
INSERT into supplier VALUES ('S011','海天','天津','12345678910');

INSERT into warehouse VALUES ('01','一号存储',50000,0,50000,'安徽合肥');
INSERT into warehouse VALUES ('02','二号存储',10000,0,10000,'安徽芜湖');
INSERT into warehouse VALUES ('03','三号存储',3000,0,3000,'安徽宣城');
INSERT into warehouse VALUES ('04','四号存储',50000,0,50000,'南京');
INSERT into warehouse VALUES ('05','五号存储',2000,0,2000,'合工大');

INSERT into type VALUES ('001','奶制品',null);
INSERT into type VALUES ('002','豆制品',null);
INSERT into type VALUES ('003','饮品',null);
INSERT into type VALUES ('004','饼干',null);
INSERT into type VALUES ('005','桶面',null);
INSERT into type VALUES ('006','肉类加工品',null);
INSERT into type VALUES ('007','坚果',null);
INSERT into type VALUES ('008','水果',null);
INSERT into type VALUES ('009','糖果',null);
INSERT into type VALUES ('010','罐头',null);
INSERT into type VALUES ('011','调味品',null);
INSERT into type VALUES ('012','面包主食',null);
set sql_safe_updates=0;
UPDATE type SET Tname='方便面' WHERE Tname='桶面';
set sql_safe_updates=1;

INSERT into goods VALUES ('G0000001','酸奶','001','奶制品','S003',5);
INSERT into goods VALUES ('G0000002','老坛酸菜牛肉面','005','桶面','S002',365);
INSERT into goods VALUES ('G0000003','干脆面','005','桶面','S002',365);
INSERT into goods VALUES ('G0000004','肉松面包','012','面包主食','S007',5);
INSERT into goods VALUES ('G0000005','老抽酱油','011','调味品','S011',365);
INSERT into goods VALUES ('G0000006','镇江醋','011','调味品','S011',365);
INSERT into goods VALUES ('G0000007','玉米热狗肠','006','肉类加工品','S009',180);
INSERT into goods VALUES ('G0000008','核桃','007','坚果','S006',365);
INSERT into goods VALUES ('G0000009','芒果干','008','水果','S007',30);
INSERT into goods VALUES ('G0000010','阿尔卑斯棒棒糖','009','糖果','S007',180);
INSERT into goods VALUES ('G0000011','三味鱼罐头','010','罐头','S011',180);
INSERT into goods VALUES ('G0000012','雪碧','003','饮品','S005',180);
INSERT into goods VALUES ('G0000013','可乐','003','饮品','S005',180);
INSERT into goods VALUES ('G0000014','真巧饼干','004','饼干','S007',365);
INSERT into goods VALUES ('G0000015','维维豆奶','002','豆制品','S003',180);
INSERT into goods VALUES ('G0000016','香辣牛肉面','005','桶面','S002',90);
INSERT into goods VALUES ('G0000017','蜂蜜柚子茶','003','饮品','S008',1);
INSERT into goods VALUES ('G0000018','全套奶茶','003','饮品','S008',1);
INSERT into goods VALUES ('G0000019','芒果冰','003','饮品','S008',1);
INSERT into goods VALUES ('G0000020','苹果','008','水果','S006',15);
INSERT INTO goods VALUES ('G0000021','纯牛奶','001','奶制品','S004',7);


INSERT INTO store VALUES (null,'G0000001','酸奶','001','奶制品','01','一号存储',200,curdate(),null);
INSERT INTO store VALUES (null,'G0000001','酸奶','001','奶制品','02','二号存储',1000,curdate(),null);
INSERT INTO store VALUES (null,'G0000002','老坛酸菜牛肉面','005','方便面','03','三号存储',100,curdate(),null);
INSERT INTO store VALUES (null,'G0000002','老坛酸菜牛肉面','005','方便面','04','四号存储',150,curdate(),null);
INSERT INTO store VALUES (null,'G0000003','干脆面','005','方便面','05','五号存储',30,curdate(),null);
INSERT INTO store VALUES (null,'G0000004','肉松面包','012','面包主食','01','一号存储',99,curdate(),null);
INSERT INTO store VALUES (null,'G0000005','老抽酱油','011','调味品','02','二号存储',132,curdate(),null);
INSERT INTO store VALUES (null,'G0000006','镇江醋','011','调味品','03','三号存储',564,curdate(),null);
INSERT INTO store VALUES (null,'G0000007','玉米热狗肠','006','肉类加工品','04','四号存储',230,curdate(),null);
INSERT INTO store VALUES (null,'G0000008','核桃','007','坚果','05','五号存储',972,curdate(),null);
INSERT INTO store VALUES (null,'G0000009','芒果干','008','水果','01','一号存储',137,curdate(),null);
INSERT INTO store VALUES (null,'G0000010','阿尔卑斯棒棒糖','009','糖果','02','二号存储',10,curdate(),null);
INSERT INTO store VALUES (null,'G0000011','三味鱼罐头','010','罐头','03','三号存储',21,curdate(),null);
INSERT INTO store VALUES (null,'G0000012','雪碧','003','饮品','04','四号存储',33,curdate(),null);
INSERT INTO store VALUES (null,'G0000013','可乐','003','饮品','05','五号存储',41,curdate(),null);
INSERT INTO store VALUES (null,'G0000014','真巧饼干','004','饼干','01','一号存储',424,curdate(),null);
INSERT INTO store VALUES (null,'G0000015','维维豆奶','002','豆制品','02','二号存储',1023,curdate(),null);
INSERT INTO store VALUES (null,'G0000016','香辣牛肉面','005','方便面','03','三号存储',123,curdate(),null);
INSERT INTO store VALUES (null,'G0000017','蜂蜜柚子茶','003','饮品','04','四号存储',534,curdate(),null);
INSERT INTO store VALUES (null,'G0000018','全套奶茶','003','饮品','05','五号存储',665,curdate(),null);
INSERT INTO store VALUES (null,'G0000019','芒果冰','003','饮品','01','一号存储',139,curdate(),null);
INSERT INTO store VALUES (null,'G0000020','苹果','008','水果','02','二号存储',188,curdate(),null);
INSERT INTO store VALUES (null,'G0000021','纯牛奶','001','奶制品','03','三号存储',299,curdate(),null);
INSERT INTO store VALUES (null,'G0000001','酸奶','001','奶制品','01','一号存储',10,curdate(),null);

-- select Gno,Gname,Tno,Tname,Wno,sum(Gnum) from store group by Gno,Gname,Tno,Tname,Wno;
