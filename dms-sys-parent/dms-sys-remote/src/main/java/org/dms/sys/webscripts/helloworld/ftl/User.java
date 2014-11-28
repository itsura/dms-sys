package org.dms.sys.webscripts.helloworld.ftl;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User {
    
    /** The firstname. */
    private String firstname;
    
    /** The lastname. */
    private String lastname;
 
    /**
     * Instantiates a new user.
     */
    public User() {
    }
 
    /**
     * Gets the firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets the firstname.
	 *
	 * @param firstname the new firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the lastname.
	 *
	 * @param lastname the new lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param firstname the firstname
	 * @param lastname the lastname
	 */
	public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
 
    }
}
