package core;

import javax.swing.*;


public class BookSearchResult {
	
	private Book bookReference; // holds a book
	private JButton book; // button to navigate to a book
	private JTextArea displayText; // area to display text
	
	public BookSearchResult(Book bookReference, String result) { // constructor 
		// initialize variables
		this.bookReference = bookReference;
		displayText = new JTextArea(result);
		book = new JButton (new ImageIcon (getClass().getResource("/Resources/Book Icon.png")));
		
		// add actionlistener to book nav button
		book.addActionListener(InScreens.getAction());
	}

	// GETTERS AND SETTERS
	public JTextArea getDisplayText() {
		return displayText;
	}

	public void setDisplayText(JTextArea displayText) {
		this.displayText = displayText;
	}

	public JButton getBook() {
		return book;
	}

	public void setBook(JButton book) {
		this.book = book;
	}
	
	public Book getBookReference() {
		return bookReference;
	}

	public void setBookReference(Book bookReference) {
		this.bookReference = bookReference;
	}
	
}
