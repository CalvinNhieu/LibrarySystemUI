package core;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ViewBookScreen extends InScreens{

	private static final long serialVersionUID = 1L;
	
	private Book book; // holds the book that is being viewed
	private JTextArea display; // holds String to display book's info
	private JLabel bookImage; // image on the left
	
	private JButton back; // back button
	private JButton checkoutButton; // to checkout book 
	private JButton returnButton; // to return a book
	private JButton addButton; // to add a new copy of this book
	private JButton removeButton; // to remove this book
	
	public ViewBookScreen (LYNXsys system, Book book) { // constructor
		super(system); // call super class
		
		// init vars
		this.book = book;
		bookImage = new JLabel (new ImageIcon (getClass().getResource("/Resources/Big Book Icon.png")));
		back = new JButton ("<<");
		checkoutButton = new JButton ("Checkout");
		returnButton = new JButton ("Return");
		addButton = new JButton ("Add");
		removeButton = new JButton ("Remove");
		
		display = new JTextArea ("\n     Title: " + book.getTitle() + "\n\n     Author: " + book.getAuthorFirstName() + " " + book.getAuthorLastName() + "\n\n     Category: " + book.getCategory() + "\n\n     Available: " + book.getAvailable() + "\n\n     ISBN: " + book.getIsbn() + "\n\n     Cost: $" + book.getCost() + "\n\n     Rating: " + book.getRating() + "\n\n     Date Out: " + book.getMonthOut() + "/" + book.getDayOut() + "/" + book.getYearOut());
		display.setEditable(false);
		display.setBackground(Color.pink);
		
		// set bounds
		bookImage.setBounds(200,250,221,242);
		display.setBounds(550,250,200,275);
		back.setBounds(30,200,50,32);
		checkoutButton.setBounds(800,260,150,30);
		returnButton.setBounds(800,330,150,30);
		addButton.setBounds(800,400,150,30);
		removeButton.setBounds(800,470,150,30);
		
		// add components 
		add(back,0);
		add(bookImage,0);
		add(display,0);
		add(checkoutButton,0);
		add(returnButton,0);
		add(addButton,0);
		add(removeButton,0);
		
		// action listeners
		back.addActionListener(getAction());
		checkoutButton.addActionListener(getAction());
		returnButton.addActionListener(getAction());
		addButton.addActionListener(getAction());
		removeButton.addActionListener(getAction());
		
		
	}
	
	public void paintComponent (Graphics g) { // paint method
		super.paintComponent(g);
	}

	// GETTERS AND SETTERS
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public JTextArea getDisplay() {
		return display;
	}

	public void setDisplay(JTextArea display) {
		this.display = display;
	}

	public JLabel getBookImage() {
		return bookImage;
	}

	public void setBookImage(JLabel bookImage) {
		this.bookImage = bookImage;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

	public JButton getCheckoutButton() {
		return checkoutButton;
	}

	public void setCheckoutButton(JButton checkoutButton) {
		this.checkoutButton = checkoutButton;
	}

	public JButton getReturnButton() {
		return returnButton;
	}

	public void setReturnButton(JButton returnButton) {
		this.returnButton = returnButton;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public JButton getRemoveButton() {
		return removeButton;
	}

	public void setRemoveButton(JButton removeButton) {
		this.removeButton = removeButton;
	}

	
	
}
