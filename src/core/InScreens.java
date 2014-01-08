package core;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InScreens extends JPanel{
	
	private static final long serialVersionUID = 1L;

	static private JLabel bkg; // background image (on every screen)
	static private JLabel panel1; // top right panel image (over)
	static private JLabel panel2; // top right panel image (under)
	static private JLabel optionsUnselected; // image if top right options are not touched
	static private JLabel bookHubSelected; // image if bookub is hovered over
	static private JLabel infoCentreSelected; // image if infocentre is hovered over
	static private JLabel networkSelected; // image if network option is hovered over
	

	static private JButton bookHubButton; // button to go to bookhub
	static private JButton infoCentreButton; // to go to info centre
	static private JButton socialNetworkButton; // to go to social network
	static private JButton homeButton; // to go back home (top left)
	static private JButton logoutButton; // button to logout
	
	static private Listener action; // action listener used for all components in program
	
	
	public InScreens (LYNXsys system) { // constructor
		
		setLayout(null); // NO LAYOUT
		
		// init variables
		action = new Listener (system); // action listener
		
		// labels
		bkg = new JLabel (new ImageIcon(getClass().getResource("/Resources/Background.png")));
		panel1 = new JLabel (new ImageIcon (getClass().getResource("/Resources/Light Panel.png")));
		panel2 = new JLabel (new ImageIcon (getClass().getResource("/Resources/Dark Panel.png")));
		optionsUnselected = new JLabel (new ImageIcon (getClass().getResource("/Resources/TRO Top Right Options.png")));
		bookHubSelected = new JLabel (new ImageIcon (getClass().getResource("/Resources/TRO BookHub.png")));
		infoCentreSelected = new JLabel (new ImageIcon (getClass().getResource("/Resources/TRO InfoCentre.png")));
		networkSelected = new JLabel (new ImageIcon (getClass().getResource("/Resources/TRO SocialNetwork.png")));
		//buttons
		bookHubButton = new JButton ();
		infoCentreButton = new JButton ();
		socialNetworkButton = new JButton ();
		homeButton = new JButton ();
		logoutButton = new JButton("Logout");
		
		//labels
		bkg.setBounds(0,0,1000,618);
		panel1.setBounds(652,0,348,136);
		panel2.setBounds(625,10,348,136);
		optionsUnselected.setBounds(755,3,213,125);
		bookHubSelected.setBounds(755,3,213,125);
		infoCentreSelected.setBounds(755,3,213,125);
		networkSelected.setBounds(755,3,213,125);
		//buttons
		bookHubButton.setBounds(830,5,145,40); // set bounds
		bookHubButton.setBackground(new Color(0f, 0f, 0f, 0f)); // set colourless
		bookHubButton.setBorder(null); // no border
		bookHubButton.setOpaque(false); // transparnent 
		
		infoCentreButton.setBounds(800,48,175,40);
		infoCentreButton.setBackground(new Color(0f, 0f, 0f, 0f));
		infoCentreButton.setBorder(null);
		infoCentreButton.setOpaque(false);
		
		socialNetworkButton.setBounds(750,91,225,40);
		socialNetworkButton.setBackground(new Color(0f, 0f, 0f, 0f));
		socialNetworkButton.setBorder(null);
		socialNetworkButton.setOpaque(false);
		
		homeButton.setBounds(10,10,260,110);
		homeButton.setBackground(new Color(0f, 0f, 0f, 0f));
		homeButton.setBorder(null);
		homeButton.setOpaque(false);
		
		logoutButton.setBounds(20,520,75,32);
		
		// add components
		// buttons
		add(homeButton);
		add(bookHubButton);
		add(infoCentreButton);
		add(socialNetworkButton);
		add(logoutButton,0);
		add(optionsUnselected);
		// images
		add(panel1);
		add(panel2);
		add(bkg);

		// add mouse listeners
		bookHubButton.addMouseListener(action);
		infoCentreButton.addMouseListener(action);
		socialNetworkButton.addMouseListener(action);
		// add action listeners
		bookHubButton.addActionListener(action);
		infoCentreButton.addActionListener(action);
		socialNetworkButton.addActionListener(action);
		homeButton.addActionListener(action);
		logoutButton.addActionListener(action);
		
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	// GETTERS AND SETTERS
	public static JButton getLogoutButton() {
		return logoutButton;
	}

	public static void setLogoutButton(JButton logoutButton) {
		InScreens.logoutButton = logoutButton;
	}
	
	public static JButton getHomeButton() {
		return homeButton;
	}

	public static void setHomeButton(JButton homeButton) {
		InScreens.homeButton = homeButton;
	}

	public static JButton getBookHubButton() {
		return bookHubButton;
	}

	public static void setBookHubButton(JButton bookHubButton) {
		InScreens.bookHubButton = bookHubButton;
	}

	public static JButton getInfoCentreButton() {
		return infoCentreButton;
	}

	public static void setInfoCentreButton(JButton infoCentreButton) {
		InScreens.infoCentreButton = infoCentreButton;
	}

	public static JButton getSocialNetworkButton() {
		return socialNetworkButton;
	}

	public static void setSocialNetworkButton(JButton networkButton) {
		InScreens.socialNetworkButton = networkButton;
	}

	public static JLabel getBkg() {
		return bkg;
	}

	public static void setBkg(JLabel bkg) {
		InScreens.bkg = bkg;
	}

	public static JLabel getPanel1() {
		return panel1;
	}

	public static void setPanel1(JLabel panel1) {
		InScreens.panel1 = panel1;
	}

	public static JLabel getPanel2() {
		return panel2;
	}

	public static void setPanel2(JLabel panel2) {
		InScreens.panel2 = panel2;
	}

	public static JLabel getOptionsUnselected() {
		return optionsUnselected;
	}

	public static void setOptionsUnselected(JLabel optionsUnselected) {
		InScreens.optionsUnselected = optionsUnselected;
	}

	public static JLabel getBookHubSelected() {
		return bookHubSelected;
	}

	public static void setBookHubSelected(JLabel bookHubSelected) {
		InScreens.bookHubSelected = bookHubSelected;
	}

	public static JLabel getInfoCentreSelected() {
		return infoCentreSelected;
	}

	public static void setInfoCentreSelected(JLabel infoCentreSelected) {
		InScreens.infoCentreSelected = infoCentreSelected;
	}

	public static JLabel getNetworkSelected() {
		return networkSelected;
	}

	public static void setNetworkSelected(JLabel networkSelected) {
		InScreens.networkSelected = networkSelected;
	}

	public static Listener getAction() {
		return action;
	}

	public static void setAction(Listener action) {
		InScreens.action = action;
	}
	
}
