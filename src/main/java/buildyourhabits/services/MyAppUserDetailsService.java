package buildyourhabits.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		buildyourhabits.models.User activeUser = service.getActiveUser(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUser.getRole());
		UserDetails userDetails = (UserDetails)new User(activeUser.getName(), activeUser.getPassword(), Arrays.asList(authority));
		return userDetails;
	}
}
