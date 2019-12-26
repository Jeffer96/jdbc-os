package com.airefresco.app.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.airefresco.app.Components.ImsException;
import com.airefresco.app.Model.Contact;
import com.airefresco.app.Model.Customer;
import com.airefresco.app.Model.Sucursal;
import com.airefresco.app.Security.ConnectionJDBC;
/**
 * @author Jefferson David Castañeda Carreño
 *@category class to access customers data
 */
@Service
public class CustomerRepository  {
	private final String sqlGetAllCustomer="SELECT * FROM Cliente LEFT JOIN Contacto ON con_cliente_id=cli_nit LEFT JOIN Sucursal ON suc_cliente=cli_nit LEFT JOIN Ciudad ON suc_ciudad=ciu_id LEFT JOIN Departamento ON ciu_departamento=dep_id LEFT JOIN Pais ON pai_id=dep_pais";
	//private final String sqlGetAllCustomer="SELECT * FROM Usuario";
	
	@Autowired
	protected ConnectionJDBC con;
	
	private Statement access() throws Exception{
		return this.con.connect();
	}
	
	@PreAuthorize("isAuthenticated()")
	public HashMap<Integer,Customer> getAll() throws ImsException{
		System.out.println("--------->1. Obteniendo todos los clientes?");
		HashMap <Integer,Customer> registro = new HashMap<>();
		//Recolect all data trought establishing connection to the data base
		try{
			Statement params=this.access();
			ResultSet rs=params.executeQuery(sqlGetAllCustomer);
			System.out.println("--------->2. Query ejecutado...");
			int count = 0;
			while (rs.next()){
				System.out.println("--------->"+count+". Query ejecutado...");
				count+=1;
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
		}catch (Exception e) {
			throw new ImsException("Error en la obtencion del listado",300,""+e.getCause());
		}
		
		return registro;
	}
	
}
