public enum VirtualPartyMenu {
	VIEW_NAME("View the name of the Zoom room"), 
	VIEW_CODE("View the code of the Zoom room"), 
	VIEW_PASSWORD("View the password of the Zoom room"),
	UPDATE_RSVP("Update the RSVP for the party"),
	VIEW_RSVP("View the RSVP for the party"),
	EXIT("Exiting the PotLuck Party Menu...");
	
	private String partyDescription;
	
	private VirtualPartyMenu(String partyDescription){
		this.partyDescription = partyDescription;
	}

	public String getDisplayString(){
		return this.partyDescription;
	}
		
	public static VirtualPartyMenu getOption(int num) {
		return VirtualPartyMenu.values()[num];
	}
	
	public static int getNumOptions() {
		return VirtualPartyMenu.values().length - 1;
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tVirtual Party Menu\t*****";
		for(VirtualPartyMenu choice : VirtualPartyMenu.values()){ //array from the enum
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
