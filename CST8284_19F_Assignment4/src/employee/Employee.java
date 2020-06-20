package employee;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Abstract Class representing information like full name of employee
 * @author Dhillon Sahilvir
 * @version  1.0
 */
public abstract class Employee {
        /**
         * String representing full name of employee
         */
	private String fullName;
	
        /**
         * Default constructor 
         */
	protected Employee() {this("unknown");}
	/**
         * 
         * Constructor used to set full name of employee
         * @param fullName String representing full name of employee
         */
        protected Employee(String fullName) {setName(fullName);}
	
        /**
         * Scanner object used to take input from user
         */
        protected static Scanner scan = new Scanner(System.in);
	
        /**
         * Method used to set name of employee
         * @param fullName String represent full name of employee
         */
	public void setName(String fullName) {this.fullName = fullName;}
	
        /**
         * Method used to get full name of employee
         * @return  String represents name of employee
         */
        public String getName() {return fullName;}
	
        /**
         * Method return polymorphic activity type
         * @return activity type of child class
         */
	public abstract ArrayList<String> getActivityType();
	
        /**
         * Method used to get name of employee
         * @return String representing name of employee
         */
	@Override
	public String toString() {return getName();}
}