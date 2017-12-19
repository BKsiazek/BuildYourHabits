package buildyourhabits.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class Habit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int habitID;
	
	@ManyToOne
	@JoinColumn(name="userID", nullable=false)
	private User owner;
	
	@Size(min=1, message = "Enter at least 1 character.")	//TODO delete it later
	private String description;
	
	private Date targetDate;
	private boolean isDone;
	
	public Habit(){
		
	}
	
	public Habit(int habitID, User owner, String description, Date targetDate, boolean isDone) {
		super();
		this.habitID = habitID;
		this.owner = owner;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public int getHabitID() {
		return habitID;
	}
	public void setHabitID(int habitID) {
		this.habitID = habitID;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	@Override
	public String toString() {
		return String.format("Habit [habitID=%s, owner=%s, description=%s, targetDate=%s, isDone=%s]", habitID, owner,
				description, targetDate, isDone);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + habitID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Habit other = (Habit) obj;
		if (habitID != other.habitID)
			return false;
		return true;
	}
}