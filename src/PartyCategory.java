
public enum PartyCategory {
	CHRISTMAS("Christmas"),
	THANKSGIVING("Thanksgiving"),
	COSTUME("Costume"),
	FAMILY("Family");
	
	private String partyCategory;
	// private Constructor 
	private PartyCategory(String partyCategory){
		this.partyCategory = partyCategory;
	}
	
	// methods 
	public String getDisplayString(){
		return this.partyCategory;
	}
		
	public static PartyCategory getOption(int num) {
		return PartyCategory.values()[num];
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tParty Category Menu\t*****";

		for(PartyCategory category: PartyCategory.values()){ //array from the enum
			prompt += "\n" + (category.ordinal()) + ": " + category.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		
		return prompt;
	}
	
	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
	
}
