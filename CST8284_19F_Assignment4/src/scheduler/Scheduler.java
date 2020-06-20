package scheduler;

import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import employee.Employee;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Class manage all of our application work. It takes required data input from user
 * and store it in appointment object and then save it in array list of appointments.
 * 
 *
 * @author Dhillon Sahilvir
 * @version 1.0
 */
public class Scheduler {
	
	/**
	 * Frame contains all GUI
	 */
	private static JFrame frame;
	/**
	 * Container contains all Components
	 */
	private static Container cp;
	/**
	 * Text Area to contain daily schedule
	 */
	private static JTextArea textArea;
	/**
	 * MenuEventHandler class object which used to add action listner to buttons
	 */
	private static MenuEventHandler hnd;

	
	/**
	 * Save Button used to open Save Appointment Dialog Box
	 */
	public static JButton saveAppointmentBtn;
	/**
	 * Display Button used to open Display Appointment Dialog Box
	 */
	public static JButton dispAppointmentBtn;
	/**
	 * Display schedule button used to display schedule of specific date
	 */
	public static JButton displayScheduleBtn;
	/**
	 * Save Button used to save current appointments to file
	 */
	public static JButton saveToFileBtn;
	/**
	 * Load Button used to load appointments from file to array list
	 */
	public static JButton loadFromFileBtn;
	/**
	 * Exit button used to exit from application
	 */
	public static JButton exitBtn;

	/**
	 * GridBagConstraints represents constraints for buttons
	 */
	private static final GridBagConstraints buttonConstants = new GridBagConstraints(
			0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,  // gridx, gridy, gridwidth, gridheight, weightx, weighty
			GridBagConstraints.NORTHWEST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady

	
	/**
	 * GridBagConstraints represents constraints for text
	 */
	private static final GridBagConstraints textAreaConstants =new GridBagConstraints(
			1, 0, 4, 6, 1.0, 0, 
			GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(2, 2, 2, 2), 10, 10);

	/**
	 * String contains dentist names
	 */
	private static  String dentistName;

	/**
	 * Scanner object used to take input from user
	 */
	private static Scanner scan = new Scanner(System.in);
	/**
	 * Array list of appointments which contains appointments in sorted order
	 */
	private ArrayList<Appointment> appointments = new ArrayList<>();
	/**
	 * Object of employee class used to store data of (Appointed person)
	 */
	private Employee employee;

	/**
	 * Named constant used to identify what action to perform
	 */
	private static final int 
	SAVE_APPOINTMENT =            1,     DELETE_APPOINTMENT =            2,
	CHANGE_APPOINTMENT =          3,     DISPLAY_APPOINTMENT =           4,
	DISPLAY_SCHEDULE =            5,     SAVE_APPOINTMENTS_TO_FILE =     6,
	LOAD_APPOINTMENTS_FROM_FILE = 7,     EXIT =                          0;

	/**
	 * Constructor which display for Dentist the appointment is going to schedule 
	 * @param emp  Employee object contains information about Dentist
	 */
	public Scheduler(Employee emp) {

		dentistName = emp.getName();

		hnd = new MenuEventHandler(this);

		developFrame();

		SaveAppointmentDialog.setActvityTypes(emp.getActivityType());

		setEmployee(emp);
	}

	/**
	 * Method used to develop frame for GUI and set its configurations
	 */
	public void developFrame()
	{
		frame = new JFrame("Scheduling Appointments for " + dentistName);
		frame.setSize(500,500);
		frame.setLocation(400, 100);

		cp = frame.getContentPane(); 
		cp.setLayout(new GridBagLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Used to make a button to display on GUI
	 * @param btnText String representing text for button
	 * @return JButton reference of button
	 */
	private static JButton addButton(String btnText)
	{
		JButton button = new JButton(btnText);
		button.setPreferredSize(new Dimension(300, 50));
		button.setHorizontalAlignment(SwingConstants.CENTER);
		//		btnPanel.add(button);
		cp.add(button, buttonConstants);

		return button;
	}

	/**
	 * Method used to add text area to GUI
	 */
	private static void addTextArea()
	{
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createEmptyBorder(02, 30, 30, 30));
		
		textArea = new JTextArea("", 22, 28);
		textArea.setEditable(false);
		JScrollPane spane = new JScrollPane(textArea);
		spane.setHorizontalScrollBarPolicy(
		        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		spane.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		p.add(spane);
		cp.add(p, textAreaConstants);
	}

	/**
	 * Used to set employee object
	 * @param emp Employee object contains data about Dentist
	 */
	private void setEmployee(Employee emp) {this.employee = emp;}

	/**
	 * Used to get employee object
	 * @return Employee object contains data about Dentist
	 */
	private Employee getEmployee() {return employee;}

	/**
	 * Method calls displayMenu method again and again to get user input
	 * and the execute action based on input 
	 */
	public void launch() {
		int choice = 0;
		do {
			displayMenu();
			executeMenuItem();
		} while (choice != EXIT);		
	}

	/**
	 * Method used to display menu to user and then get user input about his/her choice
	 */
	public void displayMenu() {

		saveAppointmentBtn = addButton("Save Appointment");
		dispAppointmentBtn = addButton("Display Appointment");
		displayScheduleBtn = addButton("Display Schedule");
		saveToFileBtn = addButton("Save Appointments to File");
		loadFromFileBtn = addButton("Load Appointments from File");
		exitBtn = addButton("Exit");

		addTextArea();
		updateTextArea();

		frame.pack();

	}

	/**
	 * Method used to update Text Area containing Daily Schedule when needed
	 */
	public void updateTextArea()
	{
		textArea.setText("");

		Date today = new Date();
		Calendar calToday = Calendar.getInstance();
		calToday.setTime(today);

		int day = calToday.get(Calendar.DAY_OF_MONTH);
		int month = calToday.get(Calendar.MONTH);
		int year = calToday.get(Calendar.YEAR);


		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, day, 0, 0);

		textArea.setText("Schedule for " + today + "\n\n");
		for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			textArea.append(getAppointment(cal));		
		}
	}

	/**
	 *  Execute particular action based on choice
	 *  Method used to hide current frame from user
	 */

	public static void hideFrame() {
		frame.setVisible(false);
	}

	/**
	 * Method used to show Frame to user
	 */
	public void showFrame()
	{
		updateTextArea();
		frame.setVisible(true);
	}
	
	/**
	 * Method used to add Action listener to buttons
	 */
	private void executeMenuItem() {

			saveAppointmentBtn.addActionListener(hnd);
			dispAppointmentBtn.addActionListener(hnd);
			displayScheduleBtn.addActionListener(hnd);
			loadFromFileBtn.addActionListener(hnd);
			saveToFileBtn.addActionListener(hnd);
			exitBtn.addActionListener(hnd);
	}
	
	/**
	 * Method used to check whether input String is Empty or not
	 * @param input String contains input from user
	 * @throws If input is null or empty then throws BadAppointmentDataException
	 */
	private void isNull(String input)
	{
		if(input.isEmpty())
			throw new BadAppointmentDataException("Must enter a value","Empty or null value entered");
	}
	
	/**
	 * Method used to validate all inputs from user
	 */
	private void validateInput()
	{
		String activityType = (String)SaveAppointmentDialog.getActivityType().getSelectedItem();

		isNull(SaveAppointmentDialog.getNameTxt().getText());
		isNull(SaveAppointmentDialog.getPhoneTxt().getText());
		isNull(SaveAppointmentDialog.getDesriptionTxt().getText());
		isNull(activityType);
		isNull(SaveAppointmentDialog.getDateTxt().getText());
		isNull(SaveAppointmentDialog.getTimeTxt().getText());

	}

	/**
	 * Method takes appointment data i.e full name, phone number, calendar(date and time),
	 * activity for appointment, from user
	 * 
	 * @return Appointment object contains data based on user input
	 */
	public Appointment makeAppointmentFromUserInput() {

		validateInput();

		String fullName = SaveAppointmentDialog.getNameTxt().getText();
		String phoneNumber = SaveAppointmentDialog.getPhoneTxt().getText();
		TelephoneNumber phone = new TelephoneNumber(phoneNumber);
		Calendar cal = makeCalendarFromUserInput(false, SaveAppointmentDialog.getDateTxt().getText(), SaveAppointmentDialog.getTimeTxt().getText());
		String activity = SaveAppointmentDialog.getDesriptionTxt().getText();

		String activityType = (String)SaveAppointmentDialog.getActivityType().getSelectedItem();

		Activity act = new Activity(activity, activityType);
		return (new Appointment(cal, fullName, phone, act));
	}

	/**
	 * Take date and time input from user and validate Date and Time to meet mentioned format(DDMMYYYY)
	 * @param suppressHour boolean representing suppressHour
	 * @param date String contains date input from user
	 * @param time String contains time input from user
	 * @return Calendar object based on user input
	 * @throws If date does not meet required format then throws BadAppointmentData exception
	 */
	public static Calendar makeCalendarFromUserInput(boolean suppressHour, String date, String time) {
		Calendar cal = Calendar.getInstance();
		int hour = 0;

		cal.clear();

		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
		df.setLenient(false);
		try
		{
			df.parse(date);
		}
		catch(ParseException ex)
		{
			throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY", "Bad calendar format");
		}

		int day = Integer.parseInt(date.substring(0,2));
		int month = Integer.parseInt(date.substring(2,4)) - 1;  // offset by one to account for zero-based month in Calendar
		int year = Integer.parseInt(date.substring(4,8));

		if (!suppressHour) {				
			hour = processTimeString(time);
		}


		cal.set(year, month, day, hour, 0);

		return (cal);
	}

	/**
	 * Takes time and process it to find hour
	 * @param t String representing time
	 * @return integer representing hour of appointment
	 */
	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains(":")) hour = Integer.parseInt(t.split(":")[0]);
		else if (t.contains (" ")) hour = Integer.parseInt(t.split(" ")[0]);
		else hour = Integer.parseInt(t);
		return ((hour < 8) ? hour+12 : hour);
	}

	/**
	 * Method to search for and appointment based on calendar object 
	 * @param cal  Calendar object act as a key to search in array list of appointments
	 * @return null if unable to find appointment, Appointment object otherwise
	 */    
	private Appointment findAppointment(Calendar cal) {
		int index = Collections.binarySearch(appointments, new Appointment(cal, "ABC EFG", new TelephoneNumber("613-555-1212"), new Activity("desc", "cat")), new SortAppointmentByCalendar());
		if(index < 0)
			return null;
		return appointments.get(index);
	}

	/**
	 * Method used to add new appointment(if it does no already exist) in array list of appointments and then sort it
	 * @param apt Appointment which we want to add
	 * @return true if successfully add appointment in array list, false otherwise 
	 */
	public boolean saveAppointment(Appointment apt) {	
		Calendar cal = apt.getCalendar();  // Check that the appointment does not already exist
		if (findAppointment(cal)==null) {  // Time slot available, okay to add appointment
			getAppointments().add(apt);
			Collections.sort(appointments, new SortAppointmentByCalendar());

			JOptionPane.showMessageDialog(null, "Appointment Saved Successfully", "Success Message", JOptionPane.INFORMATION_MESSAGE);

			return true;
		}  // else time slot taken, need to make another choice
		JOptionPane.showMessageDialog(null, "Cannot save; an appointment at that time already exists", "Failure Message", JOptionPane.INFORMATION_MESSAGE);
		return false;
	}

	/**
	 * Method used to delete appointment from array list
	 * @param cal Calendar object on which basis we want to remove appointment 
	 * @return true if appointment successfully delete, false otherwise
	 */
	public boolean deleteAppointment(Calendar cal) {
		if (displayAppointment(cal)) {
			int res = JOptionPane.showConfirmDialog(null, "Do you want to delete this Appointment", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);


			//			String okToChange = getResponseTo("\nEnter 'Yes' to delete this appointment");
			if (res == 0) {  						// okay to proceed with change/deletion?
				getAppointments().remove(findAppointment(cal)); 

				JOptionPane.showMessageDialog(null, "Appointment Deleted Successfully", "Delete Message", JOptionPane.INFORMATION_MESSAGE); 
				return true;
			} else JOptionPane.showMessageDialog(null, "Request Canceled", "Cancel Message", JOptionPane.INFORMATION_MESSAGE); 
		} return false;  // Appointment didn't exist at the date/time specified
	}

	/**
	 * Change appointment(if slot is free) after taking new time and date input from user
	 * @param cal Calendar object whose appointment we want to change
	 * @return true if appointment time successfully changes, false otherwise
	 */
	public boolean changeAppointment(Calendar cal) {
		if (displayAppointment(cal)) {  							// display existing appointment on this date/time

			int res = JOptionPane.showConfirmDialog(null, "Do you want to change date and time of this Appointment", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);


			if (res == 0) {

				JTextField dateField = new JTextField(5);
				JTextField timeField = new JTextField(5);


				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Enter Date:"));
				myPanel.add(dateField);
				myPanel.add(Box.createHorizontalStrut(15)); // a spacer
				myPanel.add(new JLabel("Enter Time:"));
				myPanel.add(timeField);

				int result = JOptionPane.showConfirmDialog(null, myPanel, 
						"Enter New Date and Time", JOptionPane.OK_OPTION);

				if(result == 0)
				{

					Calendar newCal = makeCalendarFromUserInput(false, dateField.getText(), timeField.getText()); // get new date/time
					if (findAppointment(newCal)==null) {				// appointment time not already taken
						findAppointment(cal).setCalendar(newCal);		// set new date/time in appointment
						JOptionPane.showMessageDialog(null, "Appointment re-booked", "Success Message", JOptionPane.INFORMATION_MESSAGE);
						Collections.sort(appointments, new SortAppointmentByCalendar());
						return true;									// new appointment time set

					} else JOptionPane.showMessageDialog(null, "That time is already booked for an appointment", "Cancel Message", JOptionPane.INFORMATION_MESSAGE);
				}else JOptionPane.showMessageDialog(null, "Request Canceled", "Cancel Message", JOptionPane.INFORMATION_MESSAGE);
			}else  JOptionPane.showMessageDialog(null, "Request Canceled", "Cancel Message", JOptionPane.INFORMATION_MESSAGE);
		} return false;  // Appointment does not exist, was unavailable, or cancelled
	}

	/**
	 * Display appointment based on calendar object
	 * @param cal Calendar object contains date and time of appointment to be displayed
	 * @return true if appointment found and displayed, false otherwise
	 */
	public boolean displayAppointment(Calendar cal) {
		Appointment apt = findAppointment(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		String displayMsg = ((apt!=null) ?
				"\n\n"+ apt.toString()+"\n": // Output the appointment as a string to the console, otherwise...
					"No appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
				);
		JOptionPane.showMessageDialog(null, displayMsg, "Appointment Information", JOptionPane.INFORMATION_MESSAGE);
		return (apt!=null);
	}
	
	/**
	 * Return Appointment based on calendar object
	 * @param cal Calendar object contains date and time of appointment to be get
	 * @return String based on Appointment find or not
	 */
	public String getAppointment(Calendar cal)
	{
		Appointment apt = findAppointment(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		String displayMsg = ((apt!=null) ?
				"\n"+ apt.toString()+"\n": // Output the appointment as a string to the console, otherwise...
					"\nNo appointment scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
				);
		return displayMsg;
	}

	/**
	 * Display all appointments from 8 o'clock to 17 o'clock of specific date 
	 * @param cal Calendar object contains date to which appointments we want to display
	 */
	public void displayDaySchedule(Calendar cal) {
		for (int hrCtr = 8; hrCtr < 17; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			displayAppointment(cal);		
		}
	}

	/**
	 * Save all appointments into file
	 * @param apts Array list of appointments
	 * @param saveFile file name in which appointments are to be saved
	 * @return true if successfully saved appointments, false otherwise
	 */
	public static boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile) {
		try (FileOutputStream fos = new FileOutputStream(saveFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			for (Appointment apt: apts) 
				oos.writeObject(apt);
			JOptionPane.showMessageDialog(null, "Appointment data saved to " + saveFile, "Success Message", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to save appointments to " + saveFile, "Failure Message", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	/**
	 * Load appointments from file and store it in array list
	 * @param sourceFile Name of file contains appointments
	 * @param apts Appointments array list in which all appointments are loaded from file
	 * @return true if successfully load appointments from file, false otherwise 
	 */
	public boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts) {
		apts.clear();  // remove all existing appointments from the ArrayList before loading from file
		try (FileInputStream fis = new FileInputStream(sourceFile);
				ObjectInputStream ois = new ObjectInputStream(fis);){
			while(true) apts.add((Appointment)ois.readObject());

		} 
		catch (EOFException ex) {
			JOptionPane.showMessageDialog(null, "Appointments successfully loaded from " + sourceFile, "Success Message", JOptionPane.INFORMATION_MESSAGE);
			updateTextArea();
			return true;}
		catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Failed to load appointments from " + sourceFile, "Failure Message", JOptionPane.INFORMATION_MESSAGE);
			return false;} 	
	}

	/**
	 * Used to get array list of appointments
	 * @return ArrayList of appointments
	 */
	public ArrayList<Appointment> getAppointments() {return appointments;}

}
