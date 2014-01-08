package core;

import java.awt.Graphics;
import java.util.Scanner;

public class ReturnBookScreen extends BookSubScreens{

	private static final long serialVersionUID = 1L;

	public ReturnBookScreen (LYNXsys system) { // constructor
		super(system); // call super class
		getConfirm().setText("Return"); // modify text
		
		// add appr. components
		add(getStudentNumberBox().getTextField()); 
		add(getStudentNumberBox().getBox(),0);
		add(getStudentHeader(),0);
		add(getDate().getTextField());
		add(getDate().getBox(),0);
		add(getDateHeader(),0);
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void checkFields(LYNXsys system) { // method to check textfields
		for (int j = 0; j < system.getUsers().size(); j++) { // loop through all users
			if (getStudentNumberBox().getTextField().getText().equalsIgnoreCase(system.getUsers().get(j).getID())) { // see if input is valid user
				for (int i = 0; i < system.getBooks().size(); i++) { // loop through all books
					if (getBookTitle().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getTitle()) && //check if sufficient book information is provided (see checkout book screen)
							getAuthorFirstName().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getAuthorFirstName()) &&
								getAuthorLastName().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getAuthorLastName()) &&
										(getCategory().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getCategory()) ||
												getCategory().getTextField().getText().equals("")) && 
												getIsbn().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getIsbn()) &&
												(Double.parseDouble(getCost().getTextField().getText()) == (system.getBooks().get(i).getCost()) || 
														getCost().getTextField().getText().equals("")) &&
														(getRating().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getRating()) || 
																getRating().getTextField().getText().equals("")) &&
																system.getBooks().get(i).getAvailable().equalsIgnoreCase("N") &&
																!(getDate().getTextField().getText().equalsIgnoreCase("")) &&
																!(getDate().getTextField().getText().equalsIgnoreCase("MM/DD/YYYY"))) { //end check...
						for (int k = 0; k < system.getUsers().get(j).getBooks().size(); k++) { // loop through user's current books
							if (system.getUsers().get(j).getBooks().get(k).equals(system.getBooks().get(i))) { // check if user actually has book to be returned or not
								system.returnBook(system.getBooks().get(i), system.getUsers().get(j), findMonth(getDate().getTextField().getText()), findDay(getDate().getTextField().getText()), findYear(getDate().getTextField().getText())); // return book by specific user
							}
						}
					}
				}
			}
		}
	}
	
	private int findDay (String input) {
		Scanner s = new Scanner (input);
		int[] date = new int[3]; 
		s.useDelimiter("/");
		while(s.hasNext()) {
			for (int i = 0; i < date.length; i++) {
				date[i] = s.nextInt();
			}
		}
		s.close();
		return date[1];
	}
	
	private int findMonth (String input) {
		Scanner s = new Scanner (input);
		int[] date = new int[3]; 
		s.useDelimiter("/");
		while(s.hasNext()) {
			for (int i = 0; i < date.length; i++) {
				date[i] = s.nextInt();
			}
		}
		s.close();
		return date[0];
	}
	
	private int findYear (String input) {
		Scanner s = new Scanner (input);
		int[] date = new int[3]; 
		s.useDelimiter("/");
		while(s.hasNext()) {
			for (int i = 0; i < date.length; i++) {
				date[i] = s.nextInt();
			}
		}
		s.close();
		return date[2];
	}
	
}
