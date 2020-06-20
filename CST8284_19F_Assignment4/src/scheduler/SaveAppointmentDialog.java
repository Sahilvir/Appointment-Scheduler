package scheduler;

import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


/**
 * Class contains GUI to save Appointment
 * @author Dhillon Sahilvir
 * @version 1.0
 */
public class SaveAppointmentDialog {
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
	    	GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 1, 1);
		
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
	private static final int labelWidth = 40;
	
	/**
	 * Font for Text
	 */
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 20);	
	
	/**
	 * Used to get Name of Appointed Person
	 */
	private static JTextField nameTxt;
	
	/**
	 * Used to get Phone Number of Appointed Person
	 */
	private static JTextField phoneTxt;
	
	/**
	 * Used to get date of Appointment
	 */
	private static JTextField dateTxt;
	
	/**
	 * Used to get time of Appointment
	 */
	private static JTextField timeTxt;
	
	/**
	 * Used to get description of Appointment
	 */
	private static JTextField desriptionTxt;
	
	/**
	 * Used to show user, the list of available activities
	 */
	private static JComboBox activityType;
	
	/**
	 * Contains list of Available Activities
	 */
	private static ArrayList<String> types = new ArrayList<>();
	
	
	/**
	 * Button for go to Back Page
	 */
	private static JButton backBtn = new JButton("Back");
	
	/**
	 * Button to save Appointment
	 */
	private static JButton saveBtn = new JButton("Save");
	
	/**
	 * Button for Exit
	 */
	private static JButton exitBtn = new JButton("Exit");

	
	/**
	 * Used to set list of available activities
	 * @param descriptionList contains list of available activities
	 */
	public static void setActvityTypes( ArrayList<String> descriptionList)
	{
		types = descriptionList;
	}
	
	/**
	 * Used to get list of available activities
	 * @return JComboBox with Available Activities
	 */
	public static JComboBox getActivityType() {
		return activityType;
	}
	
	/**
	 * Used to set Activity type
	 * @param activityType JComboBox with Available Activities
	 */
	public static void setActivityType(JComboBox activityType) {
		SaveAppointmentDialog.activityType = activityType;
	}

	
	/**
	 * Method to develop comboBox which contains all available activities 
	 */
	private static void setComboBox()
	{
		activityType = new JComboBox();
	    
		for(String type:types)
			activityType.addItem(type);
	    
	    cp.add(activityType, labelConstants);
	}
	
	/**
	 * Method used to set buttons on GUI
	 */
	private static void setButtonPanel()
	{
		GridLayout grid = new GridLayout(1,3, 30, 0);
		JPanel panel = new JPanel(grid);
		
		SaveAppointmentDialogEventHandler hnd = new SaveAppointmentDialogEventHandler();
		
		saveBtn.addActionListener(hnd);
		backBtn.addActionListener(hnd);
		exitBtn.addActionListener(hnd);
		
		panel.add(saveBtn);
		panel.add(backBtn);
		panel.add(exitBtn);
		
		cp.add(panel, labelConstants);
		
	}
	
	/**
	 * Method used to Display GUI on calling after properly setting it
	 */
	public static void showAppointmentDialog(){
	    f = new JFrame("Get, set, change or delete an appointment");  
	    f.setLocation(400, 100);
	    
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());

	    nameTxt = setRow("Enter Client Name (as FirstName LastName):", 'n');
	    phoneTxt = setRow("Phone Number (e.g. 613-555-1212):", 'p');
	    dateTxt = setRow("Appointment Date (entered as DDMMYYYY):", 'd');
	    timeTxt = setRow("Appointment Time:", 't');
	    desriptionTxt = setRow("Activity Description", 'a');
	
	    
	    
	    setComboBox();
	    
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
	 * Method used to add components on GUI
	 * @param label String represents label text
	 * @param keyboardShortcut char represents keyBoardshortcut 
	 * @return JTextField representing TextField on GUI
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
	 * Used to get Name TextField
	 * @return JTextField representing Name TextField  
	 */
	public static JTextField getNameTxt() {
		return nameTxt;
	}

	/**
	 * Used to set Name TextField
	 * @param nameTxt JTextField representing Name TextField
	 */
	public static void setNameTxt(JTextField nameTxt) {
		SaveAppointmentDialog.nameTxt = nameTxt;
	}
	
	/**
	 * Used to get Phone TextField
	 * @return JTextField representing Phone TextField
	 */
	public static JTextField getPhoneTxt() {
		return phoneTxt;
	}
	
	
	/**
	 * Used to set Phone TextField
	 * @param phoneTxt JTextField representing Phone TextField
	 */
	public static void setPhoneTxt(JTextField phoneTxt) {
		SaveAppointmentDialog.phoneTxt = phoneTxt;
	}

	/**
	 * Used to get Date TextField
	 * @return JTextField representing Date TextField
	 */
	public static JTextField getDateTxt() {
		return dateTxt;
	}

	/**
	 * Used to set Date TextField
	 * @param dateTxt JTextField representing Date TextField
	 */
	public static void setDateTxt(JTextField dateTxt) {
		SaveAppointmentDialog.dateTxt = dateTxt;
	}

	/**
	 * Used to get Time TextField
	 * @return JTextField representing Time TextField
	 */
	public static JTextField getTimeTxt() {
		return timeTxt;
	}
	

	
	
	/**
	 * Method used to get JButton object representing back button
	 * @return JButton representing Back Button
	 */
	public static JButton getBackBtn() {
		return backBtn;
	}

	/**
	 * Method used to set JButton object representing back button
	 * @param backBtn JButton representing Back Button
	 */
	public static void setBackBtn(JButton backBtn) {
		SaveAppointmentDialog.backBtn = backBtn;
	}

	/**
	 * Method used to get JButton object representing save button
	 * @return JButton representing save Button
	 */
	public static JButton getSaveBtn() {
		return saveBtn;
	}

	/**
	 * Method used to set JButton object representing save button
	 * @param saveBtn JButton representing save Button
	 */
	public static void setSaveBtn(JButton saveBtn) {
		SaveAppointmentDialog.saveBtn = saveBtn;
	}

	
	/**
	 * Method used to get JButton object representing exit button
	 * @return JButton representing exit Button
	 */
	public static JButton getExitBtn() {
		return exitBtn;
	}

	/**
	 * Method used to set JButton object representing exit button
	 * @param exitBtn JButton representing exit Button
	 */
	public static void setExitBtn(JButton exitBtn) {
		SaveAppointmentDialog.exitBtn = exitBtn;
	}
	
	/**
	 * Method used to set JTextField object representing time
	 * @param timeTxt JTextField representing Time TextField
	 */
	public static void setTimeTxt(JTextField timeTxt) {
		SaveAppointmentDialog.timeTxt = timeTxt;
	}

	/**
	 * Used to get Description TextField
	 * @return JTextField representing Description TextField
	 */
	public static JTextField getDesriptionTxt() {
		return desriptionTxt;
	}

	/**
	 * Used to set Description TextField
	 * @param desriptionTxt JTextField representing Description TextField
	 */
	public static void setDesriptionTxt(JTextField desriptionTxt) {
		SaveAppointmentDialog.desriptionTxt = desriptionTxt;
	}
	
	/**
	 * Method used to dispose frame
	 */
	public static void disposeFrame()
	{
		f.dispose();
	}
}
