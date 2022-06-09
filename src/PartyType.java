
public enum PartyType {
	
	POTLUCK("Potluck Party"),
	GIFTEXCHANGE("Gift Exchange Party"),
	VIRTUAL("Virtual Party"),
	HOSTED("Hosted Party");
	
	private String partyDescription;
	// private Constructor 
	private PartyType(String partyDescription){
		this.partyDescription = partyDescription;
	}
	
	// methods 
	public String getDisplayString(){
		return this.partyDescription;
	}
		
	public static PartyType getOption(int num) {
		return PartyType.values()[num];
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tParty Type Menu\t*****";

		for(PartyType type : PartyType.values()){ //array from the enum
			prompt += "\n" + (type.ordinal()) + ": " + type.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		
		return prompt;
	}
	
	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}

}
