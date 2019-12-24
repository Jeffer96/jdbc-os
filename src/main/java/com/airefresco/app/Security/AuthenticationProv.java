package com.airefresco.app.Security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProv implements AuthenticationProvider {
	
	private CustomUserDetailsService cuds;
	
	//private BCryptPasswordEncoder encoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String nick = authentication.getName();
		String pass = authentication.getCredentials()+"";
		UserDetails user = cuds.loadUserByUsername(nick);
		if (user == null) {
	        throw new BadCredentialsException("User: "+nick+" couldn´t be authenticated, try again");
	    }
	    if (!user.isEnabled()) {
	        throw new DisabledException("User: "+nick+" is not enabled to login, contact the administrator");
	    }
	    //if (!encoder.matches(pass, user.getPassword())) {
	    if (!pass.equals(user.getPassword())) {
	        throw new BadCredentialsException("User: "+nick+" couldn´t be authenticated, try again");
	    }
		return new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public final void UserDetailsService(CustomUserDetailsService uds) {
		this.cuds=uds;
	}
	
	public final void PasswordEncoder(BCryptPasswordEncoder encoder) {
		//this.encoder=encoder;
	}

}
