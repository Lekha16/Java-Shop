
/**
 * 
 * This class represents an user,who has many attributes stored in the system.Anyone who wants to use the system
 * must be logged in as a user.
 */
public class Person {

	// customer id
	private int id;
	// customer real name
	private String username;
	// customer password
	private String password;
	
	
	
	// User constructor
	public Person(int id, String username, String password) {
		this.id = id;
		this.password = password;
		this.username = username;
	}
	
	public Person(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
