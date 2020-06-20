package scheduler;

import employee.Dentist;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The SchedulerLauncher class use Scheduler class object to run the application 
 * 
 * 
 * @author  Dhillon Sahilvir
 * @version 1.0
 */

public class SchedulerLauncher {

        /**
         * Main method make object of Scheduler class and used to run the application
         * @param args command line arguments for main
         *
         */
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater
		(new Runnable() {
		public void run() {
		new Scheduler(new Dentist("Dr.Andrews")).launch();
		}
		});
            
            
        }
}




