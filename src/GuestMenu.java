
public enum GuestMenu {
	VIEW_PARTIES("Viewing Invited Parties"),
	CREATE_PARTY("Host a party"),
	MODIFY_PARTY("Modifying a party"),
	LOGOUT("Exiting guest menu...");
	
	private String description;
	// private Constructor 
	private GuestMenu(String description){
		this.description = description;
	}
	
	// methods 
	public String getDisplayString(){
		return this.description;
	}
		
	public static GuestMenu getOption(int num) {
		return GuestMenu.values()[num];
	}
	
	public static int getNumOptions() {
		return GuestMenu.values().length - 1;
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tGuest Response Menu\t*****";

		for(GuestMenu choice: GuestMenu.values()){ //array from the enum
			prompt += "\n" + (choice.ordinal()) + ": " + choice.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		
		return prompt;
	}
	
	public static String getMenuOptions(int first, int exit) {
		String prompt = "*****\tGuest Response Menu\t*****";
		
		int counter = 0;

		for(GuestMenu choice: GuestMenu.values()){ //array from the enum
			if (first == choice.ordinal() || exit == choice.ordinal())
			{
				prompt += "\n" + (choice.ordinal()) + ": " + choice.getDisplayString();
			}
		}
		prompt+="\n**********************************************\n";
		
		return prompt;
	}
	
	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
	
	public static void printMenuOptions(int first, int exit) {
		String prompt = getMenuOptions(first, exit);
		System.out.println(prompt);
	}

}
