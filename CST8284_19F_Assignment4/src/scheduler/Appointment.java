package scheduler;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents an Appointment which contains Appointment date, first name, last name, phone number
 * and activity for particular person
 * 
 * @author Dhillon Sahilvir
 * @version 1.0
 */
public class Appointment implements Serializable {
	/**
         * Represents appointment date
         */
        private Calendar aptDate;
        /**
         * Represent first name and last name of appointed person
         */
	private String firstName, lastName;
        /**
         * Represent phone number of appointed person
         */
	private TelephoneNumber phone;
        /**
         * Represent activity of appointment
         */
	private Activity activity;
	//public static final long serialVersionUID = 1L;
	
        /**
         * Constructor which uses chaining to call other parameterized constructor 
         * 
         * @param cal        Receive object of Calender class representing date of appointment
         * @param fullName   Full name of appointed person
         * @param phone      Phone number of appointed person
         * @param act        Activity of Appointment
         */
	public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity act) {
		this(cal, fullName.trim().split(" ")[0], fullName.trim().split(" ")[1], phone, act);
	}
	
        /**
         * 
         * Constructor that first validate first and last name of appointed person 
         * and then use setter to set first name, last name, phone number and activity of appointment
         * 
         * @param cal        Receive object of Calender class representing date of appointment
         * @param firstName  Full name of appointed person
         * @param lastName   Full name of appointed person
         * @param phone      Phone number of appointed person
         * @param act        Activity of Appointment
         */
	public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity act) {
		validateName(firstName);
                validateName(lastName);
                setFirstName(firstName.trim()); 
		setLastName(lastName.trim());
		setCalendar(cal); 
		setPhone(phone);
		setActivity(act);
	}
	
        
        /**
         * Method used to get the appointment date
         * @return   Calendar object representing appointment date
         */
	public Calendar getCalendar() {return aptDate;}
        
        /**
         *  Method used to set Appointment date
         * @param aptDate   receive Calender object representing appointment date 
         */
	public void setCalendar(Calendar aptDate) {this.aptDate = aptDate;}
	
        /**
         * Method used to get the first name of appointed person
         * @return   String representing first name of appointed person
         */
	public String getFirstName() {return firstName; }
        
        /**
         * Method used to set first name of appointed person
         * @param firstName  String representing first name of appointed person
         */
	public void setFirstName(String firstName) {this.firstName = firstName;}
	
        /**
         * Method used to get the last name of appointed person
         * @return String representing last name of appointed person
         */
	public String getLastName() {return lastName;}
        
        /**
         * Method used to set last name of appointed person
         * @param lastName   String representing last name of appointed person
         */
	public void setLastName(String lastName) {this.lastName = lastName;}
	
        /**
         * Method used to get the phone number of appointed person
         * @return TelephoneNumber object representing phone number of appointed person
         */
	public TelephoneNumber getPhone() {return phone;}
        
        /**
         * Method used to set the phone number of appointed person
         * @param phone   TelephoneNumber object representing phone number of appointed person
         */
	public void setPhone(TelephoneNumber phone) {this.phone = phone;}
	
        /**
         *  Method used to get activity of appointment
         * @return   Activity object representing Activity of appointment
         */
	public Activity getActivity() {return activity;}
        
        /**
         * Method used to set activity of appointment
         * @param activity  Activity object representing Activity of appointment
         */
	public void setActivity(Activity activity) {this.activity = activity;}
	
        /**
         * Method used to get detail description of Appointment object
         * @return String representing detail description of Appointment object
         */
	public String toString() {
		return getCalendar().getTime().toString() + "\n" +
			   getFirstName() + " " + getLastName() + "\n" + 
			   getPhone().toString() + "\n" +
			   getActivity().toString();
	}
        
        /**
         * Method used to validate name so that name only contains letters, '-', '.', '/''
         * @throws  If name contains other than specified letters than this method throws an BadAppointmentDataException
         * @param name String representing first name or last name
         */
        private void validateName(String name){
            for(int i=0;i<name.length();i++)
            {
                if(!(name.charAt(i) >= 'a' && name.charAt(i) <='z') && !(name.charAt(i) >= 'A' && name.charAt(i) <='Z') && name.charAt(i) != '-' && name.charAt(i) != '.' && name.charAt(i) != '\'')
                    throw new BadAppointmentDataException("Name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe (')", "Illegal characters in name");
            }
            if(name.length()>30)
                throw new BadAppointmentDataException("Name cannot exceed 30 characters", "Name exceeds maximum length");
        }

}
