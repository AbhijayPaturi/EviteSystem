import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class GiftExchange extends Party implements Contributions{
	
	// instance variables
	private PartyType partyType; 
	private PartyCategory partyCategory; 
	private Map<String, String> gifts;
	private BFF bff;

	// Constructor 
	// initialize instance variables 
	public GiftExchange(String partyName, int partyDateYear, int partyDateMonth, int partyDateDay,
			 int partyTimeHour, int partyTimeMin, int partyDurationHours,
			String partyLocation, String hostEmail, PartyCategory partyCategory) {
		super(partyName, partyDateYear, partyDateMonth, partyDateDay, 
				partyTimeHour, partyTimeMin, partyDurationHours, partyLocation, hostEmail);
		this.partyType = PartyType.GIFTEXCHANGE;
		this.partyCategory = partyCategory;
		gifts = new HashMap<>();
		bff = new BFF();
	}

	//getters and setters for instance variables 
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
	 * @return the gifts
	 */
	public Map<String, String> getGifts() {
		return gifts;
	}

	/**
	 * @return String containing the guest and the food the guest is bringing 
	 */
	public String getGiftSignUpString()
	{
		String giftsSignUps = "";
		for (String guestEmail : gifts.keySet())
		{
			giftsSignUps += guestEmail + "," + gifts.get(guestEmail) + ",";
		}
		
		return giftsSignUps;
	}

	/**
	 * @param gifts the gifts to set
	 */
	public void setGifts(Map<String, String> gifts) {
		this.gifts = gifts;
	}



	// methods 
	// Method Name: giftExchangeAction()
	// Parameters: guest is a GuestUnregistered
	// Return Value: None 
	// This program allows the guest to interact with the GiftExchange invitation.
	public void giftExchangeAction(GuestUnregistered guest)
	{
		String guestEmail = guest.getEmail();
		// variable storing the guest choice of option
		int userChoice = 55; 
		// while loop to allow the guest to 
		// keep interacting with the Menu until 
		// the guest chooses to EXIT. 
		while (userChoice != (GiftExchangeMenu.values().length - 1))
		{
			System.out.println();
			// display the options to the guest
			GiftExchangeMenu.printMenuOptions();
			// get guest input of menu option 
			userChoice = bff.inputInt(">", 0, GiftExchangeMenu.getNumOptions());
			// get the GiftExchangeMenu option corresponding to the guest input 
			GiftExchangeMenu option = GiftExchangeMenu.getOption(userChoice);
			// switch statement to match 
			// the guest-selected Menu option 
			// with the corresponding method or 
			// set of actions. 
			switch(option)
			{
			case BRING_GIFT: 
				setGiftContribution(guestEmail);
				break; 
			case VIEW_GIFT:
				getGuestGiftContribution(guestEmail);
				break;
			case UPDATE_GIFT:
				updateGuestGiftContribution(guestEmail);
				break;
			case VIEW_NUM_CONTRIBUTIONS:
				System.out.println("Number of gift contributions: " + getNumContributions());
				break;
			case UPDATE_RSVP:
				super.updateRSVP(guest);
				break;
			case VIEW_RSVP:
				super.showRSVP(guest);	
				break;
			case EXIT: 
				exitMessage();
				break;
			default:
				System.out.println(option + " not implemented yet");
			}
		}
	}
	
	// Method Name: setGiftContribution()
	// Parameters: email is a String
	// Return Value: boolean representing whether the guest can bring the gift 
	// This program determines whether a guest can bring a certain gift. 
	public boolean setGiftContribution(String email)
	{
		// get guest input on the gift
		String giftItem = bff.inputLine("What gift item would you like to bring?");
		boolean setItem = false;
		
		// branching statement to identify if the guest is already bringing a gift
		if (!(gifts.containsKey(email)))
		{
			// if not, put the guest email and the corresponding gift item into the Map
			gifts.put(email, giftItem);
			System.out.println(email + " is signed up to bring " + giftItem);
			setItem = true;
		}
		else 
		{
			// display message
			System.out.println("Cannot add " + giftItem + " for guest " + email 
					+ " because he/she/they is already bringing it!");
		}
		
		return setItem;
	}
	
	// Method Name: updateGuestGiftContribution()
	// Parameters: email is a String
	// Return Value: boolean representing whether the guest can update the gift item
	// This program determines whether a guest can update the gift item the guest is bringing. 
	public boolean updateGuestGiftContribution(String email)
	{
		// get guest input on the new gift
		String newGiftItem = bff.inputLine("What updated gift item would you like to bring?");
		boolean updated = false;
		// branching statement to identify if the guest is already bringing a gift
		if (gifts.containsKey(email))
		{
			// if not, put the guest email and the corresponding new gift item into the Map to replace the old gift item
			gifts.put(email, newGiftItem);
			updated = true;
			// display message
			System.out.println(email + " is now bringing " + newGiftItem + "!");
		}
		else 
		{
			// display message
			System.out.println(email + ", sorry but could not update the gift item as you are not signed up to bring it." 
					+ "\nPlease press the menu option sign up to bring the gift item!");
		}
		return updated;
	}
	
	// Method Name: getGuestGiftContribution()
	// Parameters: email is a String
	// Return Value: String representing the gift item the guest is currently signed up to bring
	// This program displays which gift item the guest is currently signed up to bring. 
	public String getGuestGiftContribution(String email)
	{
		String giftItem; 
		// branching statement to identify if the guest is bringing a gift
		if (gifts.containsKey(email))
		{
			giftItem = " is signed up to bring " + gifts.get(email);
		}
		else 
		{
			System.out.println(email + " has not signed up to bring a gift yet.");
			giftItem = " is not bringing a gift";
		}
		
		// display message to guest
		System.out.println(email + giftItem + "!");
		return giftItem;
	}
	
	// Method Name: exitMessage() 
	// Parameters: None 
	// Return Value: None 
	// This method displays an exit message to the guest. 
	public void exitMessage()
	{
		System.out.println("Exiting the GiftExchange Party Menu now...");
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
				+ " for " + getPartyDurationHours() + " hours!!!";  
		return msg; 
	}
	
	// Method Name: getNumContributions()
	// Parameters: None
	// Return Value: int representing the size of the gifts Map
	// This method is implemented as GiftExchange implements the Contributions interface. 
	// This method returns the number of gift contributions/sign-ups to the GiftExchange Party. 
	@Override 
	public int getNumContributions()
	{
		return gifts.size();
	}
}
