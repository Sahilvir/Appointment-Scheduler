package scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Class which handled interaction of user with GUI
 * @author Dhillon Sahilvir
 * @version 1.0
 *
 */

public class SaveAppointmentDialogEventHandler implements ActionListener{

	
	/**
	 * Default Contructor
	 */
	public SaveAppointmentDialogEventHandler() {
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * Method which receive event and perform action accordingly
	 * @param e Action Event Object which is used to identify what happen on GUI due to which this event is trigger
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == SaveAppointmentDialog.getSaveBtn().getText())
		{
			MenuEventHandler.saveAppointmentBtnPressed();
		}
		else if(e.getActionCommand() == SaveAppointmentDialog.getBackBtn().getText())
		{
			MenuEventHandler.getSchedulerRef().showFrame();
			SaveAppointmentDialog.disposeFrame();
		}
		else if(e.getActionCommand() == SaveAppointmentDialog.getExitBtn().getText())
		{
			MenuEventHandler.getSchedulerRef().showFrame();
			SaveAppointmentDialog.disposeFrame();
		}
		
	}
	
}
