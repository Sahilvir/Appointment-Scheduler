package scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class which handled interaction of user with GUI
 * @author Dhillon Sahilvir
 * @version 1.0
 *
 */
public class AppointmentDialogueEventHandler implements ActionListener{

	/**
	 * Default Constructor
	 */
	public AppointmentDialogueEventHandler() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * Method which receive event and perform action accordingly
	 * @param e Action Event Object which is used to identify what happen on GUI due to which this event is trigger
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == AppointmentDialog.getFindBtn().getText())
		{
			MenuEventHandler.findAppointment();
		}
		else if(e.getActionCommand() == AppointmentDialog.getChangeBtn().getText())
		{
			MenuEventHandler.changeAppointment();
		}
		else if(e.getActionCommand() == AppointmentDialog.getDeleteBtn().getText())
		{
			MenuEventHandler.deleteAppointment();
		}
		else if(e.getActionCommand() == AppointmentDialog.getExitBtn().getText())
		{
			MenuEventHandler.getSchedulerRef().showFrame();
			AppointmentDialog.disposeFrame();
		}
		
	}

}
