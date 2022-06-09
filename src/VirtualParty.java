import java.time.LocalDate;
import java.time.LocalTime;

public class VirtualParty extends Party {
	
	// instance variables 
	private PartyType partyType; 
	private PartyCategory partyCategory; 
	private String zoomRoomName; 
	private int zoomRoomCode; 
	private String zoomRoomPassword;
	private BFF bff;

	// Constructor 
	// initialize instance variables 
	public VirtualParty(String partyName, int partyDateYear, int partyDateMonth, int partyDateDay,
			 int partyTimeHour, int partyTimeMin, int partyDurationHours,
			String partyLocation, String hostEmail, PartyCategory partyCategory, 
			String zoomRoomName, int zoomRoomCode, String zoomRoomPassword) {
		super(partyName, partyDateYear, partyDateMonth, partyDateDay, 
				partyTimeHour, partyTimeMin, partyDurationHours, partyLocation, hostEmail);
		this.partyType = PartyType.VIRTUAL;
		this.partyCategory = partyCategory;
		this.zoomRoomName = zoomRoomName;
		this.zoomRoomCode = zoomRoomCode;
		this.zoomRoomPassword = zoomRoomPassword;
		bff = new BFF();
	}

	// getters and setters for instance variables 
	/**
	 * @return the partyType
	 */
	public PartyType getPartyType() {
		return partyType;
	}

	/**
	 * @param partyType the partyType to set
	 */
	public void setPartyType(PartyType partyType) {
		this.partyType = partyType;
	}

	/**
	 * @return the partyCategory
	 */
	public PartyCategory getPartyCategory() {
		return partyCategory;
	}

	/**
	 * @param partyCategory the partyCategory to set
	 */
	public void setPartyCategory(PartyCategory partyCategory) {
		this.partyCategory = partyCategory;
	}

	/**
	 * @return the zoomRoomName
	 */
	public String getZoomRoomName() {
		return zoomRoomName;
	}

	/**
	 * @param zoomRoomName the zoomRoomName to set
	 */
	public void setZoomRoomName(String zoomRoomName) {
		this.zoomRoomName = zoomRoomName;
	}

	/**
	 * @return the zoomRoomCode
	 */
	public int getZoomRoomCode() {
		return zoomRoomCode;
	}

	/**
	 * @return the zoomRoomPassword
	 */
	public String getZoomRoomPassword() {
		return zoomRoomPassword;
	}

	// methods 
	// Method Name: virtualAction()
	// Parameters: guest is a GuestUnregistered
	// Return Value: None 
	// This program allows the guest to interact with the VirtualParty invitation.
	public void virtualAction(GuestUnregistered guest)
	{
		String guestEmail = guest.getEmail();
		// variable storing the guest choice of option
		int userChoice = 55; 
		// while loop to allow the guest to 
		// keep interacting with the Menu until 
		// the user chooses to EXIT. 
		while (userChoice != (VirtualPartyMenu.values().length - 1))
		{
			System.out.println();
			// display the options to the guest
			VirtualPartyMenu.printMenuOptions();
			// get guest input of menu option 
			userChoice = bff.inputInt(">", 0, VirtualPartyMenu.getNumOptions());
			// get the VirtualPartyMenu option corresponding to the guest input 
			VirtualPartyMenu option = VirtualPartyMenu.getOption(userChoice);
			// switch statement to match 
			// the guest-selected Menu option 
			// with the corresponding method or 
			// set of actions. 
			switch(option)
			{
			case VIEW_NAME: 
				// display the Zoom room name
				System.out.println("Zoom Room : " + this.getZoomRoomName());
				break; 
			case VIEW_CODE:
				// display the Zoom room code
				System.out.println("Zoom Code : " + this.zoomRoomCode);
				break;
			case VIEW_PASSWORD:
				// display the Zoom room password
				System.out.println("Zoom Password : " + this.zoomRoomPassword);
				break;
			case UPDATE_RSVP:
				super.updateRSVP(guest);
				break;
			case VIEW_RSVP:
				super.showRSVP(guest);	
				break;
			case EXIT: 
				exitVirtualMessage();
				break;
			default:
				System.out.println(option + " not implemented yet");
			}
		}
	}
	
	// Method Name: exitVirtualMessage() 
	// Parameters: None 
	// Return Value: None 
	// This method displays an exit message to the guest. 
	public void exitVirtualMessage()
	{
		System.out.println("Exiting the Virtual Party Menu now...");
	}
	
	// Method Name: toString()
	// Parameters: None
	// Return Value: String
	@Override 
	public String toString() 
	{
		String msg = partyCategory + " " + partyType + "\n" 
				+ getPartyName() + " \non " + getPartyDate() 
				+ " at " + getPartyTime() + " \nat " + getPartyLocation()
				+ " for " + getPartyDurationHours() + " hours!!!" 
				+ "\nZoom Room: " + getZoomRoomName() + "\nZoom Code: " + getZoomRoomCode()
				+ "\nZoom Password: " + getZoomRoomPassword();  
		return msg; 
	}
	
}
