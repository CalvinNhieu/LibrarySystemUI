package core;

import java.awt.Graphics;

import javax.swing.*;


public class BookSubScreens extends InScreens {

	// define variables
	private static final long serialVersionUID = 1L; // suggested
	
	private static JButton back; // button to go back screens
	
	private JLabel textHeaders; // image for text labels
	private JLabel studentHeader; // image for student label
	private JLabel lostHeader; // image for lost label
	private JLabel dateHeader; // image for date label
	
	private ButtonGroup yesOrNo; // button group for yes or no (lost)
	private JRadioButton yes; // yes option
	private JRadioButton no; // no option
	private JButton confirm; // confirmt button (continue)
	
	private Textbox bookTitle; // field to enter book's title
	private Textbox authorFirstName; // field for author's first name
	private Textbox authorLastName; // field for author's last name
	private Textbox category; // field for book category
	private Textbox isbn; // field for book's isbn
	private Textbox cost; // field for book's cost
	private Textbox rating; // field for book's rating
	private Textbox2 studentNumberBox; // field to enter student's sNum
	private Textbox2 date; // field to enter checkout date
	
	public BookSubScreens(LYNXsys system) { // constructor
		super(system); // call super class
		
		// init variables
		textHeaders = new JLabel (new ImageIcon(getClass().getResource("/Resources/BookEdit Options.png")));
		studentHeader = new JLabel (new ImageIcon(getClass().getResource("/Resources/StudentNumber Header.png")));
		lostHeader = new JLabel (new ImageIcon (getClass().getResource("/Resources/Lost.png")));
		dateHeader = new JLabel (new ImageIcon (getClass().getResource("/Resources/Date Label.png")));
		
		yes = new JRadioButton("Y");
		no = new JRadioButton("N");
		yesOrNo = new ButtonGroup();
		
		back = new JButton("<<");
		confirm = new JButton();
		
		// set bounds of components
		textHeaders.setBounds(105,197,224,335);
		studentHeader.setBounds(740,250,100,31);
		lostHeader.setBounds(770,197,70,31);
		dateHeader.setBounds(779,197,70,31);
		yes.setBounds(850,200,50,20);
		no.setBounds(900,200,40,20);
		back.setBounds(30,200,50,32);
		confirm.setBounds(765,500,150,32);
		
		bookTitle = new Textbox (345,200,349,32);
		authorFirstName = new Textbox (345,250,349,32);
		authorLastName = new Textbox (345,300,349,32);
		category = new Textbox (345,350,349,32);
		isbn = new Textbox (345,400,349,32);
		cost = new Textbox (345,450,349,32);
		rating = new Textbox (345,500,349,32);
		studentNumberBox = new Textbox2(850,250,102,32);
		date = new Textbox2(850,200,102,32);
		getDate().getTextField().setText("MM/DD/YYYY");
		
		// add components to panel
		yesOrNo.add(yes); // options...
		yesOrNo.add(no);
		
		add(textHeaders,0); // buttons...
		add(back,0);
		add(confirm,0);
		
		add(bookTitle.getTextField()); // textfields...
		add(bookTitle.getBox(),0);
		add(authorFirstName.getTextField());
		add(authorFirstName.getBox(),0);
		add(authorLastName.getTextField());
		add(authorLastName.getBox(),0);
		add(category.getTextField());
		add(category.getBox(),0);
		add(isbn.getTextField());
		add(isbn.getBox(),0);
		add(cost.getTextField());
		add(cost.getBox(),0);
		add(rating.getTextField());
		add(rating.getBox(),0);
		
		// add action listeners to buttons
		back.addActionListener(getAction());
		confirm.addActionListener(getAction());
		
	}
	
	public void paintComponent(Graphics g) { // paint method
		super.paintComponent(g);
	}
	
	public void fillFields (Book book) { // automatically fill fields when entering this screen from book's page
		bookTitle.getTextField().setText(book.getTitle());
		authorFirstName.getTextField().setText(book.getAuthorFirstName());
		authorLastName.getTextField().setText(book.getAuthorLastName());
		category.getTextField().setText(book.getCategory());
		isbn.getTextField().setText(book.getIsbn());
		cost.getTextField().setText("" + book.getCost());
		rating.getTextField().setText(book.getRating());
	}
	
	public void resetFields () { //set all fields to empty
		bookTitle.getTextField().setText("");
		authorFirstName.getTextField().setText("");
		authorLastName.getTextField().setText("");
		category.getTextField().setText("");
		isbn.getTextField().setText("");
		cost.getTextField().setText("");
		rating.getTextField().setText("");
	}
	
	
	// GETTERS AND SETTERS
	public JLabel getTextHeaders() {
		return textHeaders;
	}


	public void setTextHeaders(JLabel textHeaders) {
		this.textHeaders = textHeaders;
	}


	public JLabel getStudentHeader() {
		return studentHeader;
	}


	public void setStudentHeader(JLabel studentHeader) {
		this.studentHeader = studentHeader;
	}
	
	


	public JLabel getLostHeader() {
		return lostHeader;
	}

	public void setLostHeader(JLabel lostHeader) {
		this.lostHeader = lostHeader;
	}

	public Textbox2 getStudentNumberBox() {
		return studentNumberBox;
	}


	public void setStudentNumberBox(Textbox2 studentNumberBox) {
		this.studentNumberBox = studentNumberBox;
	}
	
	public ButtonGroup getYesOrNo() {
		return yesOrNo;
	}

	public void setYesOrNo(ButtonGroup yesOrNo) {
		this.yesOrNo = yesOrNo;
	}

	public JRadioButton getYes() {
		return yes;
	}

	public void setYes(JRadioButton yes) {
		this.yes = yes;
	}

	public JRadioButton getNo() {
		return no;
	}

	public void setNo(JRadioButton no) {
		this.no = no;
	}
	
	public static JButton getBack() {
		return back;
	}


	public static void setBack(JButton back) {
		BookSubScreens.back = back;
	}


	public JButton getConfirm() {
		return confirm;
	}

	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}


	public Textbox getBookTitle() {
		return bookTitle;
	}


	public void setBookTitle(Textbox bookTitle) {
		this.bookTitle = bookTitle;
	}


	public Textbox getAuthorFirstName() {
		return authorFirstName;
	}


	public void setAuthorFirstName(Textbox authorFirstName) {
		this.authorFirstName = authorFirstName;
	}


	public Textbox getAuthorLastName() {
		return authorLastName;
	}


	public void setAuthorLastName(Textbox authorLastName) {
		this.authorLastName = authorLastName;
	}


	public Textbox getCategory() {
		return category;
	}


	public void setCategory(Textbox category) {
		this.category = category;
	}


	public Textbox getIsbn() {
		return isbn;
	}


	public void setIsbn(Textbox isbn) {
		this.isbn = isbn;
	}


	public Textbox getCost() {
		return cost;
	}


	public void setCost(Textbox cost) {
		this.cost = cost;
	}


	public Textbox getRating() {
		return rating;
	}


	public void setRating(Textbox rating) {
		this.rating = rating;
	}

	public JLabel getDateHeader() {
		return dateHeader;
	}

	public void setDateHeader(JLabel dateHeader) {
		this.dateHeader = dateHeader;
	}

	public Textbox2 getDate() {
		return date;
	}

	public void setDate(Textbox2 date) {
		this.date = date;
	}
	
}
