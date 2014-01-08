package core;

public class Librarian {
	// define vars
	private String ID; // library login ID
	private String password; // password
	
	public Librarian(String ID, String password) { // constructor
		// init variables
		this.ID = ID; 
		this.password = password;
	}

	// GETTERS AND SETTERS
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
