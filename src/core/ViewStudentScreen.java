package core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ViewStudentScreen extends InScreens {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<BookSearchResult> userBooks; // list to hold books user has checked out
	
	private User user; // holds user to be viewed in this screen
	private JTextArea display; // holds String to display user's info
	private JLabel userImage; // image to the left
	
	private JButton back; // back button
	private JButton editButton; // button to edit student
	private JButton removeButton; // button to remove student
	
	public ViewStudentScreen (LYNXsys system, User user) { // constructor
		super(system); // call super class
		
		// init vars
		this.user = user; 
		
		userBooks = new ArrayList<BookSearchResult>();
		
		userImage = new JLabel (new ImageIcon (getClass().getResource("/Resources/Big User Icon.png")));
		back = new JButton ("<<");
		editButton = new JButton ("Reset Fine");
		removeButton = new JButton ("Remove");
		
		display = new JTextArea ("\n     Name: " + user.getFirstName() + " " + user.getLastName() + "\n\n     ID: " + user.getID() + "\n\n     Fines: $" + user.getFines() + "0");
		display.setEditable(false);
		display.setBackground(Color.pink);
		
		// set bounds
		userImage.setBounds(200,250,221,242);
		display.setBounds(550,250,200,120);
		back.setBounds(30,200,50,32);
		editButton.setBounds(800,270,150,30);
		removeButton.setBounds(800,320,150,30);
		
		// add components
		add(back,0);
		add(userImage,0);
		add(display,0);
		add(editButton,0);
		add(removeButton,0);
		
		for (int i = 0; i < user.getBooks().size(); i++) { // loop through user's book arraylist
			// create a display line for each book
			userBooks.add(new BookSearchResult(user.getBooks().get(i), user.getBooks().get(i).getTitle() + " by " + user.getBooks().get(i).getAuthorFirstName() + " " + user.getBooks().get(i).getAuthorLastName()));
			userBooks.get(i).getDisplayText().setEditable(false);
			userBooks.get(i).getDisplayText().setBackground(Color.pink);
			userBooks.get(i).getDisplayText().setBounds(610,400+(21*i),300,20);
			userBooks.get(i).getBook().setBounds(570,400+(21*i),32,20);
			add(userBooks.get(i).getDisplayText(),0);
			add(userBooks.get(i).getBook(),0);
		}
		
		// add action listeners
		back.addActionListener(getAction());
		editButton.addActionListener(getAction());
		removeButton.addActionListener(getAction());
		
		
	}
	
	public void paintComponent (Graphics g) { // paint method
		super.paintComponent(g);
	}

	// GETTERS AND SETTERS
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JTextArea getDisplay() {
		return display;
	}

	public void setDisplay(JTextArea display) {
		this.display = display;
	}

	public JLabel getUserImage() {
		return userImage;
	}

	public void setUserImage(JLabel userImage) {
		this.userImage = userImage;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public void setEditButton(JButton editButton) {
		this.editButton = editButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

	public ArrayList<BookSearchResult> getUserBooks() {
		return userBooks;
	}

	public void setUserBooks(ArrayList<BookSearchResult> userBooks) {
		this.userBooks = userBooks;
	}

}
