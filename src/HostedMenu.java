public enum HostedMenu {
	VIEW_CAKE("View the description of the cake at the party"), 
	VIEW_APPS("View the appetizers that will be at party"), 
	VIEW_MAINS("View the main courses that will be at party"), 
	VIEW_DESSERTS("View the desserts that will be at party"), 
	UPDATE_RSVP("Update the RSVP for the party"),
	VIEW_RSVP("View the RSVP for the party"),
	EXIT("Exiting the PotLuck Party Menu...");
	
	private String partyDescription;
	// private Constructor 
	private HostedMenu(String partyDescription){
		this.partyDescription = partyDescription;
	}
	
	// methods 
	public String getDisplayString(){
		return this.partyDescription;
	}
		
	public static HostedMenu getOption(int num) {
		return HostedMenu.values()[num];
	}
	
	public static int getNumOptions() {
		return HostedMenu.values().length - 1;
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tHosted Party Menu\t*****";

		for(HostedMenu choice : HostedMenu.values()){ //array from the enum
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
