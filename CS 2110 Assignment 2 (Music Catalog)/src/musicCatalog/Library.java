package musicCatalog;

import java.io.IOException;
import java.util.HashMap;
import java.lang.reflect.*;

public class Library {
	
	//Declare Variables To Hold Information About the Library 
	private static int numberOfSongs = 0;
	private static int numberOfGenres = 0;
	private String libraryName;
	HashMap<String, Object> songs;
	HashMap<String, Class> classes;
	
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
	public void addSong() throws IOException {
		System.out.println("<--addSong()-->");
		
		//Ask for and Artist as a reference and the Name of the song
		Catalog.out.println("What is the name of the Song?");
		String songName = Catalog.in.readLine();
		
		//Check to see if a song by that name already exists and if it does ask to edit, and if not create new object
		checkIfContained(songName);
		
		Catalog.out.println("What is the name of the Artist or Composer?");
		String songArtist = Catalog.in.readLine();
		
		//Try to pull up information on what type of genre they may be
		tryHarvest(songArtist,songName);
		
		//Both checks flow to newSong() to make a new object.
	}//Close addSong method
	
	//Make new song object with title, artist and genre telling what class to use
	public void newSong(String songClass,String title, String artist) {
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
	}//Close newSong Method
	
	
	//To remove a song from the catalog
	public void deleteSong() {
		
	}
	
	public void editSong(Object song) {
		
	}
	
	//To check the information entered by the user
	public void spellChecker() {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	//To check if song title is in database
	public void checkIfContained(String songName) throws IOException {
		System.out.println("<--checkIfContained-->");
		if (songs.containsKey(songName)){
			Catalog.out.println("Hmm... I think you already added this song. Is this it?");
			Object songObject = songs.get(songName);
			Catalog.out.println(songObject.toString());
			String response = Catalog.in.readLine();
			checkIfSameSong(response,songObject,songName);
		}//Close check is already added if
	}
	
	//To check if song is already in database
	public void checkIfSameSong(String response,Object song, String songTitle) throws IOException {
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
	public void tryHarvest(String songArtist,String songTitle) {
		System.out.println("<--tryHarvest()-->");
		if (songs.containsKey(songArtist)) {
			//Get a song of theirs and check the genre
			Music songObject = (Music) (songs.get(songArtist));
			String genre=songObject.getGenre();
			newSong(genre,songTitle, songArtist);//Make new song object with title, artist and genre telling what class to use
		}else{
			newSong(songTitle,songArtist);//Make new song with title, artist not knowing genre
		}//close if to harvest genre
	}//Close tryHarvest
	
	
	//For returning an exception back to main menu
	public void notUnderstandable(String response) {
		System.out.println("<--notUnderstandable-->");//For Debugging
		Catalog.out.println("I'm sorry I didn't understand: " + response + 
				"\nI was looking for \"Yes\" or \"No\".");
		promptMain();
	}
	//Constructor Method
	public Library() {
		songs = new  HashMap<String, Object>();
		classes = new HashMap<String, Class>();
	}
	
	
	
}//Close Library Class
