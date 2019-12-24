package com.airefresco.app.Model;

public class Sucursal {

	private int id;
	private String nombre;
	private String direccion;
	private int cliente;
	private String ciudad;
	private String pais;
	
	public Sucursal(int id,String nombre,String direccion,int cliente,String ciudad,String pais) {
		this.id=id;
		this.nombre=nombre;
		this.direccion=direccion;
		this.cliente=cliente;
		this.ciudad=ciudad;		
		this.pais=pais;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
}
