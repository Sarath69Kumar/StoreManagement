package org.sm.beans;

public class UserAccountBean
{
	public static final String Gender_Male = "M";
	public static final String Gender_Female = "F";
	
	private Long userId;
	private String userName;
	private String password;
	private String gender;
	private byte[] imageData;
	private String imageFileName;
	
	
	public UserAccountBean()
	{
		System.out.println("User Account");
	}
	
	public UserAccountBean(Long userId, String userName, byte[] imageData, String imageFileName)
	{
		this.userId = userId;
		this.userName = userName;
		this.imageData = imageData;
		this.imageFileName = imageFileName;
	}
	
	public Long getUserId()
	{
		return userId;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	public byte[] getImageData()
	{
		return imageData;
	}
	public void setImageData(byte[] imageData)
	{
		this.imageData = imageData;
	}
	
	public String getImageFileName()
	{
		return imageFileName;
	}
	public void setImageFileName(String imageFileName)
	{
		this.imageFileName = imageFileName;
	}
	
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}


/*
 * 
 * 
show databases;
CREATE DATABASE store_management;
use store_management;


create table USER_ACCOUNT
(
USER_NAME VARCHAR(30) not null,
GENDER    VARCHAR(1) not null,
PASSWORD  VARCHAR(30) not null,
primary key (USER_NAME)
);

ALTER TABLE USER_ACCOUNT ADD USER_ID BIGINT not null;
ALTER TABLE USER_ACCOUNT ADD IMAGE_DATA LONGBLOB not null;
ALTER TABLE USER_ACCOUNT ADD IMAGE_FILE_NAME VARCHAR(30) not null;



insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('tom', 'M', 'tom001');
 
insert into user_account (USER_NAME, GENDER, PASSWORD)
values ('jerry', 'M', 'jerry001');
 


select * from user_account;

 * 
 * 
 */


