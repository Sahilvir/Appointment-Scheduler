package scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Class which handled interaction of user with Main Menu GUI
 * @author Dhillon Sahilvir
 * @version 1.0
 *
 */
public class MenuEventHandler implements ActionListener{
	
	/**
	 * Scheduler object contains reference of Scheduler class
	 */
	private static Scheduler schedulerRef;
	
	/**
	 * Parametrized Constructor
	 * @param ref contains reference of Scheduler class
	 */
	public MenuEventHandler(Scheduler ref) {
		schedulerRef = ref;
	}
	
	/**
	 * Method which receive event and perform action accordingly
	 * @param e Action Event Object which is used to identify what happen on GUI due to which this event is trigger
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == schedulerRef.saveAppointmentBtn.getText())
		{
			SaveAppointmentDialog.showAppointmentDialog();
			Scheduler.hideFrame();
		}
		else if(e.getActionCommand() == schedulerRef.dispAppointmentBtn.getText())
		{
			AppointmentDialog.showAppointmentDialog();
			Scheduler.hideFrame();
		}
		else if(e.getActionCommand() == schedulerRef.displayScheduleBtn.getText())
		{
			try {
			String code = JOptionPane.showInputDialog(null, "Enter Date", "Date Input Dialog", JOptionPane.OK_CANCEL_OPTION);
			if(code != null)
			{
				schedulerRef.displayDaySchedule(schedulerRef.makeCalendarFromUserInput(true, code, "0"));
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Request Canceled", "Cancel Message", JOptionPane.INFORMATION_MESSAGE);
			}
			}catch(BadAppointmentDataException ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		else if(e.getActionCommand() == schedulerRef.saveToFileBtn.getText())
		{
			int res = JOptionPane.showConfirmDialog(null, "Are you sure, you want to save data in file CurrentAppointments.apts", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
			if(res == 0)
			{
				schedulerRef.saveAppointmentsToFile(schedulerRef.getAppointments(), "CurrentAppointments.apts");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Request Canceled", "Cancel Message", JOptionPane.INFORMATION_MESSAGE);
			}
				
		}
		else if(e.getActionCommand() == schedulerRef.loadFromFileBtn.getText())
		{
			int res = JOptionPane.showConfirmDialog(null, "Are you sure, you want to load data from file CurrentAppointments.apts", "Confirmation Dialog", JOptionPane.YES_NO_OPTION);
			if(res == 0)
			{
				schedulerRef.loadAppointmentsFromFile("CurrentAppointments.apts", schedulerRef.getAppointments());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Request Canceled", "Cancel Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getActionCommand() == schedulerRef.exitBtn.getText())
		{
			Scheduler.hideFrame();
			System.exit(0);
		}
	}
	
	/**
	 * Used to save appointment after save button pressed
	 */
	public static void saveAppointmentBtnPressed()
	{
		try {
		schedulerRef.saveAppointment(schedulerRef.makeAppointmentFromUserInput());
		}
		catch(BadAppointmentDataException ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Used to find appointment after find button pressed
	 */
	public static void findAppointment()
	{
		try {
		schedulerRef.displayAppointment(schedulerRef.makeCalendarFromUserInput(false, AppointmentDialog.getDateTxt().getText(), AppointmentDialog.getTimeTxt().getText()));
		}catch(BadAppointmentDataException ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Used to delete appointment after delete button pressed
	 */
	public static void deleteAppointment()
	{
		try {
		schedulerRef.deleteAppointment(schedulerRef.makeCalendarFromUserInput(false, AppointmentDialog.getDateTxt().getText(), AppointmentDialog.getTimeTxt().getText()));
		}catch(BadAppointmentDataException ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/**
	 * Used to change appointment after change button preseed
	 */
	public static void changeAppointment() {
		try {
		schedulerRef.changeAppointment(schedulerRef.makeCalendarFromUserInput(false, AppointmentDialog.getDateTxt().getText(), AppointmentDialog.getTimeTxt().getText()));
		}catch(BadAppointmentDataException ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Method used to get reference of scheduler object 
	 * @return Scheduler object
	 */
	public static Scheduler getSchedulerRef() {
		return schedulerRef;
	}

	/**
	 * Method used to set value of scheduler object
	 * @param schedulerRef Scheduler object
	 */
	public static void setSchedulerRef(Scheduler schedulerRef) {
		MenuEventHandler.schedulerRef = schedulerRef;
	}
	
	
	
}
