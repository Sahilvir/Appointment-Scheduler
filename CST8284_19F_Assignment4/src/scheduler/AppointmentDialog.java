package scheduler;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/* Adapted, with considerable modification, from 
 * http://www.java2s.com/Code/Java/Swing-JFC/TextAcceleratorExample.htm,
 * which is sloppy code and should not be emulated.
 */

/**
 * Class contains GUI to Display, Change, Delete Appointment
 * @author Dhillon Sahilvir
 *@version 1.0
 */
public class AppointmentDialog {
	
	/**
	 * GridBagConstraints represents constraints for text
	 */
	private static final GridBagConstraints textConstants = new GridBagConstraints(
    	0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,  // gridx, gridy, gridwidth, gridheight, weightx, weighty
    	GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady
	
	/**
	 * GridBagConstraints represents constraints for labels
	 */
	private static final GridBagConstraints labelConstants = new GridBagConstraints(
    	1, GridBagConstraints.RELATIVE, 1, 1, 1.0, 0, 
    	GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

	
	/**
	 * Frame contains all GUI
	 */
	private static JFrame f;
	
	/**
	 * Container contains all Components
	 */
	private static Container cp;
	
	/**
	 * Label width to set width of label
	 */
	private static final int labelWidth = 35;
	
	/**
	 * Font for Texts
	 */
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);	
	

	/**
	 * Text field used to take Date input from user
	 */
	private static JTextField dateTxt;
	
	/**
	 * Text field used to take Time inut from user
	 */
	private static JTextField timeTxt;
	
	/**
	 * Button used to Search Appointment
	 */
	private static JButton findBtn = new JButton("Find");
	
	/**
	 * Button used to Change Appointment
	 */
	private static JButton changeBtn = new JButton("Change");
	
	/**
	 * Button used to delete Appointment
	 */
	private static JButton deleteBtn = new JButton("Delete");
	
	/**
	 * Button used to exit Display Appointment Dialog Box and go to previous dialog
	 */
	private static JButton exitBtn = new JButton("Exit");

	
	
	
	/**
	 * Method used to place buttons on GUI
	 */
	private static void setButtonPanel()
	{
		GridLayout grid = new GridLayout(1,4, 30, 0);
		JPanel panel = new JPanel(grid);
		
		AppointmentDialogueEventHandler hnd = new AppointmentDialogueEventHandler();
		findBtn.addActionListener(hnd);
		changeBtn.addActionListener(hnd);
		deleteBtn.addActionListener(hnd);
		exitBtn.addActionListener(hnd);
		
		panel.add(findBtn);
		panel.add(changeBtn);
		panel.add(deleteBtn);
		panel.add(exitBtn);
		
		cp.add(panel, labelConstants);
		
	}
	
	/**
	 * Method used to show GUI dialog
	 */
	public static void showAppointmentDialog(){
	    f = new JFrame("Get, set, change or delete an appointment");
	    f.setLocation(400, 100);
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());

	    
	    dateTxt = setRow("Appointment Date (entered as DDMMYYYY):", 'd');
	    timeTxt = setRow("Appointment Time:", 't');
	    
	    setButtonPanel();
	    
	    f.pack();
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }
	
	/**
	 * Method used to add Text Field and Label to GUI
	 * @param label String contains text for labels
	 * @param keyboardShortcut char represent key board shortcut
	 * @return
	 */
	private static JTextField setRow(String label, char keyboardShortcut) {
		JLabel l; JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), textConstants);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
	    cp.add(t = new JTextField(labelWidth), labelConstants);
	    t.setFocusAccelerator(keyboardShortcut);
	    return t;
	}

	/**
	 * Used to get Date text field
	 * @return JTextField represent Date text field
	 */
	public static JTextField getDateTxt() {
		return dateTxt;
	}

	/**
	 * Used to set Date text field
	 * @param dateTxt JTextField represent Date text field
	 */
	public static void setDateTxt(JTextField dateTxt) {
		AppointmentDialog.dateTxt = dateTxt;
	}

	/**
	 * Used to get Time text field
	 * @return JTextField represent Time text field
	 */
	public static JTextField getTimeTxt() {
		return timeTxt;
	}
	
	/**
	 * Used to set Time text field
	 * @param timeTxt  JTextField represents Time text field
	 */
	public static void setTimeTxt(JTextField timeTxt) {
		AppointmentDialog.timeTxt = timeTxt;
	}
	
	/**
	 * Used to get Find Button Object
	 * @return JButton Object
	 */
	public static JButton getFindBtn() {
		return findBtn;
	}

	/**
	 * Used to set Find Button Object
	 * @param findBtn JButton Object
	 */
	public static void setFindBtn(JButton findBtn) {
		AppointmentDialog.findBtn = findBtn;
	}

	/**
	 * Used to get Change Button Object
	 * @return JButton Object
	 */
	public static JButton getChangeBtn() {
		return changeBtn;
	}

	/**
	 * Used to set Change Button Object
	 * @param changeBtn JButton Object
	 */
	public static void setChangeBtn(JButton changeBtn) {
		AppointmentDialog.changeBtn = changeBtn;
	}
	
	/**
	 * Used to get Delete Button Object
	 * @return JButton Object
	 */
	public static JButton getDeleteBtn() {
		return deleteBtn;
	}
	
	/**
	 * Used to set Delete Button Object
	 * @param deleteBtn JButton Object
	 */
	public static void setDeleteBtn(JButton deleteBtn) {
		AppointmentDialog.deleteBtn = deleteBtn;
	}
	
	/**
	 * Used to get Exit Button Object
	 * @return JButton Object
	 */
	public static JButton getExitBtn() {
		return exitBtn;
	}

	/**
	 * Used to set Exit Button Object
	 * @param exitBtn JButton Object
	 */
	public static void setExitBtn(JButton exitBtn) {
		AppointmentDialog.exitBtn = exitBtn;
	}
	  
	/**
	 * Method used to dispose frame object
	 */
	public static void disposeFrame()
	{
		f.dispose();
	}
	
	
}
