package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements ActionListener, MouseListener {
	
	private LYNXsys system; // define System (will hold LYNXsys instance)
	
	public Listener (LYNXsys system) {
		this.system = system; // transfer all system information into Listener
	}

	@SuppressWarnings("deprecation") // for getText() method (having issues with suggested getPassword())
	public void actionPerformed(ActionEvent e) { //handles all button pressed actions
		
		if (e.getSource()==system.getLoginScreen().getConfirm()) { // if login button pressed 
			if (system.getLoginScreen().getLoginField().getTextField().getText().equals(system.getLib().getID()) && system.getLoginScreen().getPwField().getPassField().getText().equals(system.getLib().getPassword())){
				system.enterHomeScreen(); // login to system if fields match login/password
			}
			system.getLoginScreen().getPwField().getPassField().setText(""); // reset password field
		}
		
		if (e.getSource()==InScreens.getLogoutButton()) { // if logout button pressed
			system.enterLoginScreen(); // go back to login screen
		}
		
		if (e.getSource()==InScreens.getHomeButton()) { // if home watermark is pressed (top left)
			system.enterHomeScreen(); // go to home screen
		}
		
		if (e.getSource()==InScreens.getBookHubButton()) { // if bookhub option is pressed (top right)
			system.enterBookScreen(); // go to bookhub screen
		}
		
		if (e.getSource()==InScreens.getInfoCentreButton()) { // if infocentre option is pressed (top right)
			system.enterInfoCentreScreen(); // go to infocentre screen
		}
		
		if (e.getSource()==InScreens.getSocialNetworkButton()) { // if socialnetwork option is pressed (top right)
			system.enterSocialNetworkScreen(); // go to social network screen
		}
		
		if (e.getSource() == system.getBookScreen().getSeeAllButton()) { // if all button on books
			system.enterBookScreen(); // show all books
		}
		
		if (e.getSource() == system.getInfoCentreScreen().getSeeAllButton()) { // if all button on infocentre
			system.enterInfoCentreScreen(); // show all students
		}
		
		if (e.getSource()==system.getBookScreen().getSearchButton()) { // if search button is pressed in bookhub screen
			if (system.getBookScreen().getSearchByBookOption().isSelected()) { // search by book title case...
				system.getBookScreen().refreshSearches(); // refresh search field
				system.getBookScreen().searchByBookTitle(system, system.getBookScreen().getSearchField().getTextField().getText(),1); // do search
			}
			else if (system.getBookScreen().getSearchByAuthorOption().isSelected()) { // search by book author case...
				system.getBookScreen().refreshSearches();
				system.getBookScreen().searchByAuthor(system, system.getBookScreen().getSearchField().getTextField().getText(),1);
			}
			else if (system.getBookScreen().getSearchByCategoryOption().isSelected()) { // search by category case...
				system.getBookScreen().refreshSearches();
				system.getBookScreen().searchByCategory(system, system.getBookScreen().getSearchField().getTextField().getText(),1);
			}
		}
		
		if (e.getSource()==system.getInfoCentreScreen().getSearchButton()) { // if search button is pressed in infocentre screen
			if (system.getInfoCentreScreen().getSearchByNameOption().isSelected()) { // search by Name case...
				system.getInfoCentreScreen().refreshSearches();
				system.getInfoCentreScreen().searchByName(system, system.getInfoCentreScreen().getSearchField().getTextField().getText(),1);
			}
			else if (system.getInfoCentreScreen().getSearchByIdOption().isSelected()) { // search by book ID case...
				system.getInfoCentreScreen().refreshSearches();
				system.getInfoCentreScreen().searchByID(system, system.getInfoCentreScreen().getSearchField().getTextField().getText(),1);
			}
		}
		
		for (int i = 0; i < system.getBookScreen().getPages().size(); i++) { // if page is selected ON BOOK SCREEN
			if (e.getSource() == system.getBookScreen().getPages().get(i)) {
				system.getBookScreen().refreshSearches();
				if (system.getBookScreen().getLastSearch() == 1) { // search by book title case...
					system.getBookScreen().searchByBookTitle(system, system.getBookScreen().getSearchField().getTextField().getText(),i+1);
				}
				else if (system.getBookScreen().getLastSearch() == 2) { // search by book author case...
					system.getBookScreen().searchByAuthor(system, system.getBookScreen().getSearchField().getTextField().getText(),i+1);
				}
				else if (system.getBookScreen().getLastSearch() == 3) { // search by category case...
					system.getBookScreen().searchByCategory(system, system.getBookScreen().getSearchField().getTextField().getText(),i+1);
				}
				else if (system.getBookScreen().getLastSearch() == 0){
					system.getBookScreen().defaultBookList(system, i+1);
				}
			}
		}
		
		for (int i = 0; i < system.getInfoCentreScreen().getPages().size(); i++) { // if page is selected ON INFO SCREEN
			if (e.getSource() == system.getInfoCentreScreen().getPages().get(i)) {
				system.getInfoCentreScreen().refreshSearches();
				if (system.getInfoCentreScreen().getLastSearch() == 1) { // search by name...
					system.getInfoCentreScreen().searchByName(system, system.getInfoCentreScreen().getSearchField().getTextField().getText(),i+1);
				}
				else if (system.getInfoCentreScreen().getLastSearch() == 2) { // search by book ID...
					system.getInfoCentreScreen().searchByID(system, system.getInfoCentreScreen().getSearchField().getTextField().getText(),i+1);
				}
				else if (system.getInfoCentreScreen().getLastSearch() == 0){ // if no search... (navi pages for ALL)
					system.getInfoCentreScreen().defaultStudentList(system, i+1);
				}
			}
		}
		
		if (e.getSource()==system.getBookScreen().getCheckoutButton()) { // if checkout button on BOOKHUB SCREEN
			system.enterCheckoutBookScreen(); // go to checkout book screen
		}
		
		if (e.getSource()==system.getBookScreen().getReturnButton()) { // if return button on BOOKHUB SCREEN
			system.enterReturnBookScreen(); // go to return book screen
		}
		
		if (e.getSource()==system.getBookScreen().getAddBookButton()) { // if add button on BOOKHUB SCREEN
			system.enterAddBookScreen(); // go to add book screen
		}
		
		if (e.getSource()==system.getBookScreen().getRemoveBookButton()) { // if remove button on BOOKHUB SCREEN 
			system.enterRemoveBookScreen(); // go to remove book screen
		}		
		
		if (e.getSource()==system.getViewBookScreen().getCheckoutButton()) { // if checkout button on VIEWBOOK SCREEN
			if (system.getViewBookScreen().getBook().getAvailable().equalsIgnoreCase("Y")) {
				system.enterCheckoutBookScreen(system.getViewBookScreen().getBook()); // go to checkout book screen
			}
		}
		
		if (e.getSource()==system.getViewBookScreen().getReturnButton()) { // if return button on VIEWBOOK SCREEN
			if (system.getViewBookScreen().getBook().getAvailable().equalsIgnoreCase("N")) {
				system.enterReturnBookScreen(system.getViewBookScreen().getBook()); // go to return book screen
			}
		}
		
		if (e.getSource()==system.getViewBookScreen().getAddButton()) { // if add button on VIEWBOOK SCREEN
			system.enterAddBookScreen(system.getViewBookScreen().getBook()); // go to add book screen
		}
		
		if (e.getSource()==system.getViewBookScreen().getRemoveButton()) { // if remove button on VIEWBOOK SCREEN 
			system.enterRemoveBookScreen(system.getViewBookScreen().getBook()); // go to remove book screen
		}
		
		if (e.getSource() == system.getInfoCentreScreen().getAddStudentButton()) { // if add button on INFO CENTRE
			system.enterAddStudentScreen();
		}
		if(e.getSource() == system.getInfoCentreScreen().getRemoveStudentButton()) { // if remove button INFO CENTRE
			system.enterRemoveStudentScreen();
		}
		
		if(e.getSource()==BookSubScreens.getBack() || e.getSource()==system.getViewBookScreen().getBack()) { // if back pressed on BookHub subscreens...
			system.enterBookScreen(); // go back to bookhub screen
		}
		if(e.getSource()==InfoCentreSubScreens.getBack() || e.getSource()==system.getViewStudentScreen().getBack()) { // if back pressed on InfoCentre subscreens...
			system.enterInfoCentreScreen(); // go back to InfoCentre screen
		}

		if(e.getSource()==system.getCheckoutBookScreen().getConfirm()) { // IF CHECKOUT BOOK IS REQUESTED (after filling in fields)
			system.getCheckoutBookScreen().checkFields(system);
		}
		
		if(e.getSource()==system.getReturnBookScreen().getConfirm()) { // IF RETURN BOOK IS REQUESTED (after filling in fields)
			system.getReturnBookScreen().checkFields(system);
		}
		
		if(e.getSource()==system.getAddBookScreen().getConfirm()) { // IF ADD BOOK IS REQUESTED (after filling in fields)
			system.getAddBookScreen().checkFields(system);
		}
		
		if(e.getSource()==system.getRemoveBookScreen().getConfirm() ) { // IF REMOVE BOOK IS REQUESTED (after filling in fields)
			system.getRemoveBookScreen().checkFields(system);
		}
		
		if (e.getSource()==system.getAddStudentScreen().getConfirm()) { // IF ADD STUDENT IS REQUESTED (after filling fields)
			system.getAddStudentScreen().checkFields(system);
		}
		
		if (e.getSource()==system.getRemoveStudentScreen().getConfirm()) { // IF REMOVE STUDENT IS REQUESTED (after filling fields)
			system.getRemoveStudentScreen().checkFields(system);
		}
		
		for (int i = 0; i < system.getBookScreen().getDisplayedSearchResults().size(); i++) { // if small book icon is clicked ...
			if (e.getSource()==system.getBookScreen().getDisplayedSearchResults().get(i).getBook()) {
				system.enterViewBookScreen(system.getBookScreen().getDisplayedSearchResults().get(i).getBookReference());
			}
		}
		
		for (int i = 0; i < system.getViewStudentScreen().getUserBooks().size(); i++) { // loop through all mini book buttons
			if (e.getSource()==system.getViewStudentScreen().getUserBooks().get(i).getBook()) { // if one of these is clicked...
				system.enterViewBookScreen(system.getViewStudentScreen().getUserBooks().get(i).getBookReference()); // go to that book's specific page
			}
		}
		
		for (int i = 0; i < system.getInfoCentreScreen().getDisplayedSearchResults().size(); i++) { 
			if (e.getSource()==system.getInfoCentreScreen().getDisplayedSearchResults().get(i).getUserButton()) { // if small user icon is clicked... 
				system.enterViewStudentScreen(system.getInfoCentreScreen().getDisplayedSearchResults().get(i).getUserReference()); // go to specific student's page
			}
		}
		
		if (e.getSource() == system.getViewStudentScreen().getEditButton()) {
			system.getViewStudentScreen().getUser().setFines(0);
			system.enterViewStudentScreen(system.getViewStudentScreen().getUser());
		}

	}

	public void mouseEntered(MouseEvent e) { // if mouse is hovering over top right options...
		if (e.getSource()==InScreens.getBookHubButton()) { // animate BookHub option
			system.getContentPane().remove(InScreens.getOptionsUnselected());
			system.getContentPane().add(InScreens.getBookHubSelected(),0);
			system.getContentPane().repaint(755,3,213,125);
		}
		if (e.getSource()==InScreens.getInfoCentreButton()) { // animate InfoCentre option
			system.getContentPane().remove(InScreens.getOptionsUnselected());
			system.getContentPane().add(InScreens.getInfoCentreSelected(),0);
			system.getContentPane().repaint(755,3,213,125);
		}
		if (e.getSource()==InScreens.getSocialNetworkButton()) { // animate SocialNetwork option
			system.getContentPane().remove(InScreens.getOptionsUnselected());
			system.getContentPane().add(InScreens.getNetworkSelected(),0);
			system.getContentPane().repaint(755,3,213,125);
		}
	}

	public void mouseExited(MouseEvent e) { // if mouse stops hovering over top right options...
		if (e.getSource()==InScreens.getBookHubButton()) { // animate BookHub option
			system.getContentPane().remove(InScreens.getBookHubSelected());
			system.getContentPane().add(InScreens.getOptionsUnselected(),0);
			system.getContentPane().repaint(755,3,213,125);
		}
		if (e.getSource()==InScreens.getInfoCentreButton()) { // animate InfoCentre option
			system.getContentPane().remove(InScreens.getInfoCentreSelected());
			system.getContentPane().add(InScreens.getOptionsUnselected(),0);
			system.getContentPane().repaint(755,3,213,125);
		}
		if (e.getSource()==InScreens.getSocialNetworkButton()) { // animate SocialNetwork option
			system.getContentPane().remove(InScreens.getNetworkSelected());
			system.getContentPane().add(InScreens.getOptionsUnselected(),0);
			system.getContentPane().repaint(755,3,213,125);
		}
	}
	
	//GETTERS AND SETTERS
	public LYNXsys getSystem() {
		return system;
	}

	public void setSystem(LYNXsys system) {
		this.system = system;
	}
	
	
	//UNUSED METHODS
	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	public void mouseClicked(MouseEvent arg0) {
		
	}
}
