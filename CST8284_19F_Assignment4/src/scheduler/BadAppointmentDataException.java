package scheduler;

/**
 * Custom Exception Class used to display exception message
 * @author Dhillon Sahilvir
 * @version 1.0
 */

public class BadAppointmentDataException extends RuntimeException{
    
    /**
     * String contains description of exception
     */
    private String description;
    /**
     * Constructor which call super and send message to Runtime Exception class so that we can get it on getMessage method
     * @param msg  String representing message of exception
     * @param desc String representing description of exception
     */
    public BadAppointmentDataException(String msg, String desc) {
        super(msg);
        description = desc;
    }

    /**
     * Default Constructor use chaining to call parameterized constructor
     */
    public BadAppointmentDataException() {
        this("Please try again", "Bad data entered");
    }
    
    /**
     * Method used to set description of exception
     * @param description String represents description of exception
     */
    public void setDescription(String description){this.description= description;}
    
    /**
     * Method used to set description of exception
     * @return String representing description of exception 
     */
    public String getDescription(){return this.description;}
    
}
