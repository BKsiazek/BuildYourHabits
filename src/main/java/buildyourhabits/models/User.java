package buildyourhabits.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="person")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userID;
	
	@Column(unique=true)
	@Size(min=5, message="Username must contain at least 5 characters.")
	private String name;
	
	@Size(min=4, message="Password must contain at least 4 characters.")
	private String password;
	
	private String role;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE)
	private List<Habit> habits;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Habit> getHabits() {
		return habits;
	}
	public void setHabits(List<Habit> habits) {
		this.habits = habits;
	}
	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, password=%s]", userID, name, password);
	}
}
