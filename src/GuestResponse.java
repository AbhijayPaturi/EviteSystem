
public enum GuestResponse {
	
	YES("Attending"), 
	MAYBE("Maybe Attending"), 
	NO("Not Attending"), 
	NO_RESPONSE("No Response");

	
	private String attendence;
	// private Constructor 
	private GuestResponse(String attendence){
		this.attendence = attendence;
	}
	
	// methods 
	public String getDisplayString(){
		return this.attendence;
	}
	
	public static int getNumOptions() {
		return GuestResponse.values().length - 1;
	}
	
	public static GuestResponse getOption(int num) {
		return GuestResponse.values()[num];
	}
	
	public static String getMenuOptions() {
		String prompt = "*****\tGuest Response Menu\t*****";

		for(GuestResponse response : GuestResponse.values()){ //array from the enum
			prompt += "\n" + (response.ordinal()) + ": " + response.getDisplayString();
		}
		prompt+="\n**********************************************\n";
		
		return prompt;
	}
	
	public static void printMenuOptions() {
		String prompt = getMenuOptions();
		System.out.println(prompt);
	}
}
