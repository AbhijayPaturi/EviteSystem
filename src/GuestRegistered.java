import java.util.ArrayList;
import java.util.List;

public class GuestRegistered extends GuestUnregistered {

	// instance variables 
	private String firstName;
	private String lastName; 
	private String password; 
	private List<Party> invitedParties;
	private BFF bff;
	
	// Constructor
	// initialize instance variables 
	public GuestRegistered(String email, 
			String firstName, String lastName, String password) {
		super(email);
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.password = password;
		invitedParties = new ArrayList<>();
	}

	// getters and setters for instance variables 
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the invitedParties
	 */
	public List<Party> getInvitedParties() {
		return invitedParties;
	}
	
	// methods 
	// Method Name: getNumPartyInvites()
	// Parameters: None 
	// Return Value: int representing the number of parties the guest is invited to
	// This program identifies the number of parties the guest is invited to.
	public int getNumPartyInvites()
	{
		return invitedParties.size();
	}
	
	// Method Name: addParty()
	// Parameters: p is a Party, partyList is a List<Party>
	// Return Value: None
	// This program adds a Party to a list of parties. 
	public void addParty(Party p, List<Party> partyList)
	{
		// branching statement to identify if the parameter Party is already in the list of parties
		if (!(partyList.contains(p)))
		{
			// add Party to list of parties
			partyList.add(p);
			//System.out.println("Party added!");
		}
		else 
		{
			//System.out.println("Unfortunately, the party could not be added! " + 
					//"Already in your list of invited parties.");
		}
	}
	
	// Method Name: showParties()
	// Parameters: None 
	// Return Value: List<String>
	// This program gets a String version of all the parties the guest is invited to. 
	public List<String> showParties()
	{
		// initialize the List
		List<String> partyDisplayList = new ArrayList<>();
		// for loop to loop through the list of parties the guest is invited to. 
		for (Party p : invitedParties)
		{
			String msg = "";
			msg += p.getPartyName() + " on " + p.getPartyDate() + " at " + p.getPartyLocation() + "!!!";
			// add to the List
			partyDisplayList.add(msg);
		}
		
		return partyDisplayList;
	}

}
