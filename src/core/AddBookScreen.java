package core;

import java.awt.Graphics;

public class AddBookScreen extends BookSubScreens{

	private static final long serialVersionUID = 1L; // suggested...

	public AddBookScreen (LYNXsys system) { //constructor
		super(system); // call super class
		getConfirm().setText("Add"); // edit text in Add button
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void checkFields(LYNXsys system) { // method to check textfields before adding book
		if (!(getBookTitle().getTextField().getText().equals("")) && //check if ALL fields are provided
			!(getAuthorFirstName().getTextField().getText().equals("")) &&
				!(getAuthorLastName().getTextField().getText().equals("")) &&
					!(getCategory().getTextField().getText().equals("")) && 
						!(getIsbn().getTextField().getText().equals("")) &&
							!(getCost().getTextField().getText().equals("")) &&
								!(getRating().getTextField().getText().equals(""))) { // end check...
			// add a book ...
			system.addBook(new Book(getBookTitle().getTextField().getText(), getAuthorFirstName().getTextField().getText(), getAuthorLastName().getTextField().getText(), getIsbn().getTextField().getText(), getCategory().getTextField().getText(), Double.parseDouble(getCost().getTextField().getText()),getRating().getTextField().getText(),"Y"));
		}
	}
	
}