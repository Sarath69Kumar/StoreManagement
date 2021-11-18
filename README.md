# StoreManagement
 JEE project
 
 ![image](https://user-images.githubusercontent.com/59145492/119766270-5c8baf80-bed2-11eb-8f05-345b72f54422.png)

 
 . Product List View
 
 ![image](https://user-images.githubusercontent.com/59145492/119766356-7af1ab00-bed2-11eb-9b15-25648489371c.png)

 
 . Create Product
 
 ![image](https://user-images.githubusercontent.com/59145492/119766384-8a70f400-bed2-11eb-951f-516077629d2f.png)

 
 . Update Product
 
 ![image](https://user-images.githubusercontent.com/59145492/119766417-98bf1000-bed2-11eb-9746-d01385d77504.png)
 
 . Delete Product
 
 
 
 . User Login
 
 ![image](https://user-images.githubusercontent.com/59145492/119766466-af656700-bed2-11eb-83f1-08774afcdc2e.png)

 ![image](https://user-images.githubusercontent.com/59145492/119766547-d6bc3400-bed2-11eb-82bd-b26163ebd405.png)

 
 . Upload to Tomcat directory
 
 ![image](https://user-images.githubusercontent.com/59145492/119766562-dde34200-bed2-11eb-9a72-d4ea21b27cdb.png)

 
 . Upload to DB
 
 ![image](https://user-images.githubusercontent.com/59145492/119766570-e3d92300-bed2-11eb-9ee3-5d1a77b2570e.png)

 
 . View the uploaded files
 
 
 
 . Download the files.
 
 ![image](https://user-images.githubusercontent.com/59145492/119766647-0703d280-bed3-11eb-9e40-b7a513f75973.png)

 
 . Redirecting from OLD url to New url
 
 . HTTPS
 
 
------

Technical info

Used java 1.8, webapp v3.1, tomcat v.8, mysql 8.

-----
 
Here I used MySql Database to connect

create 3 tables under the DB you use

-------
create table USER_ACCOUNT

(

USER_ID BIGINT not null,

USER_NAME VARCHAR(30) not null,

GENDER    VARCHAR(1) not null,

PASSWORD  VARCHAR(30) not null,

IMAGE_DATA LONGBLOB not null,

IMAGE_FILE_NAME VARCHAR(30) not null,

primary key (USER_ID)

);


-------
-- Create table

create table PRODUCT

(

CODE  VARCHAR(20) not null,

NAME  VARCHAR(128) not null,

PRICE FLOAT not null,

primary key (CODE)

) ;


-----

create table ATTACHMENT

(

USER_ID     BIGINT not null,

FILE_ID	  BIGINT not null,

FILE_NAME   VARCHAR(50) not null,

FILE_DATA   BLOB not null,

DESCRIPTION VARCHAR(255),

FOREIGN KEY (USER_ID) REFERENCES USER_ACCOUNT(USER_ID)

) ;


#On Development

Product cart,
Chat opption,
Daily collection,
Monthly collection,
yearly collection,
Graph view,
Add property file to get menu,
Employee details,
Employee Attendance,
Employee Debit, Credit,
Dealers Details,
Dealers creadit, Debit,
Customer credit, debit,
Pending supply,
Delivered supply,
etc...
