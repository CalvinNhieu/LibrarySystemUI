package core;

public class Book {
	
	// define variables
	private String title; // book title
	private String authorFirstName; // author of book first name
	private String authorLastName; // author of book last name
	private String category; // book category
	private String isbn; // book isbn
	private double cost; // book cost
	private String rating; // book rating
	private String available; // book availability
	
	private int daysOut; // number of days the book has been taken out
	private int dayOut; // day of month book was taken out
	private int monthOut; // month of year book was taken out
	private int yearOut; // year the book was taken out
	private boolean lost; // is the book lost
	
	public Book (String title, String authorFirstName, String authorLastName, String isbn, String category, double cost, String rating, String available) {
		// init variables
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.category = category;
		this.isbn = isbn;
		this.cost = cost;
		this.rating = rating;
		this.available = available;
		
		daysOut = 0;
		dayOut = 0;
		monthOut = 0;
		yearOut = 0;
		lost = false; 
	}

	public boolean equals (Book book) { // equals method to compare books
		if (title.equalsIgnoreCase(book.getTitle()) && // if all fields match between the 2 books...
				authorFirstName.equalsIgnoreCase(book.getAuthorFirstName()) &&
				authorLastName.equalsIgnoreCase(book.getAuthorLastName()) &&
				category.equalsIgnoreCase(book.getCategory()) &&
				isbn.equalsIgnoreCase(book.getIsbn()) &&
				cost == book.getCost() &&
				rating.equalsIgnoreCase(book.getRating())) {
			return true;
		}
		return false;
	}
	
	public void saveDate (int month, int day, int year) {
		monthOut = month;
		dayOut = day;
		yearOut = year;
	}
	
	// GETTERS AND SETTERS
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public boolean isLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public int getDayOut() {
		return dayOut;
	}

	public void setDayOut(int dayOut) {
		this.dayOut = dayOut;
	}

	public int getYearOut() {
		return yearOut;
	}

	public void setYearOut(int yearOut) {
		this.yearOut = yearOut;
	}

	public int getMonthOut() {
		return monthOut;
	}

	public void setMonthOut(int monthOut) {
		this.monthOut = monthOut;
	}

	public int getDaysOut() {
		return daysOut;
	}

	public void setDaysOut(int daysOut) {
		this.daysOut = daysOut;
	}
	
}
