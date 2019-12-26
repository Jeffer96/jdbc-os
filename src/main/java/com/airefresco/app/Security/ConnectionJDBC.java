package com.airefresco.app.Security;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.airefresco.app.Components.ImsException;

@Component
public class ConnectionJDBC {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	private String dataBase;
	private String userApp;
	private String userPass;
	private Connection con;
	private Statement st;
	
	@Autowired
	public ConnectionJDBC(@Value("${spring.datasource.url}") String db, @Value("${spring.datasource.username}") String usr, @Value("${spring.datasource.password}") String pwd) {
		this.dataBase = db;
		this.userApp = usr;
		this.userPass = pwd;
	}
	
	/**
	 * @author Jefferson David Castañeda Carreño
	 * @param none
	 * @return Connection
	 */
	//@PreAuthorize("isAuthenticated()")
	private final void getConnection() throws ImsException{
		if (dataBase != null) {
			try {
				//Define the driver for the connection
				Class.forName(JDBC_DRIVER).newInstance();
				//Get the connection
				try {
					if (con==null) {
						con = DriverManager.getConnection(dataBase,userApp,userPass);
					}else {
						if (con.isClosed()) {
							con = DriverManager.getConnection(dataBase,userApp,userPass);
						}
					}
					st = con.createStatement();
				}catch (SQLException e) {
					System.out.println("		500. Error en la conexión: "+e.getSQLState()+" // "+e.getMessage());
				}
			}catch(Exception e) {
				System.out.println("	400. Error en el driver: "+e);
			}
		}else {
			throw new ImsException("No se puede acceder al recurso url de la base de datos",300,"SQL");
		}
	} 
	
	//@PreAuthorize("isAuthenticated()")	
	public Statement connect() throws ImsException{
		getConnection();
		return st;
	}
	
	@PreAuthorize("isAuthenticated()")
	public boolean closeConn() {
		boolean ans=false;
		try {
			con.close();
			ans=con.isClosed();
		}catch (Exception e) {
			System.out.println("Error 403: la base de datos denegó la conexion");
		}
		return ans;
	}
	

}
