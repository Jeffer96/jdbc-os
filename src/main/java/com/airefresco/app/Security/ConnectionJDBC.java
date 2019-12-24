package com.airefresco.app.Security;

import java.sql.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;

public class ConnectionJDBC {
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; 
		
	@Value("${spring.datasource.url}")
	private static String dataBase;
	
	@Value("${spring.datasource.username}")
	private static String userApp;
	
	@Value("${spring.datasource.password}")
	private static String userPass;
	
	private static Connection con;
	private static Statement st;
	
	/**
	 * @author Jefferson David Castañeda Carreño
	 * @param none
	 * @return Connection
	 */
	@PreAuthorize("isAuthenticated()")
	private static final void getConnection(){
		try {
			//Define the driver for the connection
			Class.forName(JDBC_DRIVER);
			//Get the connection
			if (con.isClosed() || con==null) {
				con = DriverManager.getConnection(dataBase,userApp,userPass);
				st = con.createStatement();
			}
		}catch(Exception e) {
			System.out.println("Error 403: la base de datos denegó la conexion");
		}
	} 
	
	@PreAuthorize("isAuthenticated()")	
	public static Statement connect(){
		ConnectionJDBC.getConnection();
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
