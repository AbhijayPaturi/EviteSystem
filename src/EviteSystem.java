import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EviteSystem {
	
	private List<GuestUnregistered> guestsList;
	private List<Party> partyList;
	private Map<Host, List<Party>> hostPartyMap;
	private BFF bff;
	
	public EviteSystem() {
		guestsList = new ArrayList<>();
		partyList = new ArrayList<>();
		hostPartyMap = new HashMap<>();
		bff = new BFF();
	}
	
	public List<GuestUnregistered> getGuestsList() {
		return guestsList;
	}

	public void setGuestsList(List<GuestUnregistered> guestsList) {
		this.guestsList = guestsList;
	}

	public List<Party> getPartyList() {
		return partyList;
	}
	
	public void setPartyList(List<Party> partyList) {
		this.partyList = partyList;
	}

	public void chooseAction()
	{
		String guestEmail = getGuestEmail();
		boolean registered = getRegistrationStatus(guestEmail);
		GuestUnregistered guest;
		if (!registered)
		{
			guest = createAccount(guestEmail);
		}
		else 
		{
			guest = logIn(guestEmail);
		}
		if (guest instanceof GuestRegistered)
		{
			Host registeredHostAndGuest = (Host) guest;
			int userChoice = 55; 
			while (userChoice != (GuestMenu.values().length - 1))
			{
				updateCollections(registeredHostAndGuest);
				System.out.println();
				GuestMenu.printMenuOptions();
				userChoice = bff.inputInt(">", 0, GuestMenu.getNumOptions());
				GuestMenu option = GuestMenu.getOption(userChoice);
				switch(option)
				{
				case VIEW_PARTIES: 
					List<Party> guestInvitedPartyList = getInvitedParties(guest);
					showInvitedParties(guest, guestInvitedPartyList);
					if (guestInvitedPartyList.size() > 0)
					{
						int partySelection = bff.inputInt("\nChoose a party invitation to view:", 0, guestInvitedPartyList.size());
						Party selectedParty = guestInvitedPartyList.get(partySelection);
						if (selectedParty instanceof PotLuck)
						{
							PotLuck potluck = (PotLuck) selectedParty;
							potluck.potLuckAction(guest);
						}
						else if (selectedParty instanceof GiftExchange)
						{
							GiftExchange giftParty = (GiftExchange) selectedParty;
							giftParty.giftExchangeAction(guest);
						}
						else if (selectedParty instanceof VirtualParty)
						{
							VirtualParty virtualParty = (VirtualParty) selectedParty;
							virtualParty.virtualAction(guest);
						}
						else if (selectedParty instanceof Hosted)
						{
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
			while (userChoice != (GuestMenu.values().length - 1))
			{
				GuestMenu.printMenuOptions(0, GuestMenu.values().length - 1);
				userChoice = bff.inputInt(">", 0, GuestMenu.getNumOptions()); 
				GuestMenu option = GuestMenu.getOption(userChoice);
				switch(option)
				{
				case VIEW_PARTIES: 
					List<Party> guestInvitedPartyList = getInvitedParties(guest);
					showInvitedParties(guest, guestInvitedPartyList);
					if (guestInvitedPartyList.size() > 0)
					{
						int partySelection = bff.inputInt("\nChoose a party invitation to view:", 0, guestInvitedPartyList.size());
						Party selectedParty = guestInvitedPartyList.get(partySelection);
						if (selectedParty instanceof PotLuck)
						{
							PotLuck potluck = (PotLuck) selectedParty;
							potluck.potLuckAction(guest);
						}
						else if (selectedParty instanceof GiftExchange)
						{
							GiftExchange giftParty = (GiftExchange) selectedParty;
							giftParty.giftExchangeAction(guest);
						}
						else if (selectedParty instanceof VirtualParty)
						{
							VirtualParty virtualParty = (VirtualParty) selectedParty;
							virtualParty.virtualAction(guest);
						}
						else if (selectedParty instanceof Hosted)
						{
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
		exitEviteMessage();	
	}

	private void modifyParty(Host host) {
		host.chooseModifyAction();
	}
	
	private void createParty(Host host) {
		Party p = host.createParty();
		partyList.add(p);
		host.addParty(p);
	}

	public List<Party> getInvitedParties(GuestUnregistered unregisteredGuest)
	{
		List<Party> invitedPartyList; 
		invitedPartyList = new ArrayList<>();
		System.out.println("Showing Invited Parties\n");
		for (int i = 0; i < partyList.size(); i++)
		{
			Party partyHolder = partyList.get(i);
			for (GuestResponse rsvp : GuestResponse.values())
			{
				if (partyHolder.getRsvpToGuestsMap().get(rsvp).contains(unregisteredGuest.getEmail()))
				{
					invitedPartyList.add(partyHolder);
				}
			}
		}
		return invitedPartyList;
	}
	
	public void showInvitedParties(GuestUnregistered guest, List<Party> partyList)
	{
		if (partyList.size() == 0)
		{
			System.out.println("\t" + guest.getEmail() + " have not been invited to any parties yet.\n");
		}
		else 
		{
			for (int i = 0; i < partyList.size(); i++)
			{
				System.out.println(i + ")\n" + partyList.get(i));
			}
		}
	}
	
	private void quitMessage() {
		System.out.println("Exiting the system...");
	}

	private void exitEviteMessage()
	{
		System.out.println("Thanks for using the Evite System!\nHope you have fun at your parties! See you soon!");
	}
	
	private boolean getRegistrationStatus(String email) {
		boolean guestRegistered = false;
		for (GuestUnregistered guest : guestsList)
		{
			if (guest.getEmail().equals(email) && (guest instanceof GuestRegistered))
			{
				guestRegistered = true;
			}
		}
		return guestRegistered;
	}

	public GuestUnregistered createAccount(String email)
	{
		String registrationStatus = bff.inputWord("Would you like to register an account(Type \"y\" or \"n\")?", "y", "n");
		GuestUnregistered newGuest;
		if (registrationStatus.equals("y"))
		{
			String firstName = bff.inputWord("Enter your first name:");
			String lastName = bff.inputWord("Enter your last name:");
			String password = bff.inputWord("Enter your password:");
			newGuest = new Host(email, firstName, lastName, password);
			System.out.println("Wonderful, an account has been created!");
			guestsList.add(newGuest);
		}
		else 
		{
			System.out.println("Without a registered account, you can only view invitations and RSVP.");
			newGuest = new GuestUnregistered(email);
			guestsList.add(newGuest);
		}
		return newGuest;
	}
	
	public String getGuestEmail()
	{
		System.out.println("Welcome to the Evite System!\n"
				+ "The Evite System is designed to allow you to RSVP to or create your own party!\n");
		String email = bff.inputWord("To get started, enter your email:");
		return email;
	}

	public GuestUnregistered logIn(String email)
	{
		System.out.println("Welcome back to the Evite System!");
		String password;
		int listIndex = 0;
		GuestUnregistered returnGuest;
		Host registeredGuest;
		for (int i = 0; i < guestsList.size(); i++)
		{
			if (guestsList.get(i).getEmail().equals(email))
			{
				listIndex = i;
			}
		}
		returnGuest = guestsList.get(listIndex);
		if (returnGuest instanceof GuestRegistered)
		{
			registeredGuest = (Host) returnGuest;
			password = bff.inputWord("Enter your password:");
			while (!(registeredGuest.getPassword().equals(password)))
			{
				password = bff.inputWord("Wrong! Please enter the correct password:");
			}
		}
		System.out.println("You are logged in!");
		return returnGuest;
	}
	
	public boolean checkLogIn(GuestRegistered guest, String email, String password)
	{
		return guest.getEmail().equals(email) && guest.getPassword().equals(password);
	}	

	public void updateCollections(Host host)
	{
		for (Party p : partyList)
		{
			String hostEmail = p.getHostEmail();
			boolean inMap = false;
			List<Party> hostPartyList;
			if (hostPartyMap.size() != 0)
			{
				for (Host partyHost : hostPartyMap.keySet())
				{
					if (partyHost.getEmail().equals(hostEmail))
					{
						inMap = true;
						hostPartyList = hostPartyMap.get(partyHost);
						hostPartyList.add(p);
						hostPartyMap.put(partyHost, hostPartyList);
						partyHost.addParty(p);
					}
				}
			}
			else if (hostPartyMap.size() != 0 || !inMap)
			{
				hostPartyList = new ArrayList<>();
				hostPartyList.add(p);
				hostPartyMap.put(host, hostPartyList);
				host.addParty(p);
			}

			for (GuestResponse rsvp : GuestResponse.values())
			{
				for (String guestEmail : p.getRsvpToGuestsMap().get(rsvp))
				{
					for (GuestUnregistered guest : guestsList)
					{
						if (guestEmail.equals(guest.getEmail()))
						{
							if (guest instanceof GuestRegistered)
							{
								GuestRegistered registeredGuest = (GuestRegistered) guest;
								registeredGuest.addParty(p, registeredGuest.getInvitedParties());
							}
						}
					}
				}
			}	
		}	
	}

	public void run()
	{
		String continueMenu = "y";
		while (!continueMenu.equalsIgnoreCase("n"))
		{
			ArrayList<String> data = FileReader.readFile("Guests.txt");
			setGuestsList(FileReader.parseGuest(data));
			data = FileReader.readFile("Parties.txt");
			setPartyList(FileReader.parseParty(data));
			chooseAction();
			FileReader.makeUnregisteredGuestsFile(guestsList);
			FileReader.makePartiesFile(partyList);
			continueMenu = bff.inputWord("\nWould you like to continue using the Evite System (Type \"y\" or \"n\")?", "y", "n");
		}
		System.out.println("\nBye! See you later!");	
	}

	public static void main(String[] args)
	{
		EviteSystem program = new EviteSystem();
		program.run();
	}
}
