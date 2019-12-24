package com.airefresco.app.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.airefresco.app.Model.Contact;
import com.airefresco.app.Model.Customer;
import com.airefresco.app.Model.Sucursal;
import com.airefresco.app.Security.ConnectionJDBC;
/**
 * @author Jefferson David Castañeda Carreño
 *@category class to access customers data
 */
@Repository
public class CustomerRepository  {
	private final String sqlGetAllCustomer="SELECT * FROM Cliente LEFT JOIN Contacto ON con_cliente_id=cli_nit LEFT JOIN Sucursal on suc_cliente=cli_nit LEFT JOIN Ciudad on suc_ciudad=ciu_id LEFT JOIN ciu_departamento=dep_id LEFT JOIN Pais on pai_id=dep_pais";
		
	@PreAuthorize("isAuthenticated()")
	public HashMap<Integer,Customer> getAll() throws SQLException{
		System.out.println("--------->Obteniendo todos los clientes?");
		HashMap <Integer,Customer> registro = new HashMap<>();
		Statement params=ConnectionJDBC.connect();
		//Recolect all data trought establishing connection to the data base
		if (params!=null){
			ResultSet rs=params.executeQuery(sqlGetAllCustomer);
			while (rs.next()){
				int nit = rs.getInt("nit");
				if (registro.containsKey(nit)) {
					Customer temp = registro.get(nit);
					Contact c = new Contact(rs.getInt("con_cedula"),rs.getString("con_nombres"),rs.getString("con_apellidos"),temp,rs.getString("con_email"),rs.getInt("con_celular"));
					temp.addContact(c);
					Sucursal s = new Sucursal(rs.getInt("suc_id"),rs.getString("suc_nombre"),rs.getString("suc_direccion"),rs.getInt("suc_cliente"),rs.getString("ciu_nombre"),rs.getString("pai_nombre"));
					temp.addSucursal(s);
				}else {
					Customer registry=new Customer(nit,rs.getString("nombre"),rs.getBoolean("activo"),rs.getDate("fecha_contratacion"));
					registry.addContact(new Contact(rs.getInt("cedula"),rs.getString("nombres"),rs.getString("apellidos"),registry,rs.getString("email"),rs.getInt("celular")));
					registro.put(nit, registry);
				}	
			}
		}
		
		return registro;
	}
	
}
