package core;

/*  CALVIN NHIEU'S LIBRARY DATABASE SYSTEM: "LYNXsys"
 *           ------------------------------------------------------------------------------------------
 *  Functionality: Single user login (only the librarian can use the system)
 *  
 *  			   *************** LOGIN: ASAYED ************************
 *  			   **************** PASSWORD: 1 *********************
 *  
 *  			   File Input (users' and books' information is read from a .txt file,
 *  			              no files are written (no information is stored after
 *                            the runtime of the program)
 *                 Navigation: Home (top left), Books and Students (top right), Logout (bottom left)
 *           ------------------------------------------------------------------------------------------
 *                 Search Books by Title (full), Author (first/last/full), Category (full)
 *                            - search function has PAGES! (13 results per page)
 *                 Add Books (requires all fields to be filled, duplicates can be made 
 *                            - more than one copy will show up in search list)
 *                 Remove Books (requires all fields to be filled, book must exist in system
 *                 			  has option for lost book 
 *                            - lost information must be filled to remove a book
 *                            - if book is lost, student's ID must be provided to
 *                            allocate fines)
 *                 Checkout Books (requires title, author (full), isbn, date and student ID)
 *                            - student must be valid
 *                            - student cannot have must have maximum 2 books to checkout
 *                            - student cannot have fines exceeding $5.00
 *                            - book must be Available (not checked out)
 *                            - date is stored in book object
 *                 Return Books (requires title, author (full), isbn, date and student ID) 
 *                            - student must have possession of the book
 *                 View Book (can view information of each individual book in their own page)
 *                            - can checkout, remove, add, return books from this screen for 
 *                            ease of filling in fields
 *                            - all information about the book including date checked out can 
 *                            be found here
 *                                         - if book is not checked out, Available will be Y 
 *                                         and date will read 0/0/0
 *           ------------------------------------------------------------------------------------------
 *                 Search Students by Name (first/last/full), ID (full)
 *                 Add Students (requires all fields to be filled, **password is for the students to access an external system**)
 *                            - duplicate students are defined by identical student numbers
 *                            - cannot add a duplicate student
 *                 Remove Students (requires Name (full) and ID)
 *                 View Student (can view information of each individual student in their own page)
 *                            - list of books checked out by student can be viewed here
 *                            - can access specific book's page from this screen
 *                 
 * 
 *           ------------------------------------------------------------------------------------------
 *           ------------------------------------------------------------------------------------------
 *                                                  BUGS
 *                                                 *    * 
 *           ------------------------------------------------------------------------------------------
 *           ------------------------------------------------------------------------------------------
 *  Text in textfields don't render (text does not show up, e.g. after clicking the search button or checking out book)
 *  However, the text is still there and the program will work properly even though you don't see the text...
 * 
 *  An Error pops up and quickly disappears in the console when the application is run... I have no idea what it is.
 *  
 *  Page numbers are not drawn on pages after page 1
 *  
 *  Poorly dummy-proofed date entering for checking out or returning a book
 *  - if MM/DD/YYYY format is not followed, fines will be miscalculated
 *  - if anything other than ##/##/#### is entered, the application will have an error
 *  
 *  NOTE: Social Network screen does nothing
 */








import java.io.FileNotFoundException;

public class Main {
	 
	 static LYNXsys system; // define new system

	public static void main (String[]args) throws FileNotFoundException { // maint method
		
	    system = new LYNXsys(); //instantiates new JFrame (window)
	    
	}
	
	//GETTERS AND SETTERS
	public static LYNXsys getSystem() {
		return system;
	}

	public static void setSystem(LYNXsys system) {
		Main.system = system;
	}
}
