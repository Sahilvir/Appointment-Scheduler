package scheduler;

import java.io.Serializable;

/**
 *  Class Telephone number used to store area code, line number, and prefix
 *  of phone number of appointed persons
 * 
 * @author Dhillon Sahilvir
 * @version 1.0
 */
public class TelephoneNumber implements Serializable {
        
        /**
         * area code    used to store area code of phone number
         * line number  used to store line number of phone number
         * prefix       used to store prefix of phone number
         */
	private int areaCode, lineNumber, prefix;
	
        /**
         *  Constructor takes phone number and validate it and then use set methods
         *  to to store area code, prefix and line number  
         * @param phoneNumber String representing phone number of appointed person
         */
	public TelephoneNumber(String phoneNumber) {
                isValidNumber(phoneNumber);
                int areaCode = Integer.parseInt(phoneNumber.split("-")[0].trim());
                int prefix = Integer.parseInt(phoneNumber.split("-")[1].trim());
		int lineNumber = Integer.parseInt(phoneNumber.split("-")[2].trim());
		
                
                setAreaCode(areaCode); setPrefix(prefix); setLineNumber(lineNumber);
	}	
	
        /**
         * Method used to get area code of phone number
         * @return integer representing area code of phone number
         */
	public int getAreaCode() {return areaCode;}
        
        /**
         * Method used to set area code of phone number
         * @param areaCode  integer representing area code of phone number
         */
        
	public void setAreaCode(int areaCode) {this.areaCode = areaCode;}
	
        /**
         *  Method used to get prefix of phone number
         * @return integer representing prefix of phone number
         */
        public int getPrefix() { return prefix;}
        
        /**
         * Method used to set prefix of phone number
         * @param prefix  integer representing prefix of phone number
         */
	public void setPrefix(int prefix) {this.prefix = prefix;}
	
        /**
         * Method used to get line number of phone number
         * @return integer representing line number of phone number
         */
        public int getLineNumber() {return lineNumber;}
        
        /**
         * Method used to set line number of phone number
         * @param lineNumber  integer representing line number of phone number
         */
	public void setLineNumber(int lineNumber) {this.lineNumber = lineNumber;}
	
        /**
         * Method to get phone number
         * @return  String representing phone number of appointed person
         */
	public String toString() {return "(" + getAreaCode() +") "+ getPrefix() + "-" + getLineNumber();}
        
        /**
         * Method used to validate phone number
         * @param phoneNumber String representing phone number
         * @throws if phone number does not meet required criteria then throws BadAppointmentDataxception
         */
        private void isValidNumber(String phoneNumber)
        {
            int countOfDash = (phoneNumber.split("-").length)-1;
            if(!checkNumber(phoneNumber))
                throw new BadAppointmentDataException("Telephone numbers can only contain numbers or the character '-'", "Bad character(s) in input string");
            if(countOfDash < 2 || countOfDash > 2)
                throw new BadAppointmentDataException("Missing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number", "Incorrect format");
            if(phoneNumber.split("-")[0].trim().length() != 3 || phoneNumber.split("-")[1].trim().length() != 3 || phoneNumber.split("-")[2].trim().length() != 4)
                throw new BadAppointmentDataException("Missing digit(s); correct format is AAA-PPP-NNNN, where AAA is the area code and PPP-NNNN is the local number", "Incorrect format");
            if(phoneNumber.split("-")[0].trim().charAt(0) == '0' || phoneNumber.split("-")[0].trim().charAt(0) == '1')
                throw new BadAppointmentDataException("Area code cannot start with a '0' or a '1'", "Invalid number");

        }
        
        /**
         * Method used to validate that phone number should contains numbers and '-'
         * @param phoneNumber string representing phone number
         * @return true if phone number contains only numbers and '-', false otherwise 
         */
        private Boolean checkNumber(String phoneNumber)
        {
            for(int i=0;i<phoneNumber.length();i++)
            {
                if(!(phoneNumber.charAt(i) >= '0' && phoneNumber.charAt(i) <='9') && phoneNumber.charAt(i) != '-')
                    return false;
            }
            return true;
        }
}
