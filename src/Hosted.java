import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Hosted extends Party {
	
	// instance variables 
	private PartyType partyType; 
	private PartyCategory partyCategory; 
	private String cake; 
	private List<String> appetizers;
	private List<String> mainCourses;
	private List<String> desserts;
	private BFF bff;

	// Constructor 
	// initialize instance variables 
	public Hosted(String partyName, int partyDateYear, int partyDateMonth, int partyDateDay,
			 int partyTimeHour, int partyTimeMin, int partyDurationHours,
			String partyLocation, String hostEmail, PartyCategory partyCategory, String cake) {
		super(partyName, partyDateYear, partyDateMonth, partyDateDay, 
				partyTimeHour, partyTimeMin, partyDurationHours, partyLocation, hostEmail);
		this.partyType = PartyType.HOSTED;
		this.partyCategory = partyCategory;
		this.cake = cake;
		appetizers = new ArrayList<>();
		mainCourses = new ArrayList<>();
		desserts = new ArrayList<>();
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
	 * @return the cake
	 */
	public String getCake() {
		return cake;
	}

	/**
	 * @param cake the cake to set
	 */
	public void setCake(String cake) {
		this.cake = cake;
	}
	
	/**
	 * @return the appetizers
	 */
	public List<String> getAppetizers() {
		return appetizers;
	}
	
	/**
	 * @return a String containing all the appetizers
	 */
	public String getAppetizersString() {
		String appetizerString = "";
		
		for (String appName : appetizers)
		{
			appetizerString += appName + ",";
		}
		
		return appetizerString; 
	}

	/**
	 * @param appetizers the appetizers to set
	 */
	public void setAppetizers(List<String> appetizers) {
		this.appetizers = appetizers;
	}
	
	/**
	 * @return the mainCourses
	 */
	public List<String> getMainCourses() {
		return mainCourses;
	}
	
	/**
	 * @return a String containing all the main courses
	 */
	public String getMainCoursesString() {
		String mainCourseString = "";
		
		for (String mainCourseName : mainCourses)
		{
			mainCourseString += mainCourseName + ",";
		}
		
		return mainCourseString; 
	}

	/**
	 * @param mainCourses the mainCourses to set
	 */
	public void setMainCourses(List<String> mainCourses) {
		this.mainCourses = mainCourses;
	}

	/**
	 * @return the desserts
	 */
	public List<String> getDesserts() {
		return desserts;
	}
	
	/**
	 * @return a String containing all the desserts
	 */
	public String getDessertsString() {
		String dessertString = "";
		
		for (String dessertName : desserts)
		{
			dessertString += dessertName + ",";
		}
		
		return dessertString; 
	}
	
	/**
	 * @param desserts the desserts to set
	 */
	public void setDesserts(List<String> desserts) {
		this.desserts = desserts;
	}

	// methods 
	// Method Name: hostedAction()
	// Parameters: guest is a GuestUnregistered
	// Return Value: None 
	// This program allows the guest to interact with the Hosted invitation. 
	public void hostedAction(GuestUnregistered guest)
	{
		String guestEmail = guest.getEmail();
		// variable storing the guest choice of option
		int userChoice = 55; 
		// while loop to allow the guest to 
		// keep interacting with the Menu until 
		// the guest chooses to EXIT. 
		while (userChoice != (HostedMenu.values().length - 1))
		{
			System.out.println();
			// display the options to the guest
			HostedMenu.printMenuOptions();
			// get guest input of menu option 
			userChoice = bff.inputInt(">", 0, HostedMenu.getNumOptions());
			// get the HostedMenu option corresponding to the guest input 
			HostedMenu option = HostedMenu.getOption(userChoice);
			// switch statement to match 
			// the guest-selected Menu option 
			// with the corresponding method or 
			// set of actions. 
			switch(option)
			{
			case VIEW_CAKE: 
				System.out.println("Cake Description: " + this.cake);
				break; 
			case VIEW_APPS:
				System.out.println("List of Appetizers:\n" + this.appetizers);
				break;
			case VIEW_MAINS:
				System.out.println("List of Main Courses:\n" + this.mainCourses);
				break;
			case VIEW_DESSERTS:
				System.out.println("List of Desserts:\n" + this.desserts);
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
	
	// Method Name: exitMessage() 
	// Parameters: None 
	// Return Value: None 
	// This method displays an exit message to the guest. 
	public void exitMessage()
	{
		System.out.println("Exiting the Hosted Party Menu now...");
	}
	
	// Method Name: addAppetizer()
	// Parameters: None 
	// Return Value: None 
	// This method allows for an appetizer to be added. 
	public void addAppetizer()
	{
		String continueAdding;
		boolean added;
		// get input on if the host would like to add an appetizer
		continueAdding = bff.inputWord("Would you like to add an appetizer(Type \"y\" or \"n\")?", "y", "n");
		// while loop to allow the host to continue adding an appetizer 
		while (continueAdding.equalsIgnoreCase("y"))
		{
			// get input on the appetizer name
			String appetizerName = bff.inputWord("Enter the name of the appetizer you would like to add:\n");
			// convert to lowercase
			appetizerName = appetizerName.toLowerCase();
			// branching statement to check if the List
			// contains the item already.
			if (!(appetizers.contains(appetizerName)))
			{
				// if not, add the appetizer to the List
				appetizers.add(appetizerName);
				added = true;
			}
			else 
			{
				added = false;
			}
			
			// branching statement for the message to display 
			if (added)
			{
				System.out.println("Added appetizer!");
			}
			else 
			{
				System.out.println("Failed to add appetizer! Already is in list!");
			}
			// get input on if the host would like to continue adding appetizers
			continueAdding = bff.inputWord("Would you like to add more appetizers (Type \"y\" or \"n\")?", "y", "n");
		}	
		
		System.out.println("Done adding appetizers. Sending updated appetizer list to chef now!");
	
	}
	
	public void removeAppetizer()
	{
		String continueRemoving;
		boolean removed;
		// get input on if the host would like to remove an appetizer
		continueRemoving = bff.inputWord("Would you like to remove an appetizer (Type \"y\" or \"n\")?", "y", "n");
		// while loop to allow the host to continue removing an appetizer 
		while (continueRemoving.equalsIgnoreCase("y"))
		{
			// get input on the appetizer name
			String appetizerName = bff.inputWord("Enter the name of the appetizer you would like to remove:\n");
			// convert to lowercase
			appetizerName = appetizerName.toLowerCase();
			// branching statement to check if the List
			// contains the item already.
			if (appetizers.contains(appetizerName))
			{
				// if so, remove the appetizer from the List
				appetizers.remove(appetizerName);
				removed = true;
			}
			else 
			{
				removed = false;
			}
			
			// branching statement for display message
			if (removed)
			{
				System.out.println("Removed appetizer!");
			}
			else 
			{
				System.out.println("Failed to remove appetizer! Not in list!");
			}
			// get input on if the host would like to continue removing appetizers
			continueRemoving = bff.inputWord("Would you like to remove more appetizers (Type \"y\" or \"n\")?", "y", "n");
		}	
		
		System.out.println("Done removing appetizers. Sending updated appetizer list to chef now!");
	}
	
	public void addMainCourse()
	{
		String continueAdding;
		boolean added;
		// get input on if the host would like to add a main course
		continueAdding = bff.inputWord("Would you like to add a main course item (Type \"y\" or \"n\")?", "y", "n");
		// while loop to allow the host to continue adding a main course
		while (continueAdding.equalsIgnoreCase("y"))
		{
			// get input on the main course name
			String mainCourseItem = bff.inputWord("Enter the name of the main course you would like to add:\n");
			// convert to lowercase
			mainCourseItem = mainCourseItem.toLowerCase();
			// branching statement to check if the List
			// contains the item already.
			if (!(mainCourses.contains(mainCourseItem)))
			{
				// if not, add the main course to the List
				mainCourses.add(mainCourseItem);
				added = true;
			}
			else 
			{
				added = false;
			}
			
			// branching statement for display message
			if (added)
			{
				System.out.println("Added main course!");
			}
			else 
			{
				System.out.println("Failed to add main course! Already is in list!");
			}
			// get input on if the host would like to continue adding main courses
			continueAdding = bff.inputWord("Would you like to add more main courses (Type \"y\" or \"n\")?", "y", "n");
		}	
		
		System.out.println("Done adding main courses. Sending updated main course list to chef now!");
	
	}
	
	public void removeMainCourse()
	{
		String continueRemoving;
		boolean removed;
		// get input on if the host would like to remove a main course
		continueRemoving = bff.inputWord("Would you like to remove a main course (Type \"y\" or \"n\")?", "y", "n");
		// while loop to allow the host to continue removing a main course
		while (continueRemoving.equalsIgnoreCase("y"))
		{
			// get input on the main course name
			String mainCourseItem = bff.inputWord("Enter the name of the main course you would like to remove:\n");
			// convert to lowercase
			mainCourseItem = mainCourseItem.toLowerCase();
			// branching statement to check if the List
			// contains the item already.
			if (mainCourses.contains(mainCourseItem))
			{
				// if so, remove the appetizer from the List
				mainCourses.remove(mainCourseItem);
				removed = true;
			}
			else 
			{
				removed = false;
			}
			
			// branching statement for display message
			if (removed)
			{
				System.out.println("Removed main course!");
			}
			else 
			{
				System.out.println("Failed to remove main course! Not in list!");
			}
			// get input on if the host would like to continue removing main courses
			continueRemoving = bff.inputWord("Would you like to remove more main courses (Type \"y\" or \"n\")?", "y", "n");
		}	
		
		System.out.println("Done removing main courses. Sending updated main course list to chef now!");
	}
	
	public void addDessert()
	{
		String continueAdding;
		boolean added;
		// get input on if the host would like to add a dessert
		continueAdding = bff.inputWord("Would you like to add a dessert item (Type \"y\" or \"n\")?", "y", "n");
		// while loop to allow the host to continue adding a dessert
		while (continueAdding.equalsIgnoreCase("y"))
		{
			// get input on the dessert name
			String dessertItem = bff.inputWord("Enter the name of the dessert you would like to add:\n");
			// convert to lowercase
			dessertItem = dessertItem.toLowerCase();
			// branching statement to check if the List
			// contains the item already.
			if (!(desserts.contains(dessertItem)))
			{
				// if not, add the dessert to the List
				desserts.add(dessertItem);
				added = true;
			}
			else 
			{
				added = false;
			}
			
			// branching statement for display message
			if (added)
			{
				System.out.println("Added dessert!");
			}
			else 
			{
				System.out.println("Failed to add dessert! Already is in list!");
			}
			// get input on if the host would like to continue adding desserts
			continueAdding = bff.inputWord("Would you like to add more desserts (Type \"y\" or \"n\")?", "y", "n");
		}	
		
		System.out.println("Done adding desserts. Sending updated desserts list to chef now!");
	
	}
	
	public void removeDessert()
	{
		String continueRemoving;
		boolean removed;
		// get input on if the host would like to remove a dessert
		continueRemoving = bff.inputWord("Would you like to remove a dessert (Type \"y\" or \"n\")?", "y", "n");
		// while loop to allow the host to continue removing a dessert
		while (continueRemoving.equalsIgnoreCase("y"))
		{
			// get input on the dessert name
			String dessertItem = bff.inputWord("Enter the name of the dessert you would like to remove:\n");
			// convert to lowercase
			dessertItem = dessertItem.toLowerCase();
			// branching statement to check if the List
			// contains the item already.
			if (desserts.contains(dessertItem))
			{
				// if so, remove the dessert from the List
				desserts.remove(dessertItem);
				removed = true;
			}
			else 
			{
				removed = false;
			}
			
			// branching statement for display message
			if (removed)
			{
				System.out.println("Removed dessert!");
			}
			else 
			{
				System.out.println("Failed to remove dessert! Not in list!");
			}
			// get input on if the host would like to continue removing desserts
			continueRemoving = bff.inputWord("Would you like to remove more desserts (Type \"y\" or \"n\")?", "y", "n");
		}	
		
		System.out.println("Done removing desserts. Sending updated dessert list to chef now!");
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
	

}
