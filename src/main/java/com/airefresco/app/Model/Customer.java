package com.airefresco.app.Model;

import java.util.Date;
import java.util.HashMap;

public class Customer {
	
	private int nit;
	private String customerName;
	private boolean statusActive;
	private Date startContract;
	private Date endContract;
	private HashMap<Integer,Contact> contacts;
	private HashMap<Integer,Sucursal> sucursals;
	
	public Customer() {
		
	}
	
	public Customer(int nit, String nombre, boolean active, Date startContract) {
		this.nit = nit;
		this.customerName = nombre;
		this.statusActive = active;
		this.startContract = startContract;
	}
	
	public void setNit(int nit) {
		this.nit = nit;
	}
	
	public int getNit() {
		return this.nit;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setStatusActive(boolean active) {
		this.statusActive = active;
	}
	
	public boolean getStatusActive() {
		return this.statusActive;
	}
	
	public void setStartContract(Date startContract) {
		this.startContract = startContract;
	}
	
	public Date getStartContract() {
		return this.startContract;
	}

	public Date getEndContract() {
		return endContract;
	}

	public void setEndContract(Date endContract) {
		this.endContract = endContract;
	}
	
	public HashMap<Integer,Contact> getContacts(){
		return this.contacts;
	}
	
	public void setContacts(HashMap<Integer,Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addContact(Contact c) {
		if (this.contacts==null) {
			this.contacts = new HashMap<Integer,Contact>();
		}
		if (!this.sucursals.containsKey(c.getId())) {
			this.contacts.put(c.getId(), c);
		}
	}
	
	public HashMap<Integer,Sucursal> getSucursals() {
		return sucursals;
	}

	public void setSucursals(HashMap<Integer,Sucursal> sucursals) {
		this.sucursals = sucursals;
	}
	
	public void addSucursal(Sucursal s) {
		if (this.sucursals==null) {
			this.sucursals = new HashMap<Integer,Sucursal>();
		}
		if (!this.sucursals.containsKey(s.getId())) {
			this.sucursals.put(s.getId(), s);
		}
	}
}
