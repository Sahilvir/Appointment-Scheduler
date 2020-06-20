package employee;

import java.util.ArrayList;

/**
 * Class representing information of Dentist like name, and work description 
 * @author Dhillon Sahilvir
 * @version 1.0
 */
public class Dentist extends Employee {
        /**
         * Represents work description i.e which services dentist can provide
         */
	private static String[] workDescription = {"Assessment", "Filling", "Crown", "Cosmetic Repair"};
        
        /**
         * Constructor which receive name of dentist and pass it to Employee class constructor
         * to set name of dentist
         * @param fullName   String representing name of Dentist 
         */
	public Dentist(String fullName) {
		super (fullName);
	}
        
        /**
         * Method display a menu to user to select type of treatment he/she wants to receive
         * @return   String representing work description of based on user selection
         */
	public ArrayList<String> getActivityType() {
		ArrayList<String> acivityTypes = new ArrayList<>();
		
		for (String description: workDescription)
			acivityTypes.add(description);
			
		
		return acivityTypes;
	}
}
