package com.airefresco.app.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.airefresco.app.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
	
	@Query(value="INSERT INTO Usuario (cedula,nombres,apellidos,email,fechan_nacimiento,role_name,activo,nick,pass,creado) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery=true)
	void saveUser(int cedula,String nombres,String apellidos,String email,String fecha1,String rol,boolean activo,String nick,String pass,Date fecha2);
	
	@Query(value = "SELECT * FROM Usuario WHERE activo = true and nick = ?1", nativeQuery = true)
	User findUserByNickName(String nickName);	
	
	@Query(value = "SELECT * FROM Usuario WHERE activo = true and cedula = ?1", nativeQuery = true)
	User findUserById(int id);
	
	@Query(value = "SELECT * FROM Usuario", nativeQuery = true)
	Collection<User> getAllUser();
	
	@Query(value = "SELECT * FROM Usuario ORDER BY cedula DESC", nativeQuery = true)
	Collection<User> getAllUsersOrderedById();
	
	@Query(value = "SELECT * FROM Usuario ORDER BY nombres", nativeQuery = true)
	Collection<User> getAllUsersOrderedByNameDesc();
	
	@Query(value = "SELECT * FROM Usuario ORDER BY nick", nativeQuery = true)
	Collection<User> getAllUsersOrderedByNick();
	
	@Query(value = "SELECT * FROM Usuario WHERE role_name =?1", nativeQuery = true)
	Collection<User> getUserByRol(String roleName);
		
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario u SET nombres = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	void updateUserName(String newName, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario u SET nick = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	void updateUserNick(String nickName, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario u SET cedula = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	void updateUserId(int newid, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario u SET email = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	void updateUserEmail(String email, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario u SET role_name = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	void updateAuthority(String rol, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario u SET pass = ?1 WHERE u.cedula = ?2", nativeQuery = true)
	void updatePass(String pass, int id);
}
