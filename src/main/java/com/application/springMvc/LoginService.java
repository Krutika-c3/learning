package com.application.springMvc;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean validateUser(String user, String password) {
		if(user.equalsIgnoreCase("krutika") && password.equals("demo")){
			return true;
		}
		return false;
	}

	public boolean notValidateUser(String user, String password) {
		if(user.equalsIgnoreCase("krutika") && password.equals("demo")){
			return false;
		}
		return true;
	}
}