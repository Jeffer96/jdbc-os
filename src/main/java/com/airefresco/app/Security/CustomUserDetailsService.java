package com.airefresco.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	public CustomUserDetailsService() {
		//System.out.println(">>>>>>>>>>>>>>>>creando el cuds");
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByNickName( username );
		if (user == null) {
			throw  new UsernameNotFoundException(" User not found : " + username+ "at custom user details service finding by nick");
		}
		
        return UserPrincipal.create(user);
	}
	
    public UserDetails loadUserById(int id) {
		 User user = userRepository.findUserById(id);
		 if (user == null) {
				throw  new UsernameNotFoundException(" User not found : " + id+ "at custom user details service finding by id");
			}
        return UserPrincipal.create(user);
    }
	 
}
