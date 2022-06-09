import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class PotLuck extends Party implements Contributions {
	
	// instance variables 
	private PartyType partyType; 
	private PartyCategory partyCategory; 
	private Map<String, String> foodSignUp;
	private BFF bff;

	// Constructor 
	// initialize instance variables 
	public PotLuck(String partyName, int partyDateYear, int partyDateMonth, int partyDateDay,
			 int partyTimeHour, int partyTimeMin, int partyDurationHours,
			String partyLocation, String hostEmail, PartyCategory partyCategory) {
		super(partyName, partyDateYear, partyDateMonth, partyDateDay, 
				partyTimeHour, partyTimeMin, partyDurationHours, partyLocation, hostEmail);
		this.partyType = PartyType.POTLUCK;
		this.partyCategory = partyCategory; 
		foodSignUp = new HashMap<>();
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
	 * @return the foodSignUp
	 */
	public Map<String, String> getFoodSignUp() {
		return foodSignUp;
	}
	
	/**
	 * @param foodSignUp the foodSignUp to set
	 */
	public void setFoodSignUp(Map<String, String> foodSignUp) {
		this.foodSignUp = foodSignUp;
	}

	/**
	 * @return String containing the guest and the food the guest is bringing 
	 */
	public String getFoodSignUpString()
	{
		String foodSignUps = "";
		for (String guestEmail : foodSignUp.keySet())
		{
			foodSignUps += guestEmail + "," + foodSignUp.get(guestEmail) + ",";
		}
		
		return foodSignUps;
	}
	
	// methods 
	// Method Name: potLuckAction()
	// Parameters: guest is a GuestUnregistered
	// Return Value: None 
	// This program allows the guest to interact with the PotLuck invitation.
	public void potLuckAction(GuestUnregistered guest)
	{
		String guestEmail = guest.getEmail();
		// variable storing the guest choice of option
		int userChoice = 55; 
		// while loop to allow the guest to 
		// keep interacting with the Menu until 
		// the guest chooses to EXIT. 
		while (userChoice != (PotLuckMenu.values().length - 1))
		{
			System.out.println();
			// display the options to the guest
			PotLuckMenu.printMenuOptions();
			// get guest input of menu option 
			userChoice = bff.inputInt(">", 0, PotLuckMenu.getNumOptions());
			// get the PotLuckMenu option corresponding to the guest input 
			PotLuckMenu option = PotLuckMenu.getOption(userChoice);
			// switch statement to match 
			// the guest-selected Menu option 
			// with the corresponding method or 
			// set of actions. 
			switch(option)
			{
			case BRING_FOOD: 
				setFoodContribution(guestEmail);
				break; 
			case VIEW_FOOD:
				getGuestFoodContribution(guestEmail);
				break;
			case UPDATE_FOOD:
				updateGuestFoodContribution(guestEmail);
				break;
			case VIEW_NUM_CONTRIBUTIONS:
				System.out.println("Number of food contributions: " + getNumContributions());
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

	// Method Name: setFoodContribution()
	// Parameters: email is a String
	// Return Value: boolean representing whether the guest can bring the food 
	// This program determines whether a guest can bring a certain food item. 
	public void setFoodContribution(String email)
	{
		// get guest input on the food
		String foodItem = bff.inputLine("What food item would you like to bring?");
		
		// branching statement to identify if the guest is already bringing a food item
		if ((!(foodSignUp.containsKey(email))))
		{
			// if not, put the guest email and the corresponding food item into the Map
			foodSignUp.put(email, foodItem);
			System.out.println(email + " is signed up to bring " + foodItem);
		}
		else 
		{
			// display message
			System.out.println("Cannot add " + foodItem + " for guest " + email 
					+ " because he/she/they is already bringing it!");
		}
	
	}
	
	// Method Name: updateGuestFoodContribution()
	// Parameters: email is a String
	// Return Value: boolean representing whether the guest can update the food item
	// This program determines whether a guest can update the food item the guest is bringing. 
	public boolean updateGuestFoodContribution(String email)
	{
		// get guest input on the new food
		String newFoodItem = bff.inputLine("What updated food item would you like to bring?");
		boolean updated = false;
		// branching statement to identify if the guest is already bringing a food item
		if (foodSignUp.containsKey(email))
		{
			// if not, put the guest email and the corresponding new food item into the Map to replace the old food item
			foodSignUp.put(email, newFoodItem);
			updated = true;
			// display message
			System.out.println(email + " is now bringing " + newFoodItem + "!");
		}
		else 
		{
			// display message
			System.out.println(email + ", sorry but could not update the food item as you are not signed up to bring it." 
					+ "\nPlease press the menu option sign up to bring the food item!");
		}
		return updated;
	}
	
	// Method Name: getGuestFoodContribution()
	// Parameters: email is a String
	// Return Value: String representing the food item the guest is currently signed up to bring
	// This program displays which food item the guest is currently signed up to bring. 
	public String getGuestFoodContribution(String email)
	{
		String foodItem;
		// branching statement to identify if the guest is bringing a food item
		if (foodSignUp.containsKey(email))
		{
			foodItem = " is signed up to bring " + foodSignUp.get(email);
		}
		else 
		{
			System.out.println(email + " has not signed up to bring food yet.");
			foodItem = " is not bringing a dish";
		}
		// display message to guest
		System.out.println(email + foodItem + "!");
		return foodItem;
	}
	
	// Method Name: exitMessage() 
	// Parameters: None 
	// Return Value: None 
	// This method displays an exit message to the guest. 
	public void exitMessage()
	{
		System.out.println("Exiting the PotLuck Party Menu now...");
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
	// Return Value: int representing the size of the foodSignUp Map
	// This method is implemented as PotLuck implements the Contributions interface. 
	// This method returns the number of food contributions/sign-ups to the PotLuck Party. 
	@Override 
	public int getNumContributions()
	{
		return foodSignUp.size();
	}
	

}
