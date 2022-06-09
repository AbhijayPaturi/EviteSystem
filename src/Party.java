import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Party {
	
	// instance variables
	private String hostEmail;
	private String partyName; 
	private int partyDateYear;
	private int partyDateMonth;
	private int partyDateDay;
	private LocalDate partyDate;
	private int partyTimeHour;
	private int partyTimeMin;
	private LocalTime partyTime;
	private int partyDurationHours;
	private String partyLocation;
	private Map<GuestResponse, List<String>> RsvpToGuestsMap;
	private BFF bff;

	// Constructor 
	// initialize instance variables 
	public Party(String partyName, int partyDateYear, int partyDateMonth, 
			int partyDateDay, int partyTimeHour, int partyTimeMin,
			int partyDurationHours, String partyLocation, String hostEmail) 
	{
		this.partyName = partyName; 
		this.partyDateYear = partyDateYear;
		this.partyDateMonth = partyDateMonth; 
		this.partyDateDay = partyDateDay;
		partyDate = LocalDate.of(partyDateYear, partyDateMonth, partyDateDay);
		this.partyTimeHour = partyTimeHour;
		this.partyTimeMin = partyTimeMin;
		partyTime = LocalTime.of(partyTimeHour, partyTimeMin);
		this.partyDurationHours = partyDurationHours;
		this.partyLocation = partyLocation;
		this.hostEmail = hostEmail;
		RsvpToGuestsMap = createRsvpToGuestsMap();;
		bff = new BFF();
		
	}

	// getters and setters for instance variables 
	/**
	 * @return the partyName
	 */
	public String getPartyName() {
		return partyName;
	}

	/**
	 * @param partyName the partyName to set
	 */
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/**
	 * @return the partyDateYear
	 */
	public int getPartyDateYear() {
		return partyDateYear;
	}

	/**
	 * @param partyDateYear the partyDateYear to set
	 */
	public void setPartyDateYear(int partyDateYear) {
		this.partyDateYear = partyDateYear;
	}

	/**
	 * @return the partyDateMonth
	 */
	public int getPartyDateMonth() {
		return partyDateMonth;
	}

	/**
	 * @param partyDateMonth the partyDateMonth to set
	 */
	public void setPartyDateMonth(int partyDateMonth) {
		this.partyDateMonth = partyDateMonth;
	}

	/**
	 * @return the partyDateDay
	 */
	public int getPartyDateDay() {
		return partyDateDay;
	}

	/**
	 * @param partyDateDay the partyDateDay to set
	 */
	public void setPartyDateDay(int partyDateDay) {
		this.partyDateDay = partyDateDay;
	}

	/**
	 * @return the partyDate
	 */
	public LocalDate getPartyDate() {
		return partyDate;
	}

	/**
	 * @param partyDate the partyDate to set
	 */
	public void setPartyDate(LocalDate partyDate) {
		this.partyDate = partyDate;
	}

	/**
	 * @return the partyTimeHour
	 */
	public int getPartyTimeHour() {
		return partyTimeHour;
	}

	/**
	 * @param partyTimeHour the partyTimeHour to set
	 */
	public void setPartyTimeHour(int partyTimeHour) {
		this.partyTimeHour = partyTimeHour;
	}

	/**
	 * @return the partyTimeMin
	 */
	public int getPartyTimeMin() {
		return partyTimeMin;
	}

	/**
	 * @param partyTimeMin the partyTimeMin to set
	 */
	public void setPartyTimeMin(int partyTimeMin) {
		this.partyTimeMin = partyTimeMin;
	}

	/**
	 * @return the partyTime
	 */
	public LocalTime getPartyTime() {
		return partyTime;
	}

	/**
	 * @param partyTime the partyTime to set
	 */
	public void setPartyTime(LocalTime partyTime) {
		this.partyTime = partyTime;
	}

	/**
	 * @return the partyDurationHours
	 */
	public int getPartyDurationHours() {
		return partyDurationHours;
	}

	/**
	 * @param partyDurationHours the partyDurationHours to set
	 */
	public void setPartyDurationHours(int partyDurationHours) {
		this.partyDurationHours = partyDurationHours;
	}

	/**
	 * @return the partyLocation
	 */
	public String getPartyLocation() {
		return partyLocation;
	}

	/**
	 * @param partyLocation the partyLocation to set
	 */
	public void setPartyLocation(String partyLocation) {
		this.partyLocation = partyLocation;
	}
	
	/**
	 * @return the rsvpToGuestsMap
	 */
	public Map<GuestResponse, List<String>> getRsvpToGuestsMap() {
		return RsvpToGuestsMap;
	}

	public String getRsvpToGuestsMapString() {
		String invitedGuestsString = "";
		
		for (GuestResponse rsvp : RsvpToGuestsMap.keySet())
		{
			for (String email : RsvpToGuestsMap.get(rsvp))
			{
				invitedGuestsString += rsvp + "," + email + ",";
			}
		}
		
		return invitedGuestsString;
	}

	/**
	 * @param rsvpToGuestsMap the rsvpToGuestsMap to set
	 */
	public void setRsvpToGuestsMap(Map<GuestResponse, List<String>> rsvpToGuestsMap) {
		RsvpToGuestsMap = rsvpToGuestsMap;
	}
	
	/**
	 * @return the hostEmail
	 */
	public String getHostEmail() {
		return hostEmail;
	}

	/**
	 * @param hostEmail the host email to set
	 */
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}
	
	// Method Name: toString()
	// Parameters: None
	// Return Value: String
	@Override
	public String toString() 
	{
		String message = partyName + " is starting at " + partyTime + 
				" on " + partyDate + " at " + partyLocation; 
		return message;
	}
	
	// other methods 
	// Method Name: createRsvpToGuestsMap()
	// Parameters: None 
	// Return Value: Map<GuestResponse, List<String>> 
	// This program creates the RsvpToGuestsMap Map. 
	public Map<GuestResponse, List<String>> createRsvpToGuestsMap() {
		RsvpToGuestsMap = new HashMap<>();
		List<String> guestList; 
		// for loop to loop through the possible RSVP choices
		for (int i = 0; i < GuestResponse.values().length; i++)
		{
			// create new ArrayList
			guestList = new ArrayList<>();
			// put RSVP choice and empty ArrayList into the Map
			RsvpToGuestsMap.put(GuestResponse.getOption(i), guestList);
		}
		return RsvpToGuestsMap;
	}

	// Method Name: addGuests()
	// Parameters: None 
	// Return Value: None 
	// This method adds guest emails to the list of invited guest emails to the Party.
	public void addGuests()
	{
		String continueAdding; 
		// initialize 
		boolean added = true;
		// initialize 
		GuestResponse rsvpResponse = GuestResponse.NO_RESPONSE;
		// get host input on if the host would like to add guests
		continueAdding = bff.inputWord("Would you like to add guests (Type \"y\" or \"n\")?", "y", "n");
		// while loop to allow the host to continue adding guests
		while (continueAdding.equalsIgnoreCase("y"))
		{
			// get host input on the guest email
			String guestEmail = bff.inputWord("Enter the email of the guest:\n");
			// for loop to loop through the keys of the Map
			for (GuestResponse rsvp : RsvpToGuestsMap.keySet())
			{
				// for loop to loop through the list of guest emails for each RSVP choice 
				for (String invitedEmail : RsvpToGuestsMap.get(rsvp))
				{
					// branching statement to check if emails match
					if (invitedEmail.equals(guestEmail))
					{
						// set boolean to false
						added = false;
						// get RSVP choice 
						rsvpResponse = rsvp;
					}
				}
			}
			
			// branching statement to add guest to the Map
			if (added)
			{
				List<String> guestEmailsList = RsvpToGuestsMap.get(rsvpResponse);
				guestEmailsList.add(guestEmail);
				RsvpToGuestsMap.put(rsvpResponse, guestEmailsList);
			}
			else 
			{
				// Display message 
				System.out.println("Guest already invited.");
			}
	
			// get host input on if the host would like to continue adding more guests
			continueAdding = bff.inputWord("Would you like to add more guests(Type \"y\" or \"n\")?", "y", "n");
		}	
		
		System.out.println("Done adding guests. Sending invites now.");
	}
	
	public void removeGuests()
	{
		String continueRemoving; 
		// initialize 
		boolean removed = false;
		// initialize 
		GuestResponse rsvpResponse = GuestResponse.NO_RESPONSE;
		// get host input on if the host would like to add guests
		continueRemoving = bff.inputWord("Would you like to remove guests?", "y", "n");
		// while loop to allow the host to continue removing guests
		while (continueRemoving.equalsIgnoreCase("y"))
		{
			// get host input of the guest email 
			String guestEmail = bff.inputWord("Enter the email of the guest:\n");
			// for loop to loop through the keys of the Map
			for (GuestResponse rsvp : RsvpToGuestsMap.keySet())
			{
				// for loop to loop through the list of guest emails for each RSVP choice 
				for (String invitedEmail : RsvpToGuestsMap.get(rsvp))
				{
					// branching statement to check if emails match
					if (invitedEmail.equals(guestEmail))
					{
						// set boolean to true
						removed = true;
						// get RSVP choice 
						rsvpResponse = rsvp;
					}
				}
			}
			
			// branching statement to remove guest from the Map
			if (removed)
			{
				List<String> guestEmailsList = RsvpToGuestsMap.get(rsvpResponse);
				guestEmailsList.remove(guestEmail);
				RsvpToGuestsMap.put(rsvpResponse, guestEmailsList);
			}
			else 
			{
				// Display message
				System.out.println("Guest not in the list of invites.");
			}
			// get host input on if the host would like to continue removing more guests
			continueRemoving = bff.inputWord("Would you like to remove more guests?", "y", "n");
		}
		
		// branching statement for display message
		if (removed)
		{
			System.out.println("Guest has been removed.");
		}
		else 
		{
			System.out.println("Guest has not been removed.");
		}
	}
	
	// Method Name: updateRSVP()
	// Parameter: guest is a GuestUnregistered 
	// Return Value: None 
	// This method updates the RSVP to a Party for a guest.
	public void updateRSVP(GuestUnregistered guest)
	{
		// display RSVP choices 
		GuestResponse.printMenuOptions();
		// get guest input on the RSVP choice
		int guestChoice = bff.inputInt("Choose an option:\n>", 0, GuestResponse.values().length);
		// store the selected RSVP choice 
		GuestResponse newRsvp = GuestResponse.getOption(guestChoice);
		
		// store the current RSVP of the guest
		GuestResponse oldRsvp = getCurrentRSVP(guest);
		List<String> rsvpList;
		// for loop to loop through the list of possible RSVP choices 
		for (GuestResponse rsvp : GuestResponse.values())
		{
			// branching statement to check if rsvps match
			if (newRsvp == rsvp)
			{
				// get List of guest email correlated to the new RSVP
				rsvpList = RsvpToGuestsMap.get(rsvp);
				// add guest to this List
				rsvpList.add(guest.getEmail());
				// put the key and corresponding value back into the Map
				RsvpToGuestsMap.put(rsvp, rsvpList);
			}
		}
		
		// get List of guest email correlated to the previous RSVP
		rsvpList = RsvpToGuestsMap.get(oldRsvp);
		// remove the guest email from this List
		rsvpList.remove(guest.getEmail());
		// put the key and corresponding value back into the Map
		RsvpToGuestsMap.put(oldRsvp, rsvpList);

		// display message
		System.out.println("RSVP updated to " + newRsvp.getDisplayString() + "!");
	}
	
	// Method Name: getCurrentRSVP()
	// Parameter: guest is a GuestUnregistered 
	// Return Value: GuestResponse 
	// This method gets the RSVP to a Party for a guest.
	public GuestResponse getCurrentRSVP(GuestUnregistered guest)
	{
		GuestResponse currRsvp;
		// set to default value
		currRsvp = GuestResponse.NO_RESPONSE;
		// for loop to loop through the possible RSVP choices
		for (GuestResponse rsvp : GuestResponse.values())
		{
			// for loop to loop through the guest emails for each RSVP choice
			for (String invitedEmail : RsvpToGuestsMap.get(rsvp))
			{
				// branching statement to check if emails match
				if (invitedEmail.equals(guest.getEmail()))
				{
					// store this RSVP 
					currRsvp = rsvp;
				}
			}
		}
		
		// return the current RSVP of the guest
		return currRsvp;
		
	}
	
	// Method Name: showRSVP()
	// Parameter: guest is a GuestUnregistered 
	// Return Value: None
	// This method show the RSVP to a Party for a guest.
	public void showRSVP(GuestUnregistered guest)
	{
		GuestResponse currentRsvp; 
		// set to default value (hoping all invited guests attend the Party!!)
		currentRsvp = GuestResponse.YES;
		// boolean variable; default set to false
		boolean found = false;
		// for loop to loop through all the possible RSVP choices
		for (GuestResponse rsvp : GuestResponse.values())
		{
			// for loop to loop through the guest emails for each RSVP choice
			for (String invitedEmail : RsvpToGuestsMap.get(rsvp))
			{
				// branching statement to check if emails match
				if (invitedEmail.equals(guest.getEmail()))
				{
					// set boolean to true 
					found = true;
					// store this RSVP 
					currentRsvp = rsvp;
				}
			}
		}
		
		// branching statement to display message
		if (found)
		{
			// display current Rsvp to guest
			System.out.println("You RSVP'ed " + currentRsvp.getDisplayString() + " to " + this.hostEmail + "'s " + this.partyName);
		}
		else 
		{
			// display message  to guest
			System.out.println("You are not on the list of invited guests to " + this.hostEmail + "'s " + this.partyName);
		}
	}
	
}
