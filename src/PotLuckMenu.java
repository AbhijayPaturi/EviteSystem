
public enum PotLuckMenu {
	BRING_FOOD("Sign up to bring a dish to the party"),
	VIEW_FOOD("See the dish you are signed up to bring to the party"),
	UPDATE_FOOD("Update the dish you are signed up to bring to the party"),
	VIEW_NUM_CONTRIBUTIONS("View the number of contributions"),
	UPDATE_RSVP("Update the RSVP for the party"),
	VIEW_RSVP("View the RSVP for the party"),
	EXIT("Exiting the PotLuck Party Menu...");
	
	private String partyDescription;
	// private Constructor 
	private PotLuckMenu(String partyDescription){
		this.partyDescription = partyDescription;
	}
	
	// methods 
	public String getDisplayString(){
		return this.partyDescription;
	}
		
	public static PotLuckMenu getOption(int num) {
		return PotLuckMenu.values()[num];
	}
	
	public static int getNumOptions() {
		return PotLuckMenu.values().length - 1;
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tPotLuck Party Menu\t*****";

		for(PotLuckMenu choice : PotLuckMenu.values()){ //array from the enum
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
