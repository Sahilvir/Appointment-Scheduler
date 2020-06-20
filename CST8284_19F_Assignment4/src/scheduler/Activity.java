package scheduler;

import java.io.Serializable;

/**
 * Class used to represent the activity of appointment
 * @author Dhillon Sahilvir
 * @version 1.0
 */
public class Activity implements Serializable {
        /**
         * String represents description of work
         */
	private String descriptionOfWork;
        /**
         * String represents category of appointment
         */
	private String category;
	
        /**
         * Constructor uses set methods to set description and category
         * @param description  String contains description of Appointment
         * @param category     String contains category of Appointment
         */
	public Activity(String description, String category) {
		setDescription(description);
		setCategory(category);
	}
	
        /**
         * Method used to get description of appointment
         * @return String represents description of appointment
         */
	public String getDescription() {return descriptionOfWork;}
        /**
         * Method used to set description of appointment
         * @param description  String represents description of appointment
         */
	public void setDescription(String description) {this.descriptionOfWork = description;}
	
        /**
         * Method used to get category of appointment
         * @return String represents category of appointment
         */
	public String getCategory() {return category;}
        
        /**
         * Method used to set category of appointment
         * @param category String represents category of appointment
         */
	public void setCategory(String category) {this.category = category;}
	
        /**
         * Method used to get detail of Activity class
         * @return String contains category and description of Appointment
         */
	public String toString() {return getCategory() + "\n" + getDescription();}
}
