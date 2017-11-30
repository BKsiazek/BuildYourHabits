package buildyourhabits.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="Person")
public class User {
	
	@Id
	private int id;
	
	@Size(min=5, message="Username must contain at least 5 characters.")
	private String name;
	
	@Size(min=4, message="Password must contain at least 4 characters.")
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
