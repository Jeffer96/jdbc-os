package com.airefresco.app.PayLoad;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginRequest {
	
	private String userName;
	private String userPass;
		
	public LoginRequest() {
		
	}
	
	public LoginRequest(String userName, String userPass) {
		this.setUserName(userName);
		this.setUserPass(userPass);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	private String secure(String plain) {
		return plain;
		//return new BCryptPasswordEncoder().encode(plain);
	}
	
	public void setUserPass(String userPass) {
		this.userPass = secure(userPass);
	}

	
}
