package pl.bksiazek;

public class UserValidationService {
	public boolean isUserValid(String user, String password) {
		if(user.equals("Bart") && password.equals("password"))
			return true;
		
		return false;
	}
}