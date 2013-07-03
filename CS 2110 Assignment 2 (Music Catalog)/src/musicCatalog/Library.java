package musicCatalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.*;


public class Library {
	
	//Declare Variables To Hold Information About the Library 
	private static int numberOfSongs = 0;
	private static int numberOfGenres = 0;
	private String libraryName;
	HashMap<String, String> songs;
	ArrayList<String> spellDict;
	HashMap<Integer, Object> songObj;
	
	//Accessory Methods
	static int getNumberOfSongs() { //For numberOfSongs
		return numberOfSongs;
	}
	public static int getNumberOfGenres() {
		return numberOfGenres;
	}
	public String getLibraryNames() {
		return libraryName;
	}
	
	//Mutator Methods
	static void setNumberOfSongs(int number) {
		numberOfSongs = number;
	}
	public static void setNumberOfGenres(int number) {
		numberOfGenres = number;
	}
	public void setLibraryName(String name) {
		libraryName = name;
	}
	
	//For working on existing catalog
	public void importLibrary(String filepath) {
		
	}
	
	//For saving out catalog
	public void exportLibrary() {
		
	}
	
	//For asking what the user wants to do like a main menu
	public void promptMain() {
		
	}
	
	//To add a song to the catalog
	public void addSong() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("<--addSong()-->");
		
		//Ask for and Artist as a reference and the Name of the song
		Catalog.out.println("What is the name of the Song?");
		String songName = cap(Catalog.in.readLine());
		
		//Check to see if a song by that name already exists and if it does ask to edit, and if not create new object
		checkIfContained(songName);
		
		Catalog.out.println("What is the name of the Artist or Composer?");
		String songArtist = cap(Catalog.in.readLine());
		
		//Try to pull up information on what type of genre they may be
		tryHarvest(songArtist,songName);
		
		//Both checks flow to newSong() to make a new object.
	}//Close addSong method
	
	//Make new song object with title, artist and genre telling what class to use
	public void newSong(String songClass,String title, String artist) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		System.out.println("<--Library.newSong(2args+genre)-->");
		Class cl = Class.forName("musicCatalog."+songClass);
		System.out.println(cl);//For Debugging
		Constructor con = cl.getConstructor(String.class,String.class);
		Object newSong = con.newInstance(title, artist);
		
		//Fill in data for new song
		askInfo(songClass,newSong);
	}//Close newSong Method
	
	//Make new song object with title, artist
	public void newSong(String title, String artist) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		System.out.println("<--Library.newSong(2args)-->");
		Catalog.out.println("What is the genre? Orchestral.");
		String songClass=cap(Catalog.in.readLine());
		Class cl = Class.forName("musicCatalog."+songClass);
		System.out.println(cl);//For Debugging
		Constructor con = cl.getConstructor(String.class,String.class);
		Object newSong = con.newInstance(title, artist);
		
		//Fill in data for new song
		askInfo(songClass,newSong);
	}//Close newSong Method
	
	//Make new song object with only title
		public void newSong(String title) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
			System.out.println("<--Library.newSong(1args)-->");
			Catalog.out.println("What is the genre? Orchestral.");
			String songClass=cap(Catalog.in.readLine());
			Class cl = Class.forName("musicCatalog."+songClass);
			Constructor con = cl.getConstructor(String.class,String.class);
			Object newSong = con.newInstance(title);
			
			//Fill in data for new song
			askInfo(songClass,newSong);
		}//Close newSong Method
	
	//To remove a song/songs or really any genre or artist from the catalog
	public void deleteSong() throws IOException {
		System.out.println("<--deleteSong()-->");
		
		//Ask for keyword to use to delete songs
		Catalog.out.println("What keyword do you want to use to delete data?"
				+ "\nNote Keywords must be exact to work. For example if you"
				+ " wanted to remove all songs of the genre orchestral you must"
				+ " enter \"Orchestral\".");
		String search = cap(Catalog.in.readLine());
		if (songs.containsKey(search)){
			for(int i=0; i<(parseSongIds(songs.get(search))).length;i++) {
			songObj.remove((parseSongIds(songs.get(search)))[i]);
			}//close for
			songs.remove(search);
		}//close if
		else {
			Catalog.out.println("Nothing resembling "+search+" was found in your library.");
		}
	}
	
	public void editSong(Object song) {
		
	}
	
	//To check the information entered by the user
	public void spellChecker() {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	//To check if song title is in database
	public void checkIfContained(String songName) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("<--checkIfContained-->");
		if (songs.containsKey(songName)){
			Catalog.out.println("Hmm... I think you already added this song. Is this it?");
			Object songObject = songObj.get(parseFirstNumber(songs.get(songName)));
			Catalog.out.println(songObject.toString());
			String response = cap(Catalog.in.readLine());
			checkIfSameSong(response,songObject,songName);
		}//Close check is already added if
	}
	
	//To check if song is already in database
	public void checkIfSameSong(String response,Object song, String songTitle) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("<--checkIfSameSong-->");//For Debugging
		if (response.equalsIgnoreCase("yes")){
			checkIfEdit(song);
		} else if (response.equalsIgnoreCase("no")) {
			Catalog.out.println("So, this is a new song.");
			newSong(songTitle);//Call method that asks user for input of song info not knowing genre
		} else {
			notUnderstandable(response);
		}//Close check if same song
	}//Close checkIfSameSong method
	
	//To Check if user wants to edit a song in database
	public void checkIfEdit(Object song) throws IOException{
		System.out.println("<--Library.checkIfEdit()-->");//For Debugging
		Catalog.out.println("Would you like to edit this?");
		String response;
			response = Catalog.in.readLine();
		if (response.equalsIgnoreCase("yes")){//Yes? then go to edit method
			editSong(song);
		} else if (response.equalsIgnoreCase("no")){//No? then go main menu
			promptMain();
		} else {//If neither then take them back to main menu
			notUnderstandable(response);
		}//Close check if want to edit
	}//Close checkIfEdit Method
	
	
	//To try and harvest genre an artist makes
	public void tryHarvest(String songArtist,String songTitle) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		System.out.println("<--tryHarvest()-->");
		if (songs.containsKey(songArtist)&&songs.get(songArtist)!=null) {
			//Get a song of theirs and check the genre
			Object sObject=songObj.get(parseFirstNumber(songs.get(songArtist)));
			Music songObject = (Music) (sObject);
			String genre=cap(songObject.getGenre());
			newSong(genre,songTitle, songArtist);//Make new song object with title, artist and genre telling what class to use
		}else{
			newSong(songTitle,songArtist);//Make new song with title, artist not knowing genre
		}//close if to harvest genre
	}//Close tryHarvest
	

	//Ask info to fill out song info
	public void askInfo(String songClass,Object newSong) throws IOException{
		//Check if object is an orchestra object
		if (songClass.equals("Orchestral")) {
			askInfoIfOrchestral(newSong);
		}//end if AskInfo Orchestra
	}//Close method
	
	public void askInfoIfOrchestral(Object newSong) throws IOException{
		System.out.println("<--askInfoIfOrchestral-->");//For Debugging
		Orchestral orch = (Orchestral) newSong;
		//Add Known Info to song
		songs.put(orch.getTitle(),songs.get(orch.getTitle())+ " " + Orchestral.getSongId());//Take current value and add this objects SongId value
		songs.put(orch.getComposer(),songs.get(orch.getComposer())+ " " + Orchestral.getSongId());
		songs.put(orch.getGenre(),songs.get(orch.getGenre())+ " " + Orchestral.getSongId());
		//Add title and composer to spellcheck
		spellDict.add(orch.getTitle());
		spellDict.add(orch.getComposer());
		//Ask and add unknown info for this object
		Catalog.out.println("What year was "+orch.getTitle() + " written?");
		String response = Catalog.in.readLine();
		if (!(response.equalsIgnoreCase("") || response.equalsIgnoreCase("\\s+"))) {
			orch.setYear(Integer.parseInt(response));
			songs.put(response,songs.get(orch.getYear())+ " " + Orchestral.getSongId());
		}//Close if
		Catalog.out.println("What is the duration of the song in seconds?");
		response = Catalog.in.readLine();
		if (!(response.equalsIgnoreCase("") || response.equalsIgnoreCase("\\s+"))) {
			orch.setDuration(Double.parseDouble(response));
			songs.put(response,songs.get(orch.getDuration())+ " " + Orchestral.getSongId());
		}
		Catalog.out.println("What are the instraments used?"
				+ " Enter them on seperate lines and when your done enter an empty line.");
		boolean enteringData=true;
		ArrayList<String> temp= new ArrayList<String>(); 
		while (enteringData) {
			response = Catalog.in.readLine().toUpperCase();
			enteringData = !(response.equalsIgnoreCase("") || response.equalsIgnoreCase("\\s+"));
			if (enteringData) {
				temp.add(response);
			}//Close if
		}//Close for
		if (!(temp.isEmpty())) { //Only parse if necessary
		String[] ttemp = temp.toArray(new String[temp.size()]);
		orch.setInstruments(ttemp);
		}
		Catalog.out.println("Enter any notes you would like to attach to this song, or leave it blank.");
		response = Catalog.in.readLine();
		if (!(response.equalsIgnoreCase("") || response.equalsIgnoreCase("\\s+"))) {
			orch.setComment(response);
		}//Close if
		//Add id to song reference
		songObj.put(Orchestral.getSongId(), orch);
	}//Close method askInfoIfOrchestra
	
	
	/*//Displays all uninitialized variables
	public void askInfo(Object newSong) throws IllegalArgumentException, IllegalAccessException{ //ask for info not already entered
		System.out.println("<--Orchestral.askInfo()-->");//For Debugging
		for (Field f : newSong.getClass().getDeclaredFields()) {
		        Class t = f.getType();
		      	Object o = f.get(newSong);
		        /*For booleans
		        if(t == boolean.class && Boolean.FALSE.equals(v)) 
		        {// found default value		 }*
		        if(t.isPrimitive() && ((Number) o).doubleValue() == 0)
		        {// found default value
		        	 Catalog.out.println("What is "+f);
		        	 Catalog.out.println("this is still not finished");
		        }
		        else if(!t.isPrimitive() && o == null)
		        { // found default value
		        	Catalog.out.println("What is "+f);
		        	Catalog.out.println("this is still not finished");
		        }//Close if
		}//Close for
	}//Close askInfoMethod
	*/
	
	public int parseFirstNumber(String numbers) {
		numbers.trim();
		String[] songIdString = numbers.split("\\s+");
		int songIdGetter=Integer.parseInt(songIdString[1]);
		System.out.println(songIdGetter);
		return songIdGetter;
	}
	
	public int[] parseSongIds(String numbers) {
		numbers.trim();
		String[] songIdString = numbers.split("\\s+");
		int[] songIds=new int[songIdString.length];
		for(int i=1;i<songIdString.length;i++){	
		int temp=Integer.parseInt(songIdString[i]);
		songIds[i]=temp;
		}
		return songIds;
	}
	
	//For returning an exception back to main menu
	public void notUnderstandable(String response) {
		System.out.println("<--notUnderstandable-->");//For Debugging
		Catalog.out.println("I'm sorry I didn't understand: " + response + 
				"\nI was looking for \"Yes\" or \"No\".");
		promptMain();
	}
	//Capitalizes a String
	public String cap (String s){
		if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	//Constructor Method
	public Library() {
		songs = new  HashMap<String, String>();
		spellDict = new ArrayList<String>();
		songObj = new HashMap<Integer, Object>();
	}
	
	
	
}//Close Library Class
