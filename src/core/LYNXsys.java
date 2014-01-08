package core;
import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LYNXsys extends JFrame { // JFrame constructs the Application window
	
	//define variables
	private Librarian lib; // contains the system's user Login and Password information
	private ArrayList<User> users; // contains an arraylist of users (students)
	private ArrayList<Book> books; // contains an arraylist of books
	
	private ArrayList<String> usersText; // arraylist of strings to hold individual lines in external Users.txt file (FILE I/O)
	private ArrayList<String> booksText;// arraylist of strings to hold individual lines in external Books.txt file (FILE I/O)
	
	private Scanner s; // scanner to read Users.txt and Books.txt files
	
	private static final long serialVersionUID = 1L; // mandatory
	private static final int WIDTH = 1000; // window width
	private static final int HEIGHT = 618; // window height
	
	private RemoveStudentScreen removeStudentScreen; // screen to remove Students
	private AddStudentScreen addStudentScreen; // screen to add Students
	private ViewStudentScreen viewStudentScreen; // screen to view specific student
	private ViewBookScreen viewBookScreen; // screen to view specific book
	private AddBookScreen addBookScreen; // screen to add books
	private RemoveBookScreen removeBookScreen; // screen to remove books
	private ReturnBookScreen returnBookScreen; // screen to return books
	private CheckoutBookScreen checkoutBookScreen; // screen to checkout books
	private SocialNetworkScreen socialNetworkScreen; // social network screen
	private InfoCentreScreen infoCentreScreen; // info centre screen
	private BookScreen bookScreen; // bookhub screen
	private HomeScreen homeScreen; // home screen (screen after you log in)
	private LoginScreen loginScreen; // login screen (first screen)
	
	
	
	public LYNXsys () throws FileNotFoundException { // constructor
		super("LYNXsys [Library System]"); 
		
		// initialize variables
		lib = new Librarian ("ASAYED", "1"); // set Librarian userID and password 
		users = new ArrayList<User>(); // init users arraylist
		books = new ArrayList<Book>(); // init books arraylist
		usersText = new ArrayList<String>(); // init this
		booksText = new ArrayList<String>(); // init that
		
		s = new Scanner (new BufferedReader (new FileReader (new File ("C:/Users/Public/Documents/Top Desk/Workspace/LibrarySystem/src/Resources/Users.txt")))); // set scanner to read from Users.txt
		createUsers(s, users, usersText); // fill user ArrayList using info from Users.txt
		s = new Scanner (new BufferedReader (new FileReader (new File ("C:/Users/Public/Documents/Top Desk/Workspace/LibrarySystem/src/Resources/Books.txt")))); // set scanner to read from Books.txt
		createBooks(s, books, booksText); // fill user ArrayList using info from Books.txt
		
		// init screens used in Listener
		removeStudentScreen = new RemoveStudentScreen (this);
		addStudentScreen = new AddStudentScreen (this);
		viewStudentScreen = new ViewStudentScreen (this, users.get(0));
		viewBookScreen = new ViewBookScreen (this, books.get(0));
		infoCentreScreen = new InfoCentreScreen (this);
		bookScreen = new BookScreen(this);
		checkoutBookScreen = new CheckoutBookScreen(this);
		returnBookScreen = new ReturnBookScreen(this);
		addBookScreen = new AddBookScreen(this);
		removeBookScreen = new RemoveBookScreen(this);
		
		enterLoginScreen(); // open program at Login Screen
		//enterReturnBookScreen(); // tester navigation 
		
		setSize(WIDTH, HEIGHT); //Window attributes
		setVisible(true); // show window
		setDefaultCloseOperation(EXIT_ON_CLOSE); // allow close by clicking X
	}
	
	public void paint (Graphics g) { // paint method
		super.paint(g);
	}
	
	private void createUsers(Scanner s, ArrayList<User> users, ArrayList<String> usersText) { // method to read Users.txt file and create user Objects
		while (s.hasNextLine()) {
			usersText.add(s.nextLine()); // fill string arraylist with lines from Users.txt
		}
		for (int i = 0; i < usersText.size(); i++) { // read each line from the txt file separately 
			Scanner s2 = new Scanner(usersText.get(i)); // new scanner to read individual lines
			s2.useDelimiter(":"); // text encryption
			users.add(new User(s2.next(),s2.next(),s2.next(),s2.next())); // instantiate users
			s2.close(); // close scanner
		}
	}
	
	private void createBooks(Scanner s, ArrayList<Book> books, ArrayList<String> booksText) { // method to read Books.txt file and create user Objects
		while (s.hasNextLine()) {
			booksText.add(s.nextLine()); // fill string arraylist with lines from Books.txt
		}
		for (int i = 0; i < booksText.size(); i++) { // read each line from the txt file separately 
			Scanner s2 = new Scanner(booksText.get(i)); // new scanner to read individual lines
			s2.useDelimiter(":"); // text encryption
			books.add(new Book(s2.next(),s2.next(),s2.next(),s2.next(),s2.next(),s2.nextDouble(),s2.next(),s2.next())); // instantiate books
			s2.close(); // close scanner
		} 
	}
	
	public void checkoutBook (Book book, User user, int month, int day, int year) { // method to checkout a book for a student
		book.saveDate(month,day,year);
		book.setAvailable("N");
		user.getBooks().add(book);
		enterBookScreen();
	}
	
	public void returnBook (Book book, User user, int month, int day, int year) { // method to return a book to the system
		book.setDaysOut(0);
		book.setAvailable("Y");
		calculateStudentFines(book, user, month, day, year);
		user.getBooks().remove(book);
		enterBookScreen();
	}
	
	public void addBook (Book book) { // method to add a book to the system
		books.add(book);
		enterBookScreen();
	}
	
	public void removeBook (Book book) { // method to remove a book from the system
		books.remove(book);
		enterBookScreen();
	}
	
	public void removeLostBook (Book book, User user) { // method to say a book has been lost
		user.setFines(user.getFines() + book.getCost());
		user.getBooks().remove(book);
		books.remove(book);
		enterBookScreen();
	}
	
	public void addStudent (User user) { // method to add a student to the system
		users.add(user);
		enterInfoCentreScreen();
	}
	
	public void removeStudent (User user) { // method to remove a student from the system
		users.remove(user);
		enterInfoCentreScreen();
	}
	
	// helper method to translate the return date of the book to an amount of student fines
	private void calculateStudentFines(Book book, User user, int monthBack, int dayBack, int yearBack) { 
		int daysInMonth = 31;
		int monthsInYear = 12;
		int daysInYear = 366;
		
		double totalDaysOut = 0;
		
		int yearDiff = yearBack - book.getYearOut();
		int monthDiff = monthBack - book.getMonthOut();
		int dayDiff = dayBack - book.getDayOut();
		
		double finesToAdd = 0;
		
		if (!(yearDiff == 0)) {
			totalDaysOut = ((monthsInYear - book.getMonthOut())*daysInMonth) + (daysInMonth - book.getDayOut()) + dayBack + monthBack*daysInMonth + ((yearDiff-1)*daysInYear);
		}
		else if (yearDiff == 0 && !(monthDiff == 0)) {
			totalDaysOut = (daysInMonth - book.getDayOut()) + dayBack + ((monthDiff-1)*daysInMonth);
		}
		else if (yearDiff == 0 && monthDiff == 0) {
			totalDaysOut = dayDiff;
		}
		
		finesToAdd = (totalDaysOut-14)/10;
		
		user.setFines(user.getFines() + finesToAdd);
	}
	
	// generic methods to change screens (each screen has its own method)
	public void enterLoginScreen () {
		loginScreen = new LoginScreen(this);
		loginScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(loginScreen);
	}
	
	public void enterHomeScreen () {
		homeScreen = new HomeScreen(this);
		homeScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(homeScreen); 
	}
	
	public void enterBookScreen() {
		bookScreen = new BookScreen(this);
		bookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(bookScreen);
	}
	
	public void enterInfoCentreScreen() {
		infoCentreScreen = new InfoCentreScreen(this);
		infoCentreScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(infoCentreScreen);
	}
	
	public void enterSocialNetworkScreen() {
		socialNetworkScreen = new SocialNetworkScreen(this);
		socialNetworkScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(socialNetworkScreen);
	}
	
	public void enterCheckoutBookScreen() {
		checkoutBookScreen = new CheckoutBookScreen(this);
		checkoutBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(checkoutBookScreen);
	}
	
	public void enterReturnBookScreen() {
		returnBookScreen = new ReturnBookScreen(this);
		returnBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(returnBookScreen);
	}
	
	public void enterAddBookScreen() {
		addBookScreen = new AddBookScreen(this);
		addBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(addBookScreen);
	}
	
	public void enterRemoveBookScreen() {
		removeBookScreen = new RemoveBookScreen(this);
		removeBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(removeBookScreen);
	}
	
	public void enterViewBookScreen(Book bookReference) {
		viewBookScreen = new ViewBookScreen(this, bookReference);
		viewBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(viewBookScreen);
	}
	
	public void enterViewStudentScreen(User userReference) {
		viewStudentScreen = new ViewStudentScreen(this, userReference);
		viewStudentScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(viewStudentScreen);
	}
	
	public void enterCheckoutBookScreen(Book book) {
		checkoutBookScreen = new CheckoutBookScreen(this);
		checkoutBookScreen.fillFields(book);
		checkoutBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(checkoutBookScreen);
	}
	
	public void enterReturnBookScreen(Book book) {
		returnBookScreen = new ReturnBookScreen(this);
		returnBookScreen.fillFields(book);
		returnBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(returnBookScreen);
	}
	
	public void enterAddBookScreen(Book book) {
		addBookScreen = new AddBookScreen(this);
		addBookScreen.fillFields(book);
		addBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(addBookScreen);
	}
	
	public void enterRemoveBookScreen(Book book) {
		removeBookScreen = new RemoveBookScreen(this);
		removeBookScreen.fillFields(book);
		removeBookScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(removeBookScreen);
	}
	
	public void enterAddStudentScreen() {
		addStudentScreen = new AddStudentScreen (this);
		addStudentScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(addStudentScreen);
	}
	
	public void enterRemoveStudentScreen() {
		removeStudentScreen = new RemoveStudentScreen (this);
		removeStudentScreen.setBounds(0,0,WIDTH,HEIGHT);
		setContentPane(removeStudentScreen);
	}

	
	
	
	// GETTERS AND SETTERS
	public LoginScreen getLoginScreen() {
		return loginScreen;
	}


	public void setLoginScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
	}
	
	public BookScreen getBookScreen() {
		return bookScreen;
	}

	public void setBookScreen(BookScreen bookScreen) {
		this.bookScreen = bookScreen;
	}


	public HomeScreen getHomeScreen() {
		return homeScreen;
	}


	public void setHomeScreen(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
	}
	
	public InfoCentreScreen getInfoCentreScreen() {
		return infoCentreScreen;
	}

	public void setInfoCentreScreen(InfoCentreScreen infoCentreScreen) {
		this.infoCentreScreen = infoCentreScreen;
	}
	
	
	public Librarian getLib() {
		return lib;
	}

	public void setLib(Librarian lib) {
		this.lib = lib;
	}

	public AddBookScreen getAddBookScreen() {
		return addBookScreen;
	}

	public void setAddBookScreen(AddBookScreen addBookScreen) {
		this.addBookScreen = addBookScreen;
	}

	public RemoveBookScreen getRemoveBookScreen() {
		return removeBookScreen;
	}

	public void setRemoveBookScreen(RemoveBookScreen removeBookScreen) {
		this.removeBookScreen = removeBookScreen;
	}

	public ReturnBookScreen getReturnBookScreen() {
		return returnBookScreen;
	}

	public void setReturnBookScreen(ReturnBookScreen returnBookScreen) {
		this.returnBookScreen = returnBookScreen;
	}

	public CheckoutBookScreen getCheckoutBookScreen() {
		return checkoutBookScreen;
	}

	public void setCheckoutBookScreen(CheckoutBookScreen checkoutBookScreen) {
		this.checkoutBookScreen = checkoutBookScreen;
	}

	public SocialNetworkScreen getSocialNetworkScreen() {
		return socialNetworkScreen;
	}

	public void setSocialNetworkScreen(SocialNetworkScreen socialNetworkScreen) {
		this.socialNetworkScreen = socialNetworkScreen;
	}

	public ViewStudentScreen getViewStudentScreen() {
		return viewStudentScreen;
	}

	public void setViewStudentScreen(ViewStudentScreen viewStudentScreen) {
		this.viewStudentScreen = viewStudentScreen;
	}

	public ViewBookScreen getViewBookScreen() {
		return viewBookScreen;
	}

	public void setViewBookScreen(ViewBookScreen viewBookScreen) {
		this.viewBookScreen = viewBookScreen;
	}
	
	public RemoveStudentScreen getRemoveStudentScreen() {
		return removeStudentScreen;
	}

	public void setRemoveStudentScreen(RemoveStudentScreen removeStudentScreen) {
		this.removeStudentScreen = removeStudentScreen;
	}

	public AddStudentScreen getAddStudentScreen() {
		return addStudentScreen;
	}

	public void setAddStudentScreen(AddStudentScreen addStudentScreen) {
		this.addStudentScreen = addStudentScreen;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<String> getUsersText() {
		return usersText;
	}

	public void setUsersText(ArrayList<String> usersText) {
		this.usersText = usersText;
	}

	public ArrayList<String> getBooksText() {
		return booksText;
	}

	public void setBooksText(ArrayList<String> booksText) {
		this.booksText = booksText;
	}

	public Scanner getS() {
		return s;
	}

	public void setS(Scanner s) {
		this.s = s;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
	
	
}
