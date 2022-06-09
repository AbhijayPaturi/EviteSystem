import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileReader {

	// Constructor 
	public FileReader() {
		// TODO Auto-generated constructor stub
	}
	
	// Method Name: readFile()
	// Parameter: fileName is a String 
	// Return Value: ArrayList<String>
	// This program reads in .txt files.
	public static ArrayList<String> readFile(String fileName)
	{
		ArrayList<String> fileLines = new ArrayList<>();
		// try block 
		try (FileInputStream fis = new FileInputStream(fileName))
		{
			// Scanner processes the raw bits of data into nicer bits 
			Scanner scan = new Scanner(fis);
			// while loop to read through lines of data 
			while(scan.hasNextLine()) 
			{ 
				String line = scan.nextLine();
				fileLines.add(line);
			}
			
		} 
		// catch block
		catch (FileNotFoundException e)
		{
			System.out.println("File " + fileName + " not found....");
		}
		// catch block
		catch (IOException e)
		{ 
			System.out.println("Some other error occurred when reading in the Guest file " + fileName);
		}
		return fileLines;
			
	}
		
		
	// GuestRegistered Methods 
	// Method Name: makeUnregisteredGuestsFile()
	// Parameter: guestList is a List<GuestUnregistered>
	// Return Value: None 
	// This method makes a .txt file containing data on guests. 
	public static void makeUnregisteredGuestsFile(List<GuestUnregistered> guestList)
	{
		// try block 
		try (FileOutputStream fos = new FileOutputStream("Guests.txt");
		          PrintWriter pw = new PrintWriter(fos)){
			
			// header 
			pw.println("Guest Registration Status : Guest Email : Guest First Name : Guest Last Name : Guest Password : Guest Invited Parties List");
			// for-each loop to loop through each Person
			for (GuestUnregistered guest : guestList)
			{
				// branching statement to identify if guest is a GuestRegistered
				if (guest instanceof GuestRegistered)
				{
					// type cast
					GuestRegistered registeredGuest = (GuestRegistered) guest;
					// Write the information of each GuestRegistered into the file 
					pw.println("Registered" + " : " + registeredGuest.getEmail() + " : " + registeredGuest.getFirstName() + " : " + registeredGuest.getLastName()
					+ " : " + registeredGuest.getPassword() + " : " + registeredGuest.showParties());
			
				}
				else 
				{
					pw.println("Unregistered" + " : " + guest.getEmail());
				}
		
			} 
		}
		// catch block 
		catch (IOException e) {
			// display message to user 
			System.out.println("In makeUnregisteredGuestsFile, not found " + e);
		}
	}
	
	// Method Name: parseGuest()
	// Parameter: data is a ArrayList<String>
	// Return Value: List<GuestUnregistered>
	// This program parses the read in data to form a guest object.
	public static List<GuestUnregistered> parseGuest(ArrayList<String> data)
	{
		List<GuestUnregistered> guestList; 
		guestList = new ArrayList<>();
		// loop through each line and turn data into a book
		String header = data.get(0);
		
		// for loop to loop through the read in data 
		for(int i = 1; i < data.size(); i++) {
			
			// branching statement here to see if it starts with "Registered" or "Unregistered"
			String line = data.get(i);
			Scanner ls = new Scanner(line); // work on one line from file
			GuestUnregistered guest;
			// delimiter 
			ls.useDelimiter(" : ");
			
			String guestStatus = ls.next();
			// branching statement to identify if the guest is registered or not 
			if (guestStatus.equalsIgnoreCase("Registered"))
			{
				String email = ls.next();
				String firstName = ls.next();
				String lastName = ls.next();
				String password = ls.next();
				guest = new Host(email, firstName, lastName, password);
			}
			else 
			{
				String email = ls.next();
				guest = new GuestUnregistered(email);
				
			}
			
			guestList.add(guest);
		}
		
		return guestList;
	}

	// Party methods 
	// Method Name: makePartiesFile() 
	// Parameter: partyList is a List<Party>
	// Return Value: None 
	// This program makes a .txt file containing data on each Party.
	public static void makePartiesFile(List<Party> partyList)
	{
		// try block 
		try (FileOutputStream fos = new FileOutputStream("Parties.txt");
		          PrintWriter pw = new PrintWriter(fos)){
			
			//pw.println("Party Type : Party Category : Party Name : Party Year : Party Month
			//Party Day : Party Hour : Party Min : Party Duration (in Hours) : 
			//PartyLocation : Party RSVP to Invited Guest Emails Map : Other Attributes Particular to each Party Type");
			
			// for-each loop to loop through each Party
			for (Party p : partyList)
			{
				// branching statements to identify which type of Party each Party is 
				if (p instanceof PotLuck)
				{
					// type cast
					PotLuck potluck = (PotLuck) p;
					pw.println(potluck.getPartyType() + " : " + potluck.getPartyCategory() + " : " + potluck.getPartyName() 
					+ " : " + potluck.getPartyDateYear() + " : " + potluck.getPartyDateMonth() + " : " 
					+ potluck.getPartyDateDay() + " : " + potluck.getPartyTimeHour() + " : "
					+ potluck.getPartyTimeMin() + " : " + potluck.getPartyDurationHours() + " : " 
					+ potluck.getPartyLocation() + " : " + potluck.getHostEmail() + 
					" : " + potluck.getRsvpToGuestsMapString() 
					+ " : " + potluck.getFoodSignUpString());
				}
				
				else if (p instanceof GiftExchange)
				{
					// type cast
					GiftExchange gf = (GiftExchange) p;
					pw.println(gf.getPartyType() + " : " + gf.getPartyCategory() + " : " + gf.getPartyName() 
					+ " : " + gf.getPartyDateYear() + " : " + gf.getPartyDateMonth() + " : " 
					+ gf.getPartyDateDay() + " : " + gf.getPartyTimeHour() + " : "
					+ gf.getPartyTimeMin() + " : " + gf.getPartyDurationHours() + " : " 
					+ gf.getPartyLocation() + " : " + gf.getHostEmail() + " : " 
					+ gf.getRsvpToGuestsMapString() + " : " + gf.getGiftSignUpString());
				}
				
				else if (p instanceof VirtualParty)
				{
					// type cast
					VirtualParty vp = (VirtualParty) p;
					pw.println(vp.getPartyType() + " : " + vp.getPartyCategory() + " : " + vp.getPartyName() 
					+ " : " + vp.getPartyDateYear() + " : " + vp.getPartyDateMonth() + " : " 
					+ vp.getPartyDateDay() + " : " + vp.getPartyTimeHour() + " : "
					+ vp.getPartyTimeMin() + " : " + vp.getPartyDurationHours() + " : " 
					+ vp.getPartyLocation() + " : " + vp.getHostEmail() + " : " 
					+ vp.getRsvpToGuestsMapString() + " : "
					+ vp.getZoomRoomName() + " : " + vp.getZoomRoomCode() + " : " + vp.getZoomRoomPassword());
				}
				
				else if (p instanceof Hosted)
				{
					// type cast
					Hosted hosted = (Hosted) p;
					pw.println(hosted.getPartyType() + " : " + hosted.getPartyCategory() + " : " + hosted.getPartyName() 
					+ " : " + hosted.getPartyDateYear() + " : " + hosted.getPartyDateMonth() + " : " 
					+ hosted.getPartyDateDay() + " : " + hosted.getPartyTimeHour() + " : "
					+ hosted.getPartyTimeMin() + " : " + hosted.getPartyDurationHours() + " : " 
					+ hosted.getPartyLocation() + " : " + hosted.getHostEmail() + " : "  
					+ hosted.getRsvpToGuestsMapString() + " : " 
					+ hosted.getCake() + " : " + hosted.getAppetizersString() + " : " + hosted.getMainCoursesString()
					+ " : " + hosted.getDessertsString());
				}
				
				else 
				{
					pw.println(p.getPartyName() + " : " + p.getPartyDate() + " : " 
							+ p.getPartyTime() + " : " + p.getPartyDurationHours() + " : " 
							+ p.getPartyLocation() + p.getHostEmail() + " : " 
							+ p.getRsvpToGuestsMapString());
				}
				
			}
		} 
		
		// catch block 
		catch (IOException e) {
			// display message to user 
			System.out.println("In makePartiesFile, not found " + e);
		}
	}
	
	// Method Name: parseParty()
	// Parameter: data is a ArrayList<String>
	// Return Value: List<Party>
	// This program parses the read in data to form objects of type of the subclasses of Party.
	public static List<Party> parseParty(ArrayList<String> data)
	{
		List<Party> partyList; 
		partyList = new ArrayList<>();
		// loop through each line and turn data into a Party
		for(int i = 0; i < data.size(); i++) {
			String line = data.get(i);
			Scanner ls = new Scanner(line); // work on one line from file
			// delimiter
			ls.useDelimiter(" : ");
			
			// instance variables for each Party from file 
			String partyCategory = ls.next();
			PartyCategory partyCat;
			// set to default value (the one I'm assuming most people like the most)
			partyCat = PartyCategory.CHRISTMAS;
			// for loop to loop through PartyCategory enum 
			for (PartyCategory category : PartyCategory.values())
			{
				// branching statement to match the category name
				if (partyCategory.equals(category.toString()))
				{
					partyCat = category;
				}
			}
			
			// information generic to every Party
			String partyName = ls.next();
			ls.next();
			int partyYear = ls.nextInt();
			int partyMonth = ls.nextInt();
			int partyDay = ls.nextInt();
			//LocalDate partyDate = LocalDate.of(partyYear, partyMonth, partyDay);
			int timeHour = ls.nextInt();
			int timeMin = ls.nextInt();
			//LocalTime partyTime = LocalTime.of(timeHour, timeMin);
			int partyDurationHours = ls.nextInt();
			String partyLocation = ls.next();
			String hostEmail = ls.next();
			// guestList for each Party
			String strGuests = ls.next();
			Map<GuestResponse, List<String>> guestRsvpMap;
			guestRsvpMap = new HashMap<>();
			for (GuestResponse rsvpOption : GuestResponse.values())
			{
				List emptyList = new ArrayList<>();
				guestRsvpMap.put(rsvpOption, emptyList);
			}
			
			String[] strGuestList;
			// split the String of data
			strGuestList = strGuests.split(",");
			int index = 0;
			// while loop to loop through strGuestList
			while (index < strGuestList.length - 1)
			{
				List<String> guestEmailList;
				String guestRsvp = strGuestList[index];
				// default value 
				GuestResponse rsvp = GuestResponse.NO_RESPONSE;
				// for loop to loop through the list of RSVP choices 
				for (GuestResponse rsvpOption : GuestResponse.values())
				{
					// branching statement to identify if RSVP options match
					if (rsvpOption.toString().equals(guestRsvp))
					{
						rsvp = rsvpOption;
					}
					// initialize List
					guestEmailList = new ArrayList<>();
					// put key and corresponding value into the map
					guestRsvpMap.put(rsvpOption, guestEmailList);
				}
				
				// store the guest's email
				String guestEmail = strGuestList[index + 1];
				
				// branching statement to identify if the Map contains the email already
				if (guestRsvpMap.containsKey(rsvp))
				{
					// get the List of guest emails
					guestEmailList = guestRsvpMap.get(rsvp);
					// add to the List of guest email
					guestEmailList.add(guestEmail);
				}
				else 
				{
					// create a new, empty ArrayList
					guestEmailList = new ArrayList<>();
					// add to the new List
					guestEmailList.add(guestEmail);
				}
				// put key (RSVP choice) and corresponding value into the map
				guestRsvpMap.put(rsvp, guestEmailList);
				// increment the index 
				index += 2;
			}
		
			// branching statements to identify what type of Party's data is being parsed
			if (data.get(i).startsWith("POTLUCK"))
			{	
				Map<String, String> foodSignUps;
				foodSignUps = new HashMap<>();
				// branching statement to identify if there is more data
				if (ls.hasNext())
				{
					String strFood = ls.next();
					String[] strList;
					// split the String of data
					strList = strFood.split(",");
					// index counter variable
					int indexCounter = 0;
					// while loop to loop through strList
					while (indexCounter < strList.length - 1)
					{
						String guestEmail = strList[indexCounter];
				
						String guestFood = strList[indexCounter + 1];
						// put the key and its corresponding value into the Map
						foodSignUps.put(guestEmail, guestFood);
						// increment the index counter
						indexCounter += 2;
					}	
				}
				
				// create PotLuck party using the parsed data 
				Party p = new PotLuck(partyName, partyYear, partyMonth, partyDay, 
						timeHour, timeMin, partyDurationHours, partyLocation, hostEmail, partyCat);
				((PotLuck) p).setFoodSignUp(foodSignUps);
				// set the Map correlating RSVP choices with a list of guest emails that RSVP'ed to the Party with that response 
				p.setRsvpToGuestsMap(guestRsvpMap);
				// add to list of parties
				partyList.add(p);
			}

			
			
			else if (data.get(i).startsWith("GIFTEXCHANGE"))
			{
				Map<String, String> giftSignUps;
				giftSignUps = new HashMap<>();
				
				// branching statement to identify if there is more data
				if (ls.hasNext())
				{
					String strGifts = ls.next();
					String[] strList;
					// split the String of data
					strList = strGifts.split(",");
					int indexCounter = 0;
					// while loop to loop through strList
					while (indexCounter < strList.length - 1)
					{
						String guestEmail = strList[indexCounter];
						String guestGift = strList[indexCounter + 1];
						// put the key and its corresponding value into the Map
						giftSignUps.put(guestEmail, guestGift);
						// increment the index counter
						indexCounter += 2;
					}
				}
				
				// create GiftExchange party using the parsed data 
				Party p = new GiftExchange(partyName, partyYear, partyMonth, partyDay, 
						timeHour, timeMin, partyDurationHours, partyLocation,  hostEmail, partyCat);
				((GiftExchange) p).setGifts(giftSignUps);
				// set the Map correlating RSVP choices with a list of guest emails that RSVP'ed to the Party with that response 
				p.setRsvpToGuestsMap(guestRsvpMap);
				// add to list of parties
				partyList.add(p);
			}
			
			else if (data.get(i).startsWith("VIRTUAL"))
			{
				String zoomName = "Unnamed";
				int zoomCode = 0;
				String zoomPassword = "None"; 
				
				// branching statement to identify if there is more data
				if (ls.hasNext())
				{
					zoomName = ls.next();
				} 
				
				// branching statement to identify if there is more data
				if (ls.hasNextInt())
				{
					zoomCode = ls.nextInt();
				} 
				
				// branching statement to identify if there is more data
				if (ls.hasNext())
				{
					zoomPassword = ls.next();
				}
		
				// create VirtualParty party using the parsed data 
				Party p = new VirtualParty(partyName, partyYear, partyMonth, partyDay, 
						timeHour, timeMin, partyDurationHours, partyLocation, hostEmail, partyCat, 
						zoomName, zoomCode, zoomPassword);
				p.setHostEmail(hostEmail);
				// set the Map correlating RSVP choices with a list of guest emails that RSVP'ed to the Party with that response 
				p.setRsvpToGuestsMap(guestRsvpMap);
				// add to list of parties
				partyList.add(p);
			}
			
			else if (data.get(i).startsWith("HOSTED"))
			{
				// cake String
				String cake = "None";
				
				// appetizers list
				String appetizers = ls.next();
				List<String> appetizersList;
				appetizersList = new ArrayList<>();
				
				// main courses list 
				String mainCourses = ls.next();
				List<String> mainCoursesList;
				mainCoursesList = new ArrayList<>();
				
				// desserts list 
				String desserts = ls.next();
				List<String> dessertsList;
				dessertsList = new ArrayList<>();
				
				// branching statement to identify if there is more data
				if (ls.hasNext())
				{
					cake = ls.next();
				}
				
				// branching statement to identify if there is more data
				if (ls.hasNext())
				{
					String[] strAppetizersList;
					// split the String of data
					strAppetizersList = appetizers.split(",");
					// for loop to loop through the String of appetizer names
					for (String appName : strAppetizersList)
					{
						appetizersList.add(appName);
					}
				}
			
				// branching statement to identify if there is more data
				if (ls.hasNext())
				{
					String[] strMainCoursesList;
					// split the String of data
					strMainCoursesList = mainCourses.split(",");
					// for loop to loop through the String of main course names
					for (String mainCourseName : strMainCoursesList)
					{
						mainCoursesList.add(mainCourseName);
					}
				}
				
				// branching statement to identify if there is more data
				if (ls.hasNext())
				{
			
					String[] strDessertsList;
					// split the String of data
					strDessertsList = desserts.split(",");
					// for loop to loop through the String of dessert names
					for (String dessertName : strDessertsList)
					{
						dessertsList.add(dessertName);
					}
				}
				
				// create Hosted party using the parsed data 
				Party p = new Hosted(partyName, partyYear, partyMonth, partyDay, 
						timeHour, timeMin, partyDurationHours, partyLocation, hostEmail, partyCat, 
						cake);
				p.setHostEmail(hostEmail);
				((Hosted) p).setAppetizers(appetizersList);
				((Hosted) p).setMainCourses(mainCoursesList);
				((Hosted) p).setDesserts(dessertsList);
				// set the Map correlating RSVP choices with a list of guest emails that RSVP'ed to the Party with that response 
				p.setRsvpToGuestsMap(guestRsvpMap);
				// add to list of parties
				partyList.add(p);
			}
			
		}
		
		// return the list of parties 
		return partyList;
	}
	
	

}
