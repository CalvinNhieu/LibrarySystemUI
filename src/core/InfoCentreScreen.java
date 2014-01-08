package core;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class InfoCentreScreen extends InScreens {

	private static final long serialVersionUID = 1L; // suggested...
	
	// define variables 
	private int lastSearch; // holds counter for the last search type made
	
	private ArrayList<StudentSearchResult> displayedSearchResults; // search results as text to show on screen
	private ArrayList<JButton> pages;
	
	private Textbox searchField; // search field textbox
	
	private JLabel searchOptions; // image to label search type options
	
	private JButton searchButton; // button to search
	private JButton seeAllButton; // button to see all
	private JButton editStudentButton; // button to move to checkout book screen
	private JButton addStudentButton; // button to move to add book screen
	private JButton removeStudentButton; // button to move to remove book screen
	
	private ButtonGroup searchSelectionGroup; // to group search type option radio buttons
	private JRadioButton searchByNameOption; // to search by book title
	private JRadioButton searchByIdOption; // to search by author name (first, last, or both)
	
	public InfoCentreScreen (LYNXsys system) {

		super(system); // instantiate super class (InScreens)
		
		//initialize components
		lastSearch = 0;
		displayedSearchResults = new ArrayList<StudentSearchResult>();
		pages = new ArrayList<JButton>();
		pages.add(new JButton("1"));
		
		searchField = new Textbox (475,200,349,32); 
		searchOptions = new JLabel (new ImageIcon (getClass().getResource("/Resources/StudentScreen SearchBy Options.png")));
		searchButton = new JButton ("Search");
		seeAllButton = new JButton ("All");
		
		editStudentButton = new JButton ("Edit Student"); //left side components (3 buttons)
		addStudentButton = new JButton ("Add Student");
		removeStudentButton = new JButton ("Remove Student");
		
		searchByNameOption = new JRadioButton (); //selection options (2) and group
		searchByIdOption = new JRadioButton ();
		searchSelectionGroup = new ButtonGroup ();
		
		//setbounds...
		pages.get(0).setBounds(620,547,43,19);
		searchOptions.setBounds(445,240,323,19); //search components
		searchButton.setBounds(830,200,75,31);
		seeAllButton.setBounds(910,200,60,31);
		
		addStudentButton.setBounds(40,290,300,50);//left side buttons
		removeStudentButton.setBounds(40,390,300,50);
		editStudentButton.setBounds(40,430,300,50);
		
		searchByNameOption.setBounds(575,240,20,20);//radio buttons (2)
		searchByIdOption.setBounds(688,240,20,20);
		
		searchSelectionGroup.add(searchByNameOption);
		searchSelectionGroup.add(searchByIdOption);
		
		//add components
		add(pages.get(0),0);
		add(searchOptions,0);
		add(searchByNameOption,0);
		add(searchByIdOption,0);
		add(searchField.getBox(),0);
		add(searchButton,0);
		add(seeAllButton,0);
		add(addStudentButton,0);
		add(removeStudentButton,0);
		add(searchField.getTextField());
		defaultStudentList(system, 1); // show all students
		//add action listeners 
		pages.get(0).addActionListener(getAction());
		searchButton.addActionListener(getAction());
		seeAllButton.addActionListener(getAction());
		addStudentButton.addActionListener(getAction());
		removeStudentButton.addActionListener(getAction());
		editStudentButton.addActionListener(getAction());
		
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void refreshSearches () { // refresh da search panel
		int pageSize = pages.size(); // holds total # of pages at the time this method is called 
		for (int i = pageSize-1; i > 0; i --) { // loop through pages
			remove(pages.get(i)); // remove page from panel
			pages.remove(pages.get(i)); // remove page from arraylist
		}
		for (int i = 0; i < displayedSearchResults.size(); i ++) { // loop through search results
			remove(displayedSearchResults.get(i).getDisplayText()); //  remove text from panel
			remove(displayedSearchResults.get(i).getUserButton()); // remove button from panel
		}
		displayedSearchResults.removeAll(displayedSearchResults); // empty search results
	}
	
	public void defaultStudentList (LYNXsys system, int page) { // to show All students in the system
		displayedSearchResults.removeAll(displayedSearchResults); // empty search results
		for (int i = 0; i < system.getUsers().size(); i ++) { // loop through arraylist of students
			// new search result
			displayedSearchResults.add(new StudentSearchResult (system.getUsers().get(i), system.getUsers().get(i).getID() + ": " + system.getUsers().get(i).getLastName() + ", " + system.getUsers().get(i).getFirstName() + ". "));
		}
		for(int i = ((page-1)*13); i < page*13; i++){ // display 13 results based on page #
			if (i < displayedSearchResults.size()) { 
				displayedSearchResults.get(i).getDisplayText().setEditable(false);
				displayedSearchResults.get(i).getDisplayText().setBackground(Color.pink);
				displayedSearchResults.get(i).getDisplayText().setBounds(500,270 + (21*(i-(13*(page-1)))),400,20);
				displayedSearchResults.get(i).getUserButton().setBounds(460,270 + (21*(i-(13*(page-1)))),32,20);
				add(displayedSearchResults.get(i).getDisplayText(),0);
				add(displayedSearchResults.get(i).getUserButton(),0);
			}
		}
		for (int i = 12; i < displayedSearchResults.size(); i = i + 13) { // add number of page buttons appr.
			if (displayedSearchResults.size() > i) {
				pages.add(new JButton());
				pages.get(pages.size() - 1).setBounds(620 + (45*(((i-12)/13)+1)),547,43,19);
				add(pages.get((((i-12)/13)+1)),0);
				pages.get((((i-12)/13)+1)).addActionListener(getAction());
			}
		}
		repaint(460,270,510,500); // repaint
	}
	
	public void searchByName (LYNXsys system, String query, int page) { // search for students by by name
		for (int i = 0; i < system.getUsers().size(); i ++) { // loop through arraylist of students
			if ((system.getUsers().get(i).getFirstName().equalsIgnoreCase(query)) || (system.getUsers().get(i).getLastName().equalsIgnoreCase(query)) || (system.getUsers().get(i).getFirstName() + " " + system.getUsers().get(i).getLastName()).equalsIgnoreCase(query)){ // check if book title matches search query
				displayedSearchResults.add(new StudentSearchResult (system.getUsers().get(i), system.getUsers().get(i).getID() + ": " + system.getUsers().get(i).getLastName() + ", " + system.getUsers().get(i).getFirstName() + ". "));
			}
		}
		for(int i = ((page-1)*13); i < page*13; i++){
			if (i < displayedSearchResults.size()) {
				displayedSearchResults.get(i).getDisplayText().setEditable(false);
				displayedSearchResults.get(i).getDisplayText().setBackground(Color.pink);
				displayedSearchResults.get(i).getDisplayText().setBounds(500,270 + (21*(i-(13*(page-1)))),400,20);
				displayedSearchResults.get(i).getUserButton().setBounds(460,270 + (21*(i-(13*(page-1)))),32,20);
				add(displayedSearchResults.get(i).getDisplayText(),0);
				add(displayedSearchResults.get(i).getUserButton(),0);
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
	
	public void searchByID (LYNXsys system, String query, int page) { // search student by ID
		displayedSearchResults.removeAll(displayedSearchResults);	
		for (int i = 0; i < system.getUsers().size(); i ++) { // loop through arraylist of students
			if (system.getUsers().get(i).getID().equalsIgnoreCase(query)){ // check if book title matches search query
				displayedSearchResults.add(new StudentSearchResult (system.getUsers().get(i), system.getUsers().get(i).getID() + ": " + system.getUsers().get(i).getLastName() + ", " + system.getUsers().get(i).getFirstName() + ". "));
			}
		}
		for(int i = ((page-1)*13); i < page*13; i++){
			if (i < displayedSearchResults.size()) {
				displayedSearchResults.get(i).getDisplayText().setEditable(false);
				displayedSearchResults.get(i).getDisplayText().setBackground(Color.pink);
				displayedSearchResults.get(i).getDisplayText().setBounds(500,270 + (21*(i-(13*(page-1)))),400,20);
				displayedSearchResults.get(i).getUserButton().setBounds(460,270 + (21*(i-(13*(page-1)))),32,20);
				add(displayedSearchResults.get(i).getDisplayText(),0);
				add(displayedSearchResults.get(i).getUserButton(),0);
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

	public JButton getAddStudentButton() {
		return addStudentButton;
	}

	public void setAddStudentButton(JButton addStudentButton) {
		this.addStudentButton = addStudentButton;
	}

	public JButton getRemoveStudentButton() {
		return removeStudentButton;
	}

	public void setRemoveStudentButton(JButton removeStudentButton) {
		this.removeStudentButton = removeStudentButton;
	}

	public JButton getEditStudentButton() {
		return editStudentButton;
	}

	public void setEditStudentkButton(JButton editStudentButton) {
		this.editStudentButton = editStudentButton;
	}
	
	public ButtonGroup getSearchSelectionGroup() {
		return searchSelectionGroup;
	}

	public void setSearchSelectionGroup(ButtonGroup searchSelectionGroup) {
		this.searchSelectionGroup = searchSelectionGroup;
	}

	public JRadioButton getSearchByNameOption() {
		return searchByNameOption;
	}

	public void setSearchByNameOption(JRadioButton searchByNameOption) {
		this.searchByNameOption = searchByNameOption;
	}

	public JRadioButton getSearchByIdOption() {
		return searchByIdOption;
	}

	public void setSearchByIdOption(JRadioButton searchByIdOption) {
		this.searchByIdOption = searchByIdOption;
	}

	public ArrayList<StudentSearchResult> getDisplayedSearchResults() {
		return displayedSearchResults;
	}

	public void setDisplayedSearchResults(
			ArrayList<StudentSearchResult> displayedSearchResults) {
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

