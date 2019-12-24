package com.airefresco.app.Model;


public class Contact {
	
	private int id;
	private String names;
	private String lastNames;
	private Customer customerNit;
	private String email;
	private int cel;
	private int tel;
	
		
	public Contact(int id, String contactName, String contactLname, Customer customerNit, String email,int contactCel) {
		this.id = id;
		this.names = contactName;
		this.lastNames=contactLname;
		this.customerNit = customerNit;
		this.email = email;
		this.cel=contactCel;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId( int id) {
		this.id = id;
	}
	
	public String getContactName() {
		return this.names;
	}
	
	public void setContactName(String contactName) {
		this.names = contactName;
	}
	
	public String getContactLname() {
		return this.lastNames;
	}
	
	public void setContactLname(String contactName) {
		this.lastNames = contactName;
	}
	
	public void setCustomerNit(Customer customerNit) {
		this.customerNit = customerNit;
	}
	
	public Customer getCustomerNit() {
		return this.customerNit;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getCel() {
		return cel;
	}

	public void setCel(int cel) {
		this.cel = cel;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public Contact() {
		
	}

}
