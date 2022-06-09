
public enum GiftExchangeMenu {
	BRING_GIFT("Sign up to bring a gift to the party"),
	VIEW_GIFT("See the gift you are signed up to bring to the party"),
	UPDATE_GIFT("Update the gift you are signed up to bring to the party"),
	VIEW_NUM_CONTRIBUTIONS("View the number of contributions"),
	UPDATE_RSVP("Update the RSVP for the party"),
	VIEW_RSVP("View the RSVP for the party"),
	EXIT("Exiting the Gift Exchange Party Menu...");
	
	private String partyDescription;
	// private Constructor 
	private GiftExchangeMenu(String partyDescription){
		this.partyDescription = partyDescription;
	}
	
	// methods 
	public String getDisplayString(){
		return this.partyDescription;
	}
		
	public static GiftExchangeMenu getOption(int num) {
		return GiftExchangeMenu.values()[num];
	}
	
	public static int getNumOptions() {
		return GiftExchangeMenu.values().length - 1;
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tGift Exchange Party Menu\t*****";

		for(GiftExchangeMenu choice : GiftExchangeMenu.values()){ //array from the enum
			prompt += "\n" + (choice.ordinal()) + ": " + choice.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		
		return prompt;
	}
	
	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
}
