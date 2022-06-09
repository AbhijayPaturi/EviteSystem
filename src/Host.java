import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Host extends GuestRegistered {

	private List<Party> partyList;
	private BFF bff;
	
	public Host(String email, String firstName, String lastName, String password) {
		super(email, firstName, lastName, password);
		partyList = new ArrayList<>();
		bff = new BFF();
	}

	// getter and setter for instance variable
	/**
	 * @return the partyList
	 */
	public List<Party> getPartyList() {
		return partyList;
	}

	/**
	 * @param partyList the partyList to set
	 */
	public void setPartyList(List<Party> partyList) {
		this.partyList = partyList;
	}
	
	// other methods 
	// Method Name: chooseModifyAction()
	// Parameters: None 
	// Return Value: None
	// This method allows the guest to modify the attributes of a Party. 
	public void chooseModifyAction() 
	{
		// Display message to guest 
		System.out.println("You can modify one Party per selection of this action, " + GuestMenu.MODIFY_PARTY
				+ " (" + GuestMenu.MODIFY_PARTY.getDisplayString() + ")!");
		showHostParties();
		// branching statement to identify the size of the list of parties 
		if (partyList.size() > 0)
		{
			// get guest input on which Party the guest would like to modify
			int partyChoice = bff.inputInt("\nWhich Party would you like to modify?", 0, partyList.size());
			// variable storing the selected Party
			Party hostSelectedParty = partyList.get(partyChoice);
			
			int userMenuChoice = 0; 
			// while loop to allow the guest to 
			// keep interacting with the Menu until 
			// the guest chooses to Exit. 
			while (userMenuChoice != (ModifyPartyMenu.values().length - 1))
			{
				// display the options to the guest
				ModifyPartyMenu.printMenuOptions();
				// get guest input of menu option 
				userMenuChoice = bff.inputInt(">", 0, ModifyPartyMenu.getNumOptions());
				// get the ModifyPartyMenu option corresponding to the user input 
				ModifyPartyMenu option = ModifyPartyMenu.getOption(userMenuChoice);
				// switch statement to match 
				// the guest-selected Menu option 
				// with the corresponding method or 
				// set of actions. 
				switch(option)
				{
				case TITLE: 
					changeTitle(hostSelectedParty);
					break; 
				case DATE:
					changeDate(hostSelectedParty);
					break;
				case TIME:
					changeTime(hostSelectedParty);
					break;
				case DURATION: 
					changeDuration(hostSelectedParty);
					break;
				case LOCATION: 
					changeLocation(hostSelectedParty);
					break; 
				case REMOVE_GUESTS:
					removeGuests(hostSelectedParty);
					break; 
				case EXIT: 
					exitMessage();
					break;
				default:
					System.out.println(option + " not implemented yet");
				}
			}
		}
	}
		
	// Method Name: exitMessage() 
	// Parameters: None 
	// Return Value: None
	// This method displays an exit message to the guest.
	private void exitMessage() {
		System.out.println("Exiting the menu to modify a Party...");
	}

	// Method Name: removeGuests()
	// Parameters: p is a Party
	// Return Value: None
	// This method calls another method to remove a guest from the list of invited guests.
	private void removeGuests(Party p) {
		p.removeGuests();
	}

	// Method Name: changeLocation()
	// Parameters: p is a Party
	// Return Value: None
	// This method allows the user to change the location of the party.
	private void changeLocation(Party p) {
		// get input of the new party location
		String newLocation = bff.inputLine("Enter the name of the new party location:");
		// branching statement to check party locations
		if (!(p.getPartyLocation().equalsIgnoreCase(newLocation)))
		{
			// if not the same, set the new party location 
			p.setPartyLocation(newLocation);
			System.out.println("Location of the party changed!");
		}
		else 
		{
			System.out.println("Location of the party could not be changed unfortunately. The locatins are the same!");
		}
		
	}

	// Method Name: changeDuration()
	// Parameters: p is a Party
	// Return Value: None
	// This method allows the user to change the duration of the party.
	private void changeDuration(Party p) {
		// get input of the new party duration
		int newDuration = bff.inputInt("Enter the time in hours of the new party duration:");
		// branching statement to check party durations
		if (!(p.getPartyDurationHours() == newDuration))
		{
			// if not the same, set the new party duration
			p.setPartyDurationHours(newDuration);
			System.out.println("Duration in hours of the party changed!");
		}
		else 
		{
			System.out.println("Duration in hours of the party could not be changed unfortunately. The durations are the same!");
		}
		
	}

	// Method Name: changeTime()
	// Parameters: p is a Party
	// Return Value: None
	// This method allows the user to change the time of the party.
	private void changeTime(Party p) {
		// get inputs of the new party time
		int newHour = bff.inputInt("Enter the new hour of the party", 1, 23);
		int newMin = bff.inputInt("Enter the new min of the party", 0, 59);
	
		// branching statement to check party times
		if (!(newHour == p.getPartyDateYear() && newMin == p.getPartyDateMonth()))
		{
			// if not the same, set the new party time
			p.setPartyTimeHour(newHour);
			p.setPartyTimeMin(newMin);
			System.out.println("Time of the party changed!");
		}
		else 
		{
			System.out.println("Time of the party could not be changed unfortunately. The times are the exact same.");
		}
		
	}

	// Method Name: changeDate()
	// Parameters: p is a Party
	// Return Value: None
	// This method allows the user to change the date of the party.
	private void changeDate(Party p) {
		// get inputs of the new party date
		int newYear = bff.inputInt("Enter the new year of the party");
		int newMonth = bff.inputInt("Enter the new month of the party", 1, 12);
		Month month = Month.of(newMonth);
		int newDay = bff.inputInt("Enter the new day of the party", 1, month.length(false));
		// branching statement to check party dates
		if (!(newYear == p.getPartyDateYear() && newMonth == p.getPartyDateMonth() 
				&& newDay == p.getPartyDateDay()))
		{
			// if not the same, set the new party date
			p.setPartyDateYear(newYear);
			p.setPartyDateMonth(newMonth);
			p.setPartyDateDay(newDay);
			System.out.println("Date of the party changed!");
		}
		else 
		{
			System.out.println("Date of the party could not be changed unfortunately. The dates are the exact same.");
		}
		
	}
	
	// Method Name: changeTitle()
	// Parameters: p is a Party
	// Return Value: None
	// This method allows the user to change the title of the party.
	private void changeTitle(Party p) {
		// get input of the new party name
		String newPartyName = bff.inputLine("Enter the name of the new party name:");
		// branching statement to check party titles
		if (!(p.getPartyName().equals(newPartyName)))
		{
			// if not the same, set the new party title
			p.setPartyName(newPartyName);
			System.out.println("Name of the party changed!");
		}
		else 
		{
			System.out.println("Name of the party could not be changed unfortunately. The names are the same!");
		}
	}

	// Method Name: showHostParties()
	// Parameters: None 
	// Return Value: None 
	// This program displays the parties that the Host is hosting. 
	public void showHostParties()
	{
		// display message 
		System.out.println("Showing Parties " + super.getFirstName() + " is Hosting\n");
		
		// branching statement to identify if the Host is hosting any parties
		if (partyList.size() != 0)
		{
			// for loop to loop through the list of parties 
			for (int i = 0; i < partyList.size(); i++)
			{
				System.out.println("\t" + i + ") " + partyList.get(i));
			}
		}
		else 
		{
			System.out.println("\t" + super.getFirstName() + " have not created any parties yet.\n");
		}
	}
	
	// Method Name: createParty()
	// Parameters: None 
	// Return Value: Party 
	// This method creates a new Party. 
	public Party createParty()
	{
		Party p;
		// get host input on the attributes of the party 
		String partyName = bff.inputLine("What is the name of your new party?");
		int partyDateYear = bff.inputInt("Enter the year of the party:");
		int partyDateMonth = bff.inputInt("Enter the month of the party:", 1, 12);
		Month month = Month.of(partyDateMonth);
		int partyDateDay = bff.inputInt("Enter the day of the party:", 0, month.length(false));
		int partyTimeHour = bff.inputInt("Enter the hour the party will start at:", 0, 23);
		int partyTimeMin = bff.inputInt("Enter the minute the party will start at:", 0, 59);
		int partyDurationHours = bff.inputInt("Enter the duration in hours of the party:", 0, 50);
		String partyLocation = bff.inputLine("Enter the location of the party:");
		
		// display the options of Party types 
		PartyType.printMenuOptions();
		// get host input on the Party Type
		int partyTypeChoice = bff.inputInt("Which party type would you like to select?", 0, PartyType.values().length);
		// String storing the selected Party type
		String partyType = PartyType.getOption(partyTypeChoice).toString();
		
		// display the options of Party categories 
		PartyCategory.printMenuOptions();
		// get host input on the Party category 
		int partyCategoryChoice = bff.inputInt("Which party category would you like to select?", 0, PartyCategory.values().length);
		
		// branching statement to identify which Party type the host selected
		if (partyType.equals(PartyType.POTLUCK.toString()))
		{
			// create new PotLuck party
			p = new PotLuck(partyName, partyDateYear, partyDateMonth, partyDateDay, partyTimeHour, partyTimeMin, 
					partyDurationHours, partyLocation, this.getEmail(), 
					PartyCategory.getOption(partyCategoryChoice));
			
		}
		
		else if (partyType.equals(PartyType.GIFTEXCHANGE.toString()))
		{
			// create new GiftExchange party
			p = new GiftExchange(partyName, partyDateYear, partyDateMonth, partyDateDay, partyTimeHour, partyTimeMin, 
					partyDurationHours, partyLocation,  this.getEmail(), 
					PartyCategory.getOption(partyCategoryChoice));
		}
		
		else if (partyType.equals(PartyType.VIRTUAL.toString()))
		{
			// get host input on the Zoom details 
			String zoomRoomName = bff.inputLine("Enter the name of the zoom room hosting this party:");
			int zoomRoomCode = bff.inputInt("Enter the code of the zoom room hosting this party:");
			String zoomRoomPassword = bff.inputWord("Enter the password (one word/String) of the zoom room hosting this party:");
			
			// create new VirtualParty party
			p = new VirtualParty(partyName, partyDateYear, partyDateMonth, partyDateDay, partyTimeHour, partyTimeMin, 
					partyDurationHours, partyLocation,  this.getEmail(), 
					PartyCategory.getOption(partyCategoryChoice), 
					zoomRoomName, zoomRoomCode, zoomRoomPassword);
		}
		
		// assuming if (partyType.equals(PartyType.HOSTED.toString())) as it is the last remaining option
		else
		{
			// get host input on the cake description
			String cake = bff.inputLine("Enter the description of the cake at this party:");
			
			// create new Hosted party
			p = new Hosted(partyName, partyDateYear, partyDateMonth, partyDateDay, partyTimeHour, partyTimeMin, 
					partyDurationHours, partyLocation,  this.getEmail(), 
					PartyCategory.getOption(partyCategoryChoice), 
					cake);
			Hosted partyHosted = (Hosted) p;
			partyHosted.addAppetizer();
			partyHosted.addMainCourse();
			partyHosted.addDessert();
			
		}
		// set the host email 
		p.setHostEmail(this.getEmail());
		// display message
		System.out.println("Created:\n\t" + p.toString());
		// add the guests 
		p.addGuests();
		// add the Party to the list of parties 
		partyList.add(p);
		// display message
		System.out.println("Done creating a party! Wonderful!");
		
		return p;
		
	}
	
	// Method Name: addParty()
	// Parameters: p is a Party, partyList is a List<Party>
	// Return Value: None
	// This program adds a Party to a list of parties. 
	public void addParty(Party p)
	{
		// branching statement to identify if the parameter Party is already in the list of parties
		if (!(partyList.contains(p)))
		{
			// add Party to list of parties
			partyList.add(p);
			//System.out.println("Party added!");
		}
	}
	
	// Method Name: toString()
	// Parameters: None
	// Return Value: String
	@Override 
	public String toString()
	{
		String msg = "Host:" + super.getFirstName();
		//this.showHostParties(); 
		return msg;
	}

}