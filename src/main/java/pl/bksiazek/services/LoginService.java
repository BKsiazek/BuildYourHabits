package pl.bksiazek.services;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public boolean validateUser(String user, String password) {
		return user.equals("Bartek") && password.equals("passy");
	}
	
}
