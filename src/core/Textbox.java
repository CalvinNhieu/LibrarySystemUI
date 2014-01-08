package core;
import javax.swing.*;


public class Textbox {

	private ImageIcon boxImage; // IMAGE orange border around textbox
	private JLabel box; //  label to hold image ^
	private JTextField textField; // textfield to type text
	private JPasswordField passField; // password field to type passwords
	
	public Textbox (int x, int y, int width, int height) { // constructor
		// init vars
		boxImage = new ImageIcon(getClass().getResource("/Resources/Textbox1.png"));
		box = new JLabel(boxImage);
		textField = new JTextField();
		passField = new JPasswordField();
		
		// set bounds
		box.setBounds(x,y,width,height);
		textField.setBounds(x+3,y+2,width-6,height-4);
		passField.setBounds(x+3,y+2,width-6,height-4);
		textField.setBorder(null);
		passField.setBorder(null);
		
	}
	
	// GETTERS AND SETTERS
	public ImageIcon getBoxImage() {
		return boxImage;
	}

	public void setBoxImage(ImageIcon boxImage) {
		this.boxImage = boxImage;
	}

	public JLabel getBox() {
		return box;
	}

	public void setBox(JLabel box) {
		this.box = box;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JPasswordField getPassField() {
		return passField;
	}

	public void setPassField(JPasswordField passField) {
		this.passField = passField;
	}
	
}
