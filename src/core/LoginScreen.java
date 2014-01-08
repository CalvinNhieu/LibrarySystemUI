package core;

import java.awt.Graphics;

import javax.swing.*;


public class LoginScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel bkg;//background image
	private JLabel panel1;//top right panel image (over)
	private JLabel panel2;//top right panel image (under)
	private JLabel readLearnConnect;//text in top right
	
	private Textbox loginField; // textbox 1
	private Textbox pwField; // textbox 2
	
	private JButton confirm; //login button
	
	private Listener action; //action listener object

	
	public LoginScreen(LYNXsys system) {
		
		setLayout(null);
		
		// initialize variables
		action = new Listener (system);
		
		bkg = new JLabel (new ImageIcon (getClass().getResource("/Resources/LoginBackground.png"))); //inst JLabel image
		panel1 = new JLabel (new ImageIcon (getClass().getResource("/Resources/Light Panel.png")));
		panel2 = new JLabel (new ImageIcon (getClass().getResource("/Resources/Dark Panel.png")));
		readLearnConnect = new JLabel (new ImageIcon (getClass().getResource("/Resources/TRO Login.png")));
		confirm = new JButton ("Login");
		
		// set bounds 
		loginField = new Textbox (385,316,349,32);
		pwField = new Textbox (385,387,349,32);
		
		bkg.setBounds(0,0,1000,618);
		panel1.setBounds(652,0,348,136);
		panel2.setBounds(625,10,348,136);
		readLearnConnect.setBounds(809,3,160,125);
		confirm.setBounds(740, 387, 80, 32);
		
		// add components to panel
		add(confirm);
		
		add(readLearnConnect,0);
		add(loginField.getTextField());
		add(pwField.getPassField());
		add(loginField.getBox(),0);
		add(pwField.getBox(),0);
		add(panel1);
		add(panel2);
		add(bkg);
		
		// add action listeners
		confirm.addActionListener(action);
	
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}

	

	// GETTERS AND SETTERS
	public JLabel getBkg() {
		return bkg;
	}

	public void setBkg(JLabel bkg) {
		this.bkg = bkg;
	}


	public JLabel getReadLearnConnect() {
		return readLearnConnect;
	}

	public void setReadLearnConnect(JLabel readLearnConnect) {
		this.readLearnConnect = readLearnConnect;
	}

	public JLabel getPanel1() {
		return panel1;
	}



	public void setPanel1(JLabel panel1) {
		this.panel1 = panel1;
	}



	public JLabel getPanel2() {
		return panel2;
	}



	public void setPanel2(JLabel panel2) {
		this.panel2 = panel2;
	}



	public Textbox getLoginField() {
		return loginField;
	}



	public void setLoginField(Textbox loginField) {
		this.loginField = loginField;
	}



	public Textbox getPwField() {
		return pwField;
	}



	public void setPwField(Textbox pwField) {
		this.pwField = pwField;
	}



	public JButton getConfirm() {
		return confirm;
	}



	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}



	public Listener getAction() {
		return action;
	}



	public void setAction(Listener action) {
		this.action = action;
	}
	
}
