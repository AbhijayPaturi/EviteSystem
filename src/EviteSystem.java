import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EviteSystem {

	// instance variables 
	private List<GuestUnregistered> guestsList;
	private List<Party> partyList;
	private Map<Host, List<Party>> hostPartyMap;
	private BFF bff;
	
	// Constructor 
	// initialize the instance variables 
	public EviteSystem() {
		guestsList = new ArrayList<>();
		partyList = new ArrayList<>();
		hostPartyMap = new HashMap<>();
		bff = new BFF();
	}
	
	// getters and setters for instance variables 
	/**
	 * @return the guestsList
	 */
	public List<GuestUnregistered> getGuestsList() {
		return guestsList;
	}

	/**
	 * @param guestsList the guestsList to set
	 */
	public void setGuestsList(List<GuestUnregistered> guestsList) {
		this.guestsList = guestsList;
	}

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

	// methods 
	// Method Name: chooseAction() 
	// Parameters: None 
	// Return Value: None 
	// This method allows guests to interact with the hierarchies 
	// by selecting an option from a menu of options. 
	public void chooseAction()
	{
		// String storing the email of the guest on the system
		String guestEmail = getGuestEmail();
		// boolean representing whether the guest is a registered guest or not
		boolean registered = getRegistrationStatus(guestEmail);
		GuestUnregistered guest;
		// branching statement to allow guest to login or create an account 
		// depending on their registration status. 
		if (!registered)
		{
			guest = createAccount(guestEmail);
		}
		else 
		{
			guest = logIn(guestEmail);
		}
		
		// branching statement to identify which menu of options 
		// the guest can interact with 
		if (guest instanceof GuestRegistered)
		{
			// create Host (inherits GuestRegistered attributes and methods)
			Host registeredHostAndGuest = (Host) guest;
			// variable storing the number of the choice of action the guest chooses 
			int userChoice = 55; 
			// while loop to allow the guest to 
			// keep interacting with the Menu until 
			// the guest chooses to LOGOUT. 
			while (userChoice != (GuestMenu.values().length - 1))
			{
				// call updateLists() to update the Collections 
				// in each of the inheritance hierarchies. 
				updateCollections(registeredHostAndGuest);
				System.out.println();
				// display the options to the guest
				GuestMenu.printMenuOptions();
				// get guest input of menu option 
				userChoice = bff.inputInt(">", 0, GuestMenu.getNumOptions());
				// get the GuestMenu option corresponding to the guest input 
				GuestMenu option = GuestMenu.getOption(userChoice);
				// switch statement to match 
				// the guest-selected Menu option 
				// with the corresponding method or 
				// set of actions. 
				switch(option)
				{
				case VIEW_PARTIES: 
					// List of parties guest is invited to
					List<Party> guestInvitedPartyList = getInvitedParties(guest);
					// display the list of parties the guest is invited to
					showInvitedParties(guest, guestInvitedPartyList);
					// branching statement to allow guest to select a Party the guest is invited to
					// if the guest is invited to at least one Party.
					if (guestInvitedPartyList.size() > 0)
					{
						// variable storing the guest's Party selection number
						int partySelection = bff.inputInt("\nChoose a party invitation to view:", 0, guestInvitedPartyList.size());
						// variable storing the guest's selected Party 
						Party selectedParty = guestInvitedPartyList.get(partySelection);
						// branching statements to identify which type of Party the guest selected 
						// to allow the guest to interact with the menu of options corresponding 
						// to that specific Party type. 
						if (selectedParty instanceof PotLuck)
						{
							// type cast
							PotLuck potluck = (PotLuck) selectedParty;
							potluck.potLuckAction(guest);
						}
						else if (selectedParty instanceof GiftExchange)
						{
							// type cast
							GiftExchange giftParty = (GiftExchange) selectedParty;
							giftParty.giftExchangeAction(guest);
						}
						else if (selectedParty instanceof VirtualParty)
						{
							// type cast
							VirtualParty virtualParty = (VirtualParty) selectedParty;
							virtualParty.virtualAction(guest);
						}
						else if (selectedParty instanceof Hosted)
						{
							// type cast
							Hosted hostedParty = (Hosted) selectedParty;
							hostedParty.hostedAction(guest);
						}
					}
					break; 
				case CREATE_PARTY:
					createParty(registeredHostAndGuest);
					break;
				case MODIFY_PARTY:
					modifyParty(registeredHostAndGuest);
					break;
				case LOGOUT: 
					quitMessage();
					break;
				default:
					System.out.println(option + " not implemented yet");
				}
			}
		}
		else
		{
			int userChoice = 0; 
			// while loop to allow the guest to 
			// keep interacting with the Menu until 
			// the guest chooses to LOGOUT. 
			while (userChoice != (GuestMenu.values().length - 1))
			{
				// display the options to the guest
				GuestMenu.printMenuOptions(0, GuestMenu.values().length - 1);
				// get guest input of menu option 
				userChoice = bff.inputInt(">", 0, GuestMenu.getNumOptions());
				// get the GuestMenu option corresponding to the guest input 
				GuestMenu option = GuestMenu.getOption(userChoice);
				// switch statement to match 
				// the guest-selected Menu option 
				// with the corresponding method or 
				// set of actions. 
				switch(option)
				{
				case VIEW_PARTIES: 
					// List of parties guest is invited to
					List<Party> guestInvitedPartyList = getInvitedParties(guest);
					// display the list of parties the guest is invited to
					showInvitedParties(guest, guestInvitedPartyList);
					// branching statement to allow guest to select a Party the guest is invited to
					// if the guest is invited to at least one Party.
					if (guestInvitedPartyList.size() > 0)
					{
						// variable storing the guest's Party selection number
						int partySelection = bff.inputInt("\nChoose a party invitation to view:", 0, guestInvitedPartyList.size());
						// variable storing the guest's selected Party 
						Party selectedParty = guestInvitedPartyList.get(partySelection);
						// branching statements to identify which type of Party the guest selected 
						// to allow the guest to interact with the menu of options corresponding 
						// to that specific Party type. 
						if (selectedParty instanceof PotLuck)
						{
							// type cast
							PotLuck potluck = (PotLuck) selectedParty;
							potluck.potLuckAction(guest);
						}
						else if (selectedParty instanceof GiftExchange)
						{
							// type cast
							GiftExchange giftParty = (GiftExchange) selectedParty;
							giftParty.giftExchangeAction(guest);
						}
						else if (selectedParty instanceof VirtualParty)
						{
							// type cast
							VirtualParty virtualParty = (VirtualParty) selectedParty;
							virtualParty.virtualAction(guest);
						}
						else if (selectedParty instanceof Hosted)
						{
							// type cast
							Hosted hostedParty = (Hosted) selectedParty;
							hostedParty.hostedAction(guest);
						}
					}
					break;
				case LOGOUT:
					quitMessage();
					break;
				default:
					System.out.println(option + " not implemented yet");
				}
			}
		}
		// display exit message
		exitEviteMessage();
		
	}
	
	// Method Name: modifyParty()
	// Parameter: host is a Host 
	// Return Value: None 
	// This program calls another method 
	// to modify the attributes of a Party.
	private void modifyParty(Host host) {
		host.chooseModifyAction();
	}
	
	// Method Name: createParty()
	// Parameter: host is a Host 
	// Return Value: None 
	// This program calls another method to 
	// create a Party.
	private void createParty(Host host) {
		Party p = host.createParty();
		// add Party to list of parties
		partyList.add(p);
		host.addParty(p);
	}
	
	// Method Name: getInvitedParties()
	// Parameter: unregisteredGuest is a GuestUnregistered
	// Return Value: List<Party>
	// This program returns a list of parties a guest is invited to. 
	public List<Party> getInvitedParties(GuestUnregistered unregisteredGuest)
	{
		List<Party> invitedPartyList; 
		// initialize List
		invitedPartyList = new ArrayList<>();
		// Display message
		System.out.println("Showing Invited Parties\n");
		// outer for loop to loop through list of parties
		for (int i = 0; i < partyList.size(); i++)
		{
			Party partyHolder = partyList.get(i);
			// inner for loop to loop through the RSVP choices
			for (GuestResponse rsvp : GuestResponse.values())
			{
				// branching statement to identify if the guest is invited to this party
				if (partyHolder.getRsvpToGuestsMap().get(rsvp).contains(unregisteredGuest.getEmail()))
				{
					// add Party to the list of invited parties
					invitedPartyList.add(partyHolder);
				}
			}
			
		}
		// return the list of parties the guest is invited to
		return invitedPartyList;
		
	}
	
	// Method Name: getInvitedParties()
	// Parameter: unregisteredGuest is a GuestUnregistered
	// Return Value: None
	// This program displays each of the parties a guest is invited to.
	public void showInvitedParties(GuestUnregistered guest, List<Party> partyList)
	{
		// branching statement to identify the size of the list of parties
		if (partyList.size() == 0)
		{
			// Display message
			System.out.println("\t" + guest.getEmail() + " have not been invited to any parties yet.\n");
		}
		else 
		{
			// for loop to loop through the list of parties
			for (int i = 0; i < partyList.size(); i++)
			{
				// Display each Party
				System.out.println(i + ")\n" + partyList.get(i));
			}
		}
	}
	
	// Method Name: quitMessage()
	// Parameters: None
	// Return Value: None 
	// This program displays a message when exiting the menu.
	private void quitMessage() {
		System.out.println("Exiting the system...");
	}

	// Method Name: exitEviteMessage()
	// Parameters: None
	// Return Value: None 
	// This program displays a message when exiting the EviteSystem.
	private void exitEviteMessage()
	{
		System.out.println("Thanks for using the Evite System!\nHope you have fun at your parties! See you soon!");
	}

	// Method Name: getRegistrationStatus()
	// Parameter: email is a String
	// Return Value: boolean representing whether a guest is registered 
	// This program checks if a guest's email is registered with the EviteSystem.
	private boolean getRegistrationStatus(String email) {
		// boolean with default value of false
		boolean guestRegistered = false;
		// for-each loop to loop through List of guests
		for (GuestUnregistered guest : guestsList)
		{
			// branching statement to identify if the emails match
			if (guest.getEmail().equals(email) && (guest instanceof GuestRegistered))
			{
				// set boolean to true if emails match and guest is of type GuestRegistered or Host
				guestRegistered = true;
			}
		}
		
		// return the boolean value
		return guestRegistered;
	}

	// Method Name: createAccount()
	// Parameter: email is a String
	// Return Value: GuestUnregistered
	// This program creates an account for a guest who wants to register an account. 
	public GuestUnregistered createAccount(String email)
	{
		// get guest input on whether the guest would like to reigster an account
		String registrationStatus = bff.inputWord("Would you like to register an account(Type \"y\" or \"n\")?", "y", "n");
		GuestUnregistered newGuest;
		// branching statement to identify the guest's response
		if (registrationStatus.equals("y"))
		{
			// get guest input on information pertaining to a registered guest
			String firstName = bff.inputWord("Enter your first name:");
			String lastName = bff.inputWord("Enter your last name:");
			String password = bff.inputWord("Enter your password:");
			newGuest = new Host(email, firstName, lastName, password);
			System.out.println("Wonderful, an account has been created!");
			
			// add guest to list of guests
			guestsList.add(newGuest);
		}
		else 
		{
			// displayMessage
			System.out.println("Without a registered account, you can only view invitations and RSVP.");
			// guest is not registered
			newGuest = new GuestUnregistered(email);
			// add guest to list of guests
			guestsList.add(newGuest);
		}
		
		// return the guest object
		return newGuest;
	}
	
	// Method Name: getGuestEmail()
	// Parameters: None 
	// Return Value: String representing the email of the guest
	// This program returns the email of the guest who just entered the EviteSystem.
	public String getGuestEmail()
	{
		// Display message
		System.out.println("Welcome to the Evite System!\n"
				+ "The Evite System is designed to allow you to RSVP to or create your own party!\n");
		// get the guest's email
		String email = bff.inputWord("To get started, enter your email:");
		// return the guest's email
		return email;
	}
	
	// Method Name: logIn()
	// Parameter: email is a String 
	// Return Value: GuestUnregistered 
	// This program logs in a guest. 
	public GuestUnregistered logIn(String email)
	{
		System.out.println("Welcome back to the Evite System!");
		String password;
		int listIndex = 0;
		GuestUnregistered returnGuest;
		Host registeredGuest;
		
		// for loop to loop through the list of guests
		for (int i = 0; i < guestsList.size(); i++)
		{
			// branching statement to identify if emails match 
			if (guestsList.get(i).getEmail().equals(email))
			{
				listIndex = i;
			}
		}
		
		returnGuest = guestsList.get(listIndex);
		// branching statement to identify if the guest is a registered guest
		if (returnGuest instanceof GuestRegistered)
		{
			// type cast
			registeredGuest = (Host) returnGuest;
			// get the guest's password
			password = bff.inputWord("Enter your password:");
			// while loop to check the guest's password 
			while (!(registeredGuest.getPassword().equals(password)))
			{
				// get the guest's password if typed incorrectly
				password = bff.inputWord("Wrong! Please enter the correct password:");
			}
		}
		
		// display message
		System.out.println("You are logged in!");
		
		return returnGuest;
	}

	// Method Name: checkLogIn()
	// Parameters: guest is a GuestRegistered, email is a String, password is a String 
	// Return Value: boolean representing whether the guest is logged in. 
	// This program checks whether a guest is logged in or not, such as entering the 
	// correct password. 
	public boolean checkLogIn(GuestRegistered guest, String email, String password)
	{
		return guest.getEmail().equals(email) && guest.getPassword().equals(password);
	}	
	
	// Method Name: updateCollections()
	// Parameter: host is a Host
	// Return Value: None 
	// This method updates some of the collections in each inheritance hierarchy. 
	public void updateCollections(Host host)
	{
		// for loop to loop through the list of parties
		for (Party p : partyList)
		{
			// store the host's email
			String hostEmail = p.getHostEmail();
			// boolean variable; default value set to false
			boolean inMap = false;
			List<Party> hostPartyList;
			
			// branching statement to identify if the Map's size is greater than 0
			if (hostPartyMap.size() != 0)
			{
				// for loop to loop through the keys of the Map
				for (Host partyHost : hostPartyMap.keySet())
				{
					// branching statement to check emails
					if (partyHost.getEmail().equals(hostEmail))
					{
						// set boolean to true if the host's email is in the Map already
						inMap = true;
						// get value to the corresponding key
						hostPartyList = hostPartyMap.get(partyHost);
						// add party to the value
						hostPartyList.add(p);
						// put the updated list with the corresponding key back into the Map
						hostPartyMap.put(partyHost, hostPartyList);
						// add the Party to the list of Parties in the Host class
						partyHost.addParty(p);
						
					}
				}
			}
			else if (hostPartyMap.size() != 0 || !inMap)
			{
				hostPartyList = new ArrayList<>();
				// add the Party to the newly created ArrayList
				hostPartyList.add(p);
				// put the updated list with the corresponding key back into the Map
				hostPartyMap.put(host, hostPartyList);
				// add the Party to the list of Parties in the Host class
				host.addParty(p);
			}
			
			// adding each Party to each guest in the list of guests
			// for loop to loop through the RSVP choices
			for (GuestResponse rsvp : GuestResponse.values())
			{
				// for loop to loop through the corresponding value (List of Strings)
				for (String guestEmail : p.getRsvpToGuestsMap().get(rsvp))
				{
					// for loop to loop through each GuestUnregistered
					for (GuestUnregistered guest : guestsList)
					{
						// branching statement to check emails
						if (guestEmail.equals(guest.getEmail()))
						{
							// branching statement to check if guest is of type GuestRegistered
							if (guest instanceof GuestRegistered)
							{
								// type cast
								GuestRegistered registeredGuest = (GuestRegistered) guest;
								// add the Party to the list of parties in the GuestRegistered class
								registeredGuest.addParty(p, registeredGuest.getInvitedParties());
							}
						}
					}
				}
			}
			
		}
		
	}

	// Method Name: run()
	// Parameters: None 
	// Return Value: None 
	// This program allows the guest to enter the EviteSystem and 
	// calls the functions to make and read in .txt files containing data 
	// on the guests and parties. 
	public void run()
	{
		// String storing whether guests would like to keep using the EviteSystem
		String continueMenu = "y";
		// while loop to allow guests to keep interacting with the EviteSystem
		while (!continueMenu.equalsIgnoreCase("n"))
		{
			// read guests file
			ArrayList<String> data = FileReader.readFile("Guests.txt");
			// parse 
			setGuestsList(FileReader.parseGuest(data));
			// read parties file
			data = FileReader.readFile("Parties.txt");
			// parse
			setPartyList(FileReader.parseParty(data));
			// allow guest to interact with EviteSystem
			chooseAction();
			// make .txt files containing the updated data about guests and parties
			FileReader.makeUnregisteredGuestsFile(guestsList);
			FileReader.makePartiesFile(partyList);
			// get guest input on whether to continue using the EviteSystem or not
			continueMenu = bff.inputWord("\nWould you like to continue using the Evite System (Type \"y\" or \"n\")?", "y", "n");
		}
		// Display exit message
		System.out.println("\nBye! See you later!");
			
	}
	
	// Method name: main()
	// Parameter: Strings[] args
	// Return Value: None 
	// This function allows the EviteSystem to function.
	public static void main(String[] args)
	{
		EviteSystem program = new EviteSystem();
		program.run();
		
	}

}
