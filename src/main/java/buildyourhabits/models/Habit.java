package buildyourhabits.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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
	
	private Date startDate;
	private Date targetDate;
	private boolean isDone;
	
	@Transient
	private int daysLeft;

	@Transient
	private int successRate;

	@Transient
	private int completionRate;
	
	//@ElementCollection
	//public List<String> succesfulDays = new ArrayList<String>();
	
	public Habit(){
		
	}
	
	public Habit(int habitID, User owner, String description) {
		super();
		this.habitID = habitID;
		this.owner = owner;
		this.description = description;
		this.startDate = new Date();
		this.targetDate = new Date();
		this.isDone = false;
		this.daysLeft = 0;
		this.successRate = 0;
		this.completionRate = 0;
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
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}

	public int getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(int successRate) {
		this.successRate = successRate;
	}

	public int getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(int completionRate) {
		this.completionRate = completionRate;
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