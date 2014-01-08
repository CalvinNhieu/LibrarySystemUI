package core;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

public class BookScreen extends InScreens { 

	private static final long serialVersionUID = 1L; // suggested...
	
	// define variables 
	private int lastSearch; // holds counter for the last search type made
	
	private ArrayList<BookSearchResult> displayedSearchResults; // search results as text to show on screen
	private ArrayList<JButton> pages; // arraylist to hold page navigation buttons
	
	private Textbox searchField; // search field textbox
	
	private JLabel searchOptions; // image to label search type options
	
	private JButton searchButton; // button to search
	private JButton seeAllButton; // button to see all
	private JButton checkoutButton; // button to move to checkout book screen
	private JButton returnButton; // button to move to return book screen
	private JButton addBookButton; // button to move to add book screen
	private JButton removeBookButton; // button to move to remove book screen
	
	private ButtonGroup searchSelectionGroup; // to group search type option radio buttons
	private JRadioButton searchByBookOption; // to search by book title
	private JRadioButton searchByAuthorOption; // to search by author name (first, last, or both)
	private JRadioButton searchByCategoryOption; // ot search by book category
	
	public BookScreen (LYNXsys system) { // constructor

		super(system); // instantiate super class (InScreens)
		
		//initialize components
		lastSearch = 0;
		displayedSearchResults = new ArrayList<BookSearchResult>();
		pages = new ArrayList<JButton>();
		pages.add(new JButton("1")); // page 1...
		
		searchField = new Textbox (475,200,349,32); 
		searchOptions = new JLabel (new ImageIcon (getClass().getResource("/Resources/BookScreen SearchBy Options.png")));
		searchButton = new JButton ("Search");
		seeAllButton = new JButton ("All");
		
		checkoutButton = new JButton ("Checkout"); //left side components (4 buttons)
		returnButton = new JButton ("Return");
		addBookButton = new JButton ("Add Book");
		removeBookButton = new JButton ("Remove Book");
		
		searchByBookOption = new JRadioButton (); //selection options (3) and group
		searchByAuthorOption = new JRadioButton ();
		searchByCategoryOption = new JRadioButton ();
		searchSelectionGroup = new ButtonGroup ();
		
		//setbounds...
		pages.get(0).setBounds(620,547,43,19);
		searchOptions.setBounds(475,240,323,19); //search components
		searchButton.setBounds(830,200,75,31);
		seeAllButton.setBounds(910,200,60,31);
		
		checkoutButton.setBounds(40,230,300,50);//left side buttons
		returnButton.setBounds(40,300,300,50);
		addBookButton.setBounds(40,370,300,50);
		removeBookButton.setBounds(40,440,300,50);
		
		searchByBookOption.setBounds(575,240,20,20);//radio buttons (3)
		searchByAuthorOption.setBounds(688,240,20,20);
		searchByCategoryOption.setBounds(803,240,20,20);
		
		searchSelectionGroup.add(searchByBookOption);
		searchSelectionGroup.add(searchByAuthorOption);
		searchSelectionGroup.add(searchByCategoryOption);
		
		//add components
		add(pages.get(0),0);
		add(searchOptions,0);
		add(searchByBookOption,0);
		add(searchByAuthorOption,0);
		add(searchByCategoryOption,0);
		add(searchField.getBox(),0);
		add(searchButton,0);
		add(seeAllButton,0);
		add(checkoutButton,0);
		add(returnButton,0);
		add(addBookButton,0);
		add(removeBookButton,0);
		add(searchField.getTextField());
		defaultBookList(system, 1); // show all books
		
		//add action listeners 
		pages.get(0).addActionListener(getAction());
		searchButton.addActionListener(getAction());
		seeAllButton.addActionListener(getAction());
		checkoutButton.addActionListener(getAction());
		returnButton.addActionListener(getAction());
		addBookButton.addActionListener(getAction());
		removeBookButton.addActionListener(getAction());
		
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void refreshSearches () { // method to refresh the search panel 
		int pageSize = pages.size(); // holds # of pages at the time when this method is called
		for (int i = pageSize-1; i > 0; i --) { // loop through all page buttons
			remove(pages.get(i)); // remove page buttons from panel
			pages.remove(pages.get(i)); // remove page buttons from the array...
		}
		for (int i = 0; i < displayedSearchResults.size(); i ++) { // loop through all results 
			remove(displayedSearchResults.get(i).getDisplayText()); // remove result's textfield from panel
			remove(displayedSearchResults.get(i).getBook()); // remove result's book button from panel
		}
		displayedSearchResults.removeAll(displayedSearchResults); // Clean out search results arraylist
	}
	
	public void defaultBookList (LYNXsys system, int page) { // method to display all books in search list
		displayedSearchResults.removeAll(displayedSearchResults);	// don't know if I need this here...
		for (int i = 0; i < system.getBooks().size(); i ++) { // loop through arraylist of books
			// add each book as a search result
			displayedSearchResults.add(new BookSearchResult (system.getBooks().get(i), system.getBooks().get(i).getTitle() + ", " + system.getBooks().get(i).getAuthorFirstName() + " " + system.getBooks().get(i).getAuthorLastName() + ". " + system.getBooks().get(i).getCategory() + ". ISBN: " + system.getBooks().get(i).getIsbn() + ". Available: " + system.getBooks().get(i).getAvailable()));
		}
		for(int i = ((page-1)*13); i < page*13; i++){ // add 13 results to the panel based on the page #...
			if (i < displayedSearchResults.size()) {
				displayedSearchResults.get(i).getDisplayText().setEditable(false);
				displayedSearchResults.get(i).getDisplayText().setBackground(Color.pink);
				displayedSearchResults.get(i).getDisplayText().setBounds(500,270 + (21*(i-(13*(page-1)))),450,20);
				displayedSearchResults.get(i).getBook().setBounds(460,270 + (21*(i-(13*(page-1)))),32,20);
				add(displayedSearchResults.get(i).getDisplayText(),0);
				add(displayedSearchResults.get(i).getBook(),0);
			}
		}
		for (int i = 12; i < displayedSearchResults.size(); i = i + 13) { // add page buttons based on # of search results needed to display
				pages.add(new JButton());
				pages.get(pages.size() - 1).setBounds(620 + (45*(((i-12)/13)+1)),547,43,19);
				add(pages.get((((i-12)/13)+1)),0);
				pages.get((((i-12)/13)+1)).addActionListener(getAction());
		}
		repaint(460,270,510,500); // repaint search area
	}
	
	public void searchByBookTitle (LYNXsys system, String query, int page) { // search for books by Title
		for (int i = 0; i < system.getBooks().size(); i ++) { // loop through arraylist of books
			if (system.getBooks().get(i).getTitle().equalsIgnoreCase(query)){ // check if book title matches search query
				displayedSearchResults.add(new BookSearchResult (system.getBooks().get(i), system.getBooks().get(i).getTitle() + ", " + system.getBooks().get(i).getAuthorFirstName() + " " + system.getBooks().get(i).getAuthorLastName() + ". " + system.getBooks().get(i).getCategory() + ". ISBN: " + system.getBooks().get(i).getIsbn() + ". Available: " + system.getBooks().get(i).getAvailable()));
			}
		}
		for(int i = ((page-1)*13); i < page*13; i++){
			if (i < displayedSearchResults.size()) {
				displayedSearchResults.get(i).getDisplayText().setEditable(false);
				displayedSearchResults.get(i).getDisplayText().setBackground(Color.pink);
				displayedSearchResults.get(i).getDisplayText().setBounds(500,270 + (21*(i-(13*(page-1)))),450,20);
				displayedSearchResults.get(i).getBook().setBounds(460,270 + (21*(i-(13*(page-1)))),32,20);
				add(displayedSearchResults.get(i).getDisplayText(),0);
				add(displayedSearchResults.get(i).getBook(),0);
			}
		}
		for (int i = 12; i < displayedSearchResults.size(); i = i + 13) {
			if (displayedSearchResults.size() > i) {
				pages.add(new JButton());
				pages.get(pages.size() - 1).setBounds(620 + (45*(((i-12)/13)+1)),547,43,19);
				add(pages.get((((i-12)/13)+1)),0);
				pages.get((((i-12)/13)+1)).addActionListener(getAction());
			}
		}
		repaint(460,270,510,500);
		lastSearch = 1;
	}
	
	public void searchByAuthor (LYNXsys system, String query, int page) {
		displayedSearchResults.removeAll(displayedSearchResults);	
		for (int i = 0; i < system.getBooks().size(); i ++) { // loop through arraylist of books
			if (system.getBooks().get(i).getAuthorFirstName().equalsIgnoreCase(query) || system.getBooks().get(i).getAuthorLastName().equalsIgnoreCase(query) || (system.getBooks().get(i).getAuthorFirstName() + " " + system.getBooks().get(i).getAuthorLastName()).equalsIgnoreCase(query)){ // check if book title matches search query
				displayedSearchResults.add(new BookSearchResult (system.getBooks().get(i), system.getBooks().get(i).getTitle() + ", " + system.getBooks().get(i).getAuthorFirstName() + " " + system.getBooks().get(i).getAuthorLastName() + ". " + system.getBooks().get(i).getCategory() + ". ISBN: " + system.getBooks().get(i).getIsbn() + ". Available: " + system.getBooks().get(i).getAvailable()));
			}
		}
		for(int i = ((page-1)*13); i < page*13; i++){
			if (i < displayedSearchResults.size()) {
				displayedSearchResults.get(i).getDisplayText().setEditable(false);
				displayedSearchResults.get(i).getDisplayText().setBackground(Color.pink);
				displayedSearchResults.get(i).getDisplayText().setBounds(500,270 + (21*(i-(13*(page-1)))),450,20);
				displayedSearchResults.get(i).getBook().setBounds(460,270 + (21*(i-(13*(page-1)))),32,20);
				add(displayedSearchResults.get(i).getDisplayText(),0);
				add(displayedSearchResults.get(i).getBook(),0);
			}
		}
		for (int i = 12; i < displayedSearchResults.size(); i = i + 13) {
			if (displayedSearchResults.size() > i) {
				pages.add(new JButton());
				pages.get(pages.size() - 1).setBounds(620 + (45*(((i-12)/13)+1)),547,43,19);
				add(pages.get((((i-12)/13)+1)),0);
				pages.get((((i-12)/13)+1)).addActionListener(getAction());
			}
		}
		lastSearch = 2;
		repaint(460,270,510,500);
	}
	public void searchByCategory (LYNXsys system, String query, int page) {
		displayedSearchResults.removeAll(displayedSearchResults);	
		for (int i = 0; i < system.getBooks().size(); i ++) { // loop through arraylist of books
			if (system.getBooks().get(i).getCategory().equalsIgnoreCase(query)) {
				displayedSearchResults.add(new BookSearchResult (system.getBooks().get(i), system.getBooks().get(i).getTitle() + ", " + system.getBooks().get(i).getAuthorFirstName() + " " + system.getBooks().get(i).getAuthorLastName() + ". " + system.getBooks().get(i).getCategory() + ". ISBN: " + system.getBooks().get(i).getIsbn() + ". Available: " + system.getBooks().get(i).getAvailable()));
			}
		}
		for(int i = ((page-1)*13); i < page*13; i++){
			if (i < displayedSearchResults.size()) {
				displayedSearchResults.get(i).getDisplayText().setEditable(false);
				displayedSearchResults.get(i).getDisplayText().setBackground(Color.pink);
				displayedSearchResults.get(i).getDisplayText().setBounds(500,270 + (21*(i-(13*(page-1)))),450,20);
				displayedSearchResults.get(i).getBook().setBounds(460,270 + (21*(i-(13*(page-1)))),32,20);
				add(displayedSearchResults.get(i).getDisplayText(),0);
				add(displayedSearchResults.get(i).getBook(),0);
			}
		}
		for (int i = 12; i < displayedSearchResults.size(); i = i + 13) {
			if (displayedSearchResults.size() > i) {
				pages.add(new JButton());
				pages.get(pages.size() - 1).setBounds(620 + (45*(((i-12)/13)+1)),547,43,19);
				add(pages.get((((i-12)/13)+1)),0);
				pages.get((((i-12)/13)+1)).addActionListener(getAction());
			}
		}
		lastSearch = 3;
		repaint(460,270,510,500);
	}
	
	//GETTERS AND SETTERS
	public Textbox getSearchField() {
		return searchField;
	}

	public void setSearchField(Textbox searchField) {
		this.searchField = searchField;
	}

	public JLabel getSearchOptions() {
		return searchOptions;
	}

	public void setSearchOptions(JLabel searchOptions) {
		this.searchOptions = searchOptions;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
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

	public JButton getAddBookButton() {
		return addBookButton;
	}

	public void setAddBookButton(JButton addBookButton) {
		this.addBookButton = addBookButton;
	}

	public JButton getRemoveBookButton() {
		return removeBookButton;
	}

	public void setRemoveBookButton(JButton removeBookButton) {
		this.removeBookButton = removeBookButton;
	}

	public ButtonGroup getSearchSelectionGroup() {
		return searchSelectionGroup;
	}

	public void setSearchSelectionGroup(ButtonGroup searchSelectionGroup) {
		this.searchSelectionGroup = searchSelectionGroup;
	}

	public JRadioButton getSearchByBookOption() {
		return searchByBookOption;
	}

	public void setSearchByBookOption(JRadioButton searchByBookOption) {
		this.searchByBookOption = searchByBookOption;
	}

	public JRadioButton getSearchByAuthorOption() {
		return searchByAuthorOption;
	}

	public void setSearchByAuthorOption(JRadioButton searchByAuthorOption) {
		this.searchByAuthorOption = searchByAuthorOption;
	}

	public JRadioButton getSearchByCategoryOption() {
		return searchByCategoryOption;
	}

	public void setSearchByCategoryOption(JRadioButton searchByCategoryOption) {
		this.searchByCategoryOption = searchByCategoryOption;
	}

	public ArrayList<BookSearchResult> getDisplayedSearchResults() {
		return displayedSearchResults;
	}

	public void setDisplayedSearchResults(
			ArrayList<BookSearchResult> displayedSearchResults) {
		this.displayedSearchResults = displayedSearchResults;
	}

	public JButton getSeeAllButton() {
		return seeAllButton;
	}

	public void setSeeAllButton(JButton seeAllButton) {
		this.seeAllButton = seeAllButton;
	}

	public ArrayList<JButton> getPages() {
		return pages;
	}

	public void setPages(ArrayList<JButton> pages) {
		this.pages = pages;
	}

	public int getLastSearch() {
		return lastSearch;
	}

	public void setLastSearch(int lastSearch) {
		this.lastSearch = lastSearch;
	}
	
	
	
}

