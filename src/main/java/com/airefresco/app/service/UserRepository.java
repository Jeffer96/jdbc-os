package com.airefresco.app.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airefresco.app.Model.User;
import com.airefresco.app.Security.ConnectionJDBC;

@Service
public class UserRepository {
	 private static final String getUserById = "SELECT * FROM Usuario WHERE cedula=";
	 private static final String getUserByNick = "SELECT * FROM Usuario WHERE nick='";
	 private static final String getUsers = "SELECT * FROM Usuario WHERE activo=1";
	 private static final String getUserByRole = "SELECT * FROM Usuario WHERE role_name='";
	@Autowired
	protected ConnectionJDBC con;
	
	//@Query(value="INSERT INTO Usuario (cedula,nombres,apellidos,email,fechan_nacimiento,role_name,activo,nick,pass,creado) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery=true)
	public void saveUser(int cedula,String nombres,String apellidos,String email,String fecha1,String rol,boolean activo,String nick,String pass,Date fecha2) {
		
	}
	
	private Statement access() throws Exception{
		return this.con.connect();
	}
		
	//@Query(value = "SELECT * FROM Usuario WHERE activo = true and nick = ?1", nativeQuery = true)
	public User findUserByNickName(String nickName) {
		User ans = null;
		try {
			Statement params=this.access();
			ResultSet rs=params.executeQuery(getUserByNick+nickName+"'");
			//System.out.println(getUserByNick+nickName+"';");
			while (rs.next()){
				ans = new User(rs.getInt("cedula"),
								rs.getString("nombres"),
								rs.getString("apellidos"),
								rs.getString("nick"),
								rs.getString("pass"),
								rs.getString("role_name"),
								rs.getString("email"),
								rs.getBoolean("activo"),
								rs.getDate("creado"));
			}
		}catch(Exception e) {
			System.out.println("=============GET USER BY NICK=============\n		"+e);
		}
		return ans;
	}	
	
	//@Query(value = "SELECT * FROM Usuario WHERE activo = true and cedula = ?1", nativeQuery = true)
	public User findUserById(int id) {
		User ans = null;
		try {
			Statement params=this.access();
			ResultSet rs=params.executeQuery(getUserById+id);
			while (rs.next()){
				ans = new User(rs.getInt("cedula"),
								rs.getString("nombres"),
								rs.getString("apellidos"),
								rs.getString("nick"),
								rs.getString("pass"),
								rs.getString("role_name"),
								rs.getString("email"),
								rs.getBoolean("activo"),
								rs.getDate("creado"));
			}
		}catch(Exception e) {
			System.out.println("=============GET USER BY ID=============\n		"+e);
		}
		return ans;
	}
	
	//@Query(value = "SELECT * FROM Usuario", nativeQuery = true)
	public Collection<User> getAllUser(){
		Collection<User> ans = new ArrayList<>();
		try {
			Statement params=this.access();
			ResultSet rs=params.executeQuery(getUsers);
			while (rs.next()){
				ans.add(new User(rs.getInt("cedula"),
								rs.getString("nombres"),
								rs.getString("apellidos"),
								rs.getString("nick"),
								rs.getString("pass"),
								rs.getString("role_name"),
								rs.getString("email"),
								rs.getBoolean("activo"),
								rs.getDate("creado")
							));
			}
		}catch(Exception e) {
			System.out.println("=============GET USERS=============\n		"+e);
		}
		return ans;
	}
	
	//@Query(value = "SELECT * FROM Usuario ORDER BY cedula DESC", nativeQuery = true)
	public Collection<User> getAllUsersOrderedById(String ascDesc){
		Collection<User> ans = new ArrayList<>();
		try {
			Statement params=this.access();
			ResultSet rs=params.executeQuery(getUsers+" ORDER BY cedula "+ascDesc);
			while (rs.next()){
				ans.add(new User(rs.getInt("cedula"),
								rs.getString("nombres"),
								rs.getString("apellidos"),
								rs.getString("nick"),
								rs.getString("pass"),
								rs.getString("role_name"),
								rs.getString("email"),
								rs.getBoolean("activo"),
								rs.getDate("creado")
							));
			}
		}catch(Exception e) {
			System.out.println("=============GET USERS ORDERED=============\n		"+e);
		}
		return ans;
	}
	
	//@Query(value = "SELECT * FROM Usuario ORDER BY nombres", nativeQuery = true)
	public Collection<User> getAllUsersOrderedByName(String ascDesc){
		Collection<User> ans = new ArrayList<>();
		try {
			Statement params=this.access();
			ResultSet rs=params.executeQuery(getUsers+" ORDER BY nombres,apellidos "+ascDesc);
			while (rs.next()){
				ans.add(new User(rs.getInt("cedula"),
								rs.getString("nombres"),
								rs.getString("apellidos"),
								rs.getString("nick"),
								rs.getString("pass"),
								rs.getString("role_name"),
								rs.getString("email"),
								rs.getBoolean("activo"),
								rs.getDate("creado")
							));
			}
		}catch(Exception e) {
			System.out.println("=============GET USERS ORDERED BY 1=============\n		"+e);
		}
		return ans;
	}
	
	//@Query(value = "SELECT * FROM Usuario ORDER BY nick", nativeQuery = true)
	public Collection<User> getAllUsersOrderedByNick(String ascDesc){
		Collection<User> ans = new ArrayList<>();
		try {
			Statement params=this.access();
			ResultSet rs=params.executeQuery(getUsers+" ORDER BY nick "+ascDesc);
			while (rs.next()){
				ans.add(new User(rs.getInt("cedula"),
								rs.getString("nombres"),
								rs.getString("apellidos"),
								rs.getString("nick"),
								rs.getString("pass"),
								rs.getString("role_name"),
								rs.getString("email"),
								rs.getBoolean("activo"),
								rs.getDate("creado")
							));
			}
		}catch(Exception e) {
			System.out.println("=============GET USERS ORDERED BY 2=============\n		"+e);
		}
		return ans;
	}
	
	//@Query(value = "SELECT * FROM Usuario WHERE role_name =?1", nativeQuery = true)
	public Collection<User> getUserByRol(String roleName){
		Collection<User> ans = new ArrayList<>();
		try {
			Statement params=this.access();
			ResultSet rs=params.executeQuery(getUserByRole+roleName+"'");
			while (rs.next()){
				ans.add(new User(rs.getInt("cedula"),
								rs.getString("nombres"),
								rs.getString("apellidos"),
								rs.getString("nick"),
								rs.getString("pass"),
								rs.getString("role_name"),
								rs.getString("email"),
								rs.getBoolean("activo"),
								rs.getDate("creado")
							));
			}
		}catch(Exception e) {
			System.out.println("=============GET BY ROL=============\n		"+e);
		}
		return ans;
	}
		
	//@Modifying
	//@Transactional
	//@Query(value = "UPDATE Usuario u SET nombres = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	public boolean updateUserName(String newName, int id) {
		//Statement params=ConnectionJDBC.connect();
		return false;
	}
	
	//@Modifying
	//@Transactional
	//@Query(value = "UPDATE Usuario u SET nick = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	public boolean updateUserNick(String nickName, int id) {
		//Statement params=ConnectionJDBC.connect();
				return false;
	}
	
	//@Modifying
	//@Transactional
	//@Query(value = "UPDATE Usuario u SET cedula = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	public void updateUserId(int newid, int id) {
		//Statement params=ConnectionJDBC.connect();
	}
	
	//@Modifying
	//@Transactional
	//@Query(value = "UPDATE Usuario u SET email = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	public boolean updateUserEmail(String email, int id) {
		//Statement params=ConnectionJDBC.connect();
				return false;
	}
	
	//@Modifying
	//@Transactional
	//@Query(value = "UPDATE Usuario u SET role_name = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	public void updateAuthority(String rol, int id) {
		//Statement params=ConnectionJDBC.connect();
	}
	
	//@Modifying
	//@Transactional
	//@Query(value = "UPDATE Usuario u SET pass = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	public boolean updatePass(String pass, int id) {
		//Statement params=ConnectionJDBC.connect();
		return false;
	}
}
