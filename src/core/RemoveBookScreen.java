package core;

import java.awt.Graphics;

public class RemoveBookScreen extends BookSubScreens { 

	private static final long serialVersionUID = 1L; // suggested

	public RemoveBookScreen (LYNXsys system) { // constructor
		super(system); // call super class
		getConfirm().setText("Remove"); // modify button text
		
		// add components
		add(getLostHeader(),0); 
		add(getYes(),0);
		add(getNo(),0);
		add(getStudentNumberBox().getTextField());
		add(getStudentNumberBox().getBox(),0);
		add(getStudentHeader(),0);
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void checkFields(LYNXsys system) { // check textfields method
		for (int i = 0; i < system.getBooks().size(); i++) { // loop through all books
			if (getBookTitle().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getTitle()) && //check if sufficient book information is provided (ALL INFO MUST BE PRODVIDED)
					getAuthorFirstName().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getAuthorFirstName()) &&
						getAuthorLastName().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getAuthorLastName()) &&
								getCategory().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getCategory()) && 
										getIsbn().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getIsbn()) &&
										Double.parseDouble(getCost().getTextField().getText()) == (system.getBooks().get(i).getCost()) &&
												getRating().getTextField().getText().equalsIgnoreCase(system.getBooks().get(i).getRating())) { // end check...
				if (getYes().isSelected()) { // if book is lost...
					for (int j = 0; j < system.getUsers().size(); j++) { // loop through all users
						if (getStudentNumberBox().getTextField().getText().equalsIgnoreCase(system.getUsers().get(j).getID())) { // see if input is valid user
							// call lostBook method
							system.removeLostBook(system.getBooks().get(i), system.getUsers().get(j));
						}
					}
				}
				else if (getNo().isSelected()) { // if book is not lost
					// call normal remove method
					system.removeBook(system.getBooks().get(i));
				}
			}
		}
	}
}
