
public enum ModifyPartyMenu {
	TITLE("Change the name of the party"),
	DATE("Change the date of the party"),
	TIME("Change the time of the party"),
	DURATION("Change the duration of the party"), 
	LOCATION("Change the location of the party"), 
	REMOVE_GUESTS("Remove guests from RSVP to the party"), 
	EXIT("Exiting this menu to modify a Party...");
	
	private String description;
	// private Constructor 
	private ModifyPartyMenu(String description){
		this.description = description;
	}
	
	// methods 
	public String getDisplayString(){
		return this.description;
	}
		
	public static ModifyPartyMenu getOption(int num) {
		return ModifyPartyMenu.values()[num];
	}
	
	public static int getNumOptions() {
		return ModifyPartyMenu.values().length - 1;
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tModify Party Menu\t*****";

		for(ModifyPartyMenu choice: ModifyPartyMenu.values()){ //array from the enum
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
