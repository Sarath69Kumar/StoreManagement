package org.sm.beans;

public class Product 
{
	private String code;
	private String name;
	private float price;
	
	public Product()
	{
		System.out.println("Product");
	}
	
	public Product(String code, String name, float price)
	{
		this.code = code;
		this.name = name;
		this.price = price;
		System.out.println("Product with param: code = "+ code + "  name = "+name+ "  price = "+ price );
	}
	
	public String getCode()
	{
		return code;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public float getPrice()
	{
		return price;
	}
	public void setPrice(float price)
	{
		this.price = price;
	}
}

/*
 * 
 * 
-- Create table
create table PRODUCT
(
CODE  VARCHAR(20) not null,
NAME  VARCHAR(128) not null,
PRICE FLOAT not null,
primary key (CODE)
) ;


insert into product (CODE, NAME, PRICE)
values ('P001', 'Java Core', 100);
 
insert into product (CODE, NAME, PRICE)
values ('P002', 'C# Core', 90);
 * 
 * 
 */
