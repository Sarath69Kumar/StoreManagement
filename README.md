# StoreManagement
 JEE project
 
 . Create Product
 . Update Product
 . Delete Product
 . User Login
 . Upload to Tomcat directory
 . Upload to DB
 . View the uploaded files
 . Download the files.
 
 
------
 
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


----
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

Product cart
Chat opption
Daily collection
Monthly collection
yearly collection
Graph view
Add property file to get menu
Employee details
Employee Attendance
Employee Debit, Credit
Dealers Details
Dealers creadit, Debit
Customer credit, debit
Pending supply
Delivered supply
etc...
