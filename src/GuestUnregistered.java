
public class GuestUnregistered {

	// instance variables 
	private String email; 
	private boolean registrationStatus; 
	//private GuestResponse rsvpResponse; 
	private BFF bff; 
	
	// Constructors
	
	public GuestUnregistered(String email) {
		this.email = email; 
		//this.rsvpResponse = rsvpResponse;
		bff = new BFF(); 
	}

	// getters and setters for instance variables 
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	// Method Name: toString()
	// Parameters: None
	// Return Value: String
	@Override 
	public String toString() 
	{
		String msg = email + " is an unregistered guest on the system";
		return msg; 
	}
	
	

}
