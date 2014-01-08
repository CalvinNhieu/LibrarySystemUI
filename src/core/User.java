package core;

import java.util.ArrayList;

public class User {
	
	// define and init final variables
	private static final int MAX_NUMBER_OF_BOOKS = 3; // max number of books a student can hold...
	
	// define variables
	private String firstName; // user's first name
	private String lastName; // user's last name
	private String ID; // user's Student Number/ID
	private String password; // user's password for external system
	private double fines; // user's fines
	
	private ArrayList<Book> books = new ArrayList<Book>(); // arraylist of books that user has checked out
	
	public User(String firstName, String lastName, String ID, String password) { // constructor
		// init variables
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
		this.password = password;
		
		fines = 0;
	}

	
	// GETTERS AND SETTERS
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getFines() {
		return fines;
	}

	public void setFines(double fines) {
		this.fines = fines;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public static int getMaxNumberOfBooks() {
		return MAX_NUMBER_OF_BOOKS;
	}
	
}
