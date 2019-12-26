package com.airefresco.app.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.airefresco.app.Components.TokenProvider;
import com.airefresco.app.Model.User;
import com.airefresco.app.PayLoad.LoginRequest;
import com.airefresco.app.PayLoad.ResponseLogin;
import com.airefresco.app.Security.AuthenticationProv;
import com.airefresco.app.service.UserRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.airefresco.app.Security.Constants.HEADER_AUTHORIZACION_KEY;
import static com.airefresco.app.Security.Constants.TOKEN_BEARER_PREFIX;

//import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

@RestController
@CrossOrigin(origins="http://localhost:8080")
public class AuthControllerRest {
			
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected AuthenticationProv authenticationManager;
	
	@Autowired
	protected BCryptPasswordEncoder encoder;
	
	
	protected final void authenticate(UsernamePasswordAuthenticationToken upat, ResponseLogin ans, User us) {
		try {
			authenticationManager.PasswordEncoder(encoder);
			Authentication authentication = authenticationManager.authenticate(upat);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			ans.setToken(TokenProvider.generateToken(authentication));
			ans.setUserName(us.getNombres()+" "+us.getApellidos());
			ans.setRespCode("202");
			ans.successAuth(us.getRoleName());
		}catch (AuthenticationException  ex) {
			ans.setRespCode(ex.toString());
		}
	}
			
	@RequestMapping(value="/login/auth")
	@ResponseBody
	public ResponseLogin  authenticateUser(@RequestBody LoginRequest lr) {
		ResponseLogin ans = new ResponseLogin();
		try {
			User us;
			us = userRepository.findUserByNickName(lr.getUserName());
			if (us != null) {
				UsernamePasswordAuthenticationToken upat =  new UsernamePasswordAuthenticationToken(lr.getUserName(),lr.getUserPass());
				//System.out.println("Credentials states: "+upat.getName()+" \n "+upat.getCredentials()+" VS "+us.getPass());
				authenticate(upat, ans, us);
			}else {
				ans.setRespCode("401");
			}
		}catch (DataAccessException ex) { 
			ans.setRespCode("500");
		}
		return ans;
	}
			
	private final boolean isAuthenticated(HttpServletRequest request, String role) {
		String bearerToken = request.getHeader(HEADER_AUTHORIZACION_KEY);
		boolean resp = false;
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_BEARER_PREFIX)) {
			String ans = bearerToken.substring(7,bearerToken.length());
			if (StringUtils.hasText(ans) && TokenProvider.validateToken(ans)) {
				int userId = TokenProvider.getUserId(ans);
				try {
					System.out.println("*********************** el id esta activo: "+userId);
					resp = userRepository.findUserById(userId).getRoleName().equals(role);
				}catch (Exception ex) {
					
				}
			}
		}
		return resp;
	}
	
	@RequestMapping("/login/getUrl")
	public String getUrl(HttpServletRequest request) {
		if(isAuthenticated(request , "ADMIN")) {
			return "/administrador";
		}else if (isAuthenticated(request,"EMPLOYEE")) {
			return "/other";
		}else if (isAuthenticated(request,"USER")) {
			return "/home";
		}
		/**User actual_admin=userRepository.findUserById(1010226725);
		if (actual_admin==null) {
			try {
				//int id, String userName, String lname, String nickName, String userPass, String roleName, String email, boolean activo,Date creado
				//SimpleDateFormat formatter= new SimpleDateFormat("DD/MM/YYYY 'at' HH:mm:ss z");
				//Date date = new Date(System.currentTimeMillis());
				userRepository.save(new User(1010226725,"Jefferson David","Castañeda Carreño","JDCASTANEDA",encoder.encode("123456"),"ADMIN","jefferdcc@gmail.com",true,null));
				//System.out.println("Usuario Jefferson Guardado nuevamente?: "+date);
			}catch(Exception e) {
				System.out.println("Error ocurred: "+e);
			}
		}else if (actual_admin!=null) {
			userRepository.updatePass("123456", actual_admin.getCedula());
		}**/
		return "/login";
	}
	
}
