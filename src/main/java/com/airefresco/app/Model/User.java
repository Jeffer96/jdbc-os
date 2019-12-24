
package com.airefresco.app.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.ToString;


@Entity
@Table(name="Usuario")
@ToString(exclude = "pass")
@Data
public class User {
	
	@Id
	private int cedula;
	@NotBlank
	private String nombres;
	@NotBlank
	private String apellidos;
	@NotBlank
	private String nick;
	@NotBlank 
	private String pass;
	@NotBlank
	private String roleName;
	private String email;
	private boolean activo;
	private Date creado;
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public User() {
		
	}
	
	public User(int id, String userName, String lname, String nickName, String userPass, String roleName, String email, boolean activo,Date creado) {
		this.cedula = id;
		this.pass = secure(userPass);
		this.nick = nickName;
		this.nombres = userName;
		this.apellidos = lname;
		this.roleName = roleName;
		this.email = email;
		this.activo=activo;
		this.setCreado(creado);
	}
	
	
	protected String secure(String param) {
		//return param;
		return new BCryptPasswordEncoder().encode(param);
	}
	
	public User(String nickName, String userPass) {
		this.nick = nickName;
		this.pass = secure(userPass);
	}
	
	public void setNombres(String names) {
		this.nombres = names;
	}
	
	public String getNombres() {
		return this.nombres;
	}
	
	public String getApellidos() {
		return this.apellidos;
	}
	
	public void setApellidos(String lnames) {
		this.apellidos = lnames;
	}
	
	public String getPass(){
		return this.pass;
	}
	
	public void setPass(String userPass) {
		this.pass = secure(userPass);
	}
	
	public int getCedula() {
		return this.cedula;
	}
	
	public void setCedula(int id) {
		this.cedula = id;
	}
	
	public void setNick(String nickName) {
		this.nick = nickName;
	}
	
	public String getNick() {
		return this.nick;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(String rol) {
		this.roleName = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreado() {
		return creado;
	}

	public void setCreado(Date creado) {
		this.creado = creado;
	}
	
}